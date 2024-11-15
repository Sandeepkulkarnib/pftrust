package com.coreintegra.pftrust.services.administration;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.coreintegra.pftrust.entities.administration.*;
import com.coreintegra.pftrust.entities.administration.dtos.ChangePasswordDto;
import com.coreintegra.pftrust.entities.administration.dtos.NewUserDTO;
import com.coreintegra.pftrust.entities.administration.dtos.UserDetailsDto;
import com.coreintegra.pftrust.entities.tenant.NotificationEmailDesign;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.exceptions.AuthException;
import com.coreintegra.pftrust.repositories.administration.*;
import com.coreintegra.pftrust.repositories.pf.NotificationEmailDesignRepository;
import com.coreintegra.pftrust.repositories.tenant.UserTenantMappingRepository;
import com.coreintegra.pftrust.security.JWTUtil;
import com.coreintegra.pftrust.services.email.EmailService;
import com.coreintegra.pftrust.services.tenant.TenantService;
import com.coreintegra.pftrust.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserTenantPfDepartmentRoleMappingRepository userTenantPfDepartmentRoleMappingRepository;
    private final UserTenantMappingRepository userTenantMappingRepository;
    private final PfDepartmentRepository pfDepartmentRepository;
    private final PfDepartmentRoleGroupRepository pfDepartmentRoleGroupRepository;
    private final NotificationEmailDesignRepository notificationEmailDesignRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;
    private final TenantService tenantService;

    private final JWTUtil jwtUtil;

    @Value("${application.baseURL}")
    private String baseURL;


    public UserServiceImpl(UserRepository userRepository,
                           UserTenantPfDepartmentRoleMappingRepository userTenantPfDepartmentRoleMappingRepository,
                           UserTenantMappingRepository userTenantMappingRepository,
                           PfDepartmentRepository pfDepartmentRepository,
                           PfDepartmentRoleGroupRepository pfDepartmentRoleGroupRepository,
                           NotificationEmailDesignRepository notificationEmailDesignRepository,
                           PasswordEncoder passwordEncoder, EmailService emailService,
                           TenantService tenantService, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userTenantPfDepartmentRoleMappingRepository = userTenantPfDepartmentRoleMappingRepository;
        this.userTenantMappingRepository = userTenantMappingRepository;
        this.pfDepartmentRepository = pfDepartmentRepository;
        this.pfDepartmentRoleGroupRepository = pfDepartmentRoleGroupRepository;
        this.notificationEmailDesignRepository = notificationEmailDesignRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.tenantService = tenantService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsernameAndIsActive(username, true);
        if(optionalUser.isEmpty()){
            logger.error("user not found for give user name -> {}", username);
            throw new UsernameNotFoundException("user not found for give user name");
        }
        return optionalUser.get();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsernameAndIsActive(username, true);
    }

    @Override
    @Transactional
    public List<UserTenantPfDepartmentRoleMapping> getUserRolesForTenant(User user, Tenant tenant) throws Exception {

        List<UserTenantPfDepartmentRoleMapping> userTenantPfDepartmentRoleMappingList =
                userTenantPfDepartmentRoleMappingRepository.findByUserAndTenantAndIsActive(user,
                        tenant, true);

        if(userTenantPfDepartmentRoleMappingList.isEmpty()){
            throw new Exception("Any roles not assigned to user for the given tenant");
        }

        return userTenantPfDepartmentRoleMappingList;

    }

    @Override
    public User createUser(NewUserDTO newUserDTO, Tenant tenant) throws Exception {

        Optional<User> optionalUserByUserName = userRepository.findByUsernameAndIsActive(newUserDTO.getUsername(),
                true);

        if(optionalUserByUserName.isPresent()){
            throw new EntityExistsException("user already exists with username.");
        }

        Optional<User> optionalUserByEmail = userRepository.findByEmailAndIsActive(newUserDTO.getEmail(),
                true);

        if(optionalUserByEmail.isPresent()){
            throw new EntityExistsException("user already exists with email.");
        }

        List<PfDepartmentRoleGroup> pfDepartmentRoleGroups = pfDepartmentRoleGroupRepository.findAllByIsActiveAndEntityIdIn(
                true, Set.copyOf(newUserDTO.getRoles()));

        if(pfDepartmentRoleGroups.size() != newUserDTO.getRoles().size()){
            throw new Exception("invalid roles");
        }

        User user = new User(newUserDTO.getFirstName(), newUserDTO.getLastName(), newUserDTO.getEmail(),
                newUserDTO.getUsername());

        User save = userRepository.save(user);

        UserTenantMapping userTenantMapping = new UserTenantMapping();

        userTenantMapping.setUser(save);
        userTenantMapping.setTenant(tenant);
        userTenantMapping.setIsActive(true);
        userTenantMapping.setEntityId(UUID.randomUUID().toString());

        UserTenantMapping savedUserTenantMapping = userTenantMappingRepository.save(userTenantMapping);

        List<UserTenantPfDepartmentRoleMapping> departmentRoleMappingList = pfDepartmentRoleGroups.stream().map(pfDepartmentRoleGroup -> {
            UserTenantPfDepartmentRoleMapping roleMapping = new UserTenantPfDepartmentRoleMapping();
            roleMapping.setUser(save);
            roleMapping.setTenant(tenant);
            roleMapping.setPfDepartmentRoleGroup(pfDepartmentRoleGroup);
            roleMapping.setIsActive(true);
            roleMapping.setEntityId(UUID.randomUUID().toString());

            return roleMapping;
        }).collect(Collectors.toList());

        List<UserTenantPfDepartmentRoleMapping> savedDepartmentRoleMappingList = userTenantPfDepartmentRoleMappingRepository
                .saveAll(departmentRoleMappingList);

        save.setUserTenantMappingList(List.of(savedUserTenantMapping));
        save.setUserTenantPfDepartmentRoleMappingList(savedDepartmentRoleMappingList);

        return save;
    }

    @Override
    public User updateUser(UserDetailsDto userDetailsDto, String username) {

        Optional<User> optionalUserByUsername = userRepository.findByUsernameAndIsActive(username, true);

        if(optionalUserByUsername.isEmpty()){
            throw new EntityNotFoundException("user not found");
        }

        User user = optionalUserByUsername.get();

        user.setFirstName(userDetailsDto.getFirstName());
        user.setLastName(userDetailsDto.getLastName());

        return userRepository.save(user);

    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto, String username) throws Exception {

        Optional<User> optionalUserByUsername = userRepository.findByUsernameAndIsActive(username, true);

        if(optionalUserByUsername.isEmpty()){
            throw new EntityNotFoundException("user not found");
        }

        User user = optionalUserByUsername.get();

        boolean matches = passwordEncoder.matches(changePasswordDto.getCurrentPassword(), user.getPassword());

        if(!matches){
            throw new Exception("Invalid Password");
        }

        if(!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())){
            throw new Exception("Please provide same password in confirm password field");
        }

        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));

        userRepository.save(user);

    }

    @Override
    public Page<UserTenantMapping> getUserTenantMapping(Tenant tenant, int page, int size) {
        return userTenantMappingRepository.getAllByTenant(tenant, PageRequest.of(page-1, size));
    }

    @Override
    @Async
    public void sendUserInvitation(User user, Tenant tenant) {

        String accessToken = jwtUtil.createAccessToken(user, tenant);

        String link = baseURL + "/verify?token=" + accessToken;

        String subject = "Subject: " + tenant.getName() + " PF Trust Management Account Invitation";

        Optional<NotificationEmailDesign> optional = notificationEmailDesignRepository
                .findByDocumentTypeAndTenant(NotificationEmailDesign.USER_ACCOUNT_INVITATION_EMAIL, tenant);

        if(optional.isEmpty()) throw new EntityNotFoundException("Email Design Not Found");

        NotificationEmailDesign notificationEmailDesign = optional.get();

        String emailBody = notificationEmailDesign.getDocument()
                .replace("{{firstName}}", user.getFirstName())
                .replace("{{lastName}}", user.getLastName())
                .replace("{{link}}", link);

        emailService.SendEmail(user.getEmail(),subject, emailBody);

    }

    @Override
    public Triple<User, String, Tenant> verifyUser(Map<String, String> request) throws Exception {

        String token = request.get("token");

        if(token == null){
            throw new AuthException("Invalid User");
        }

        DecodedJWT decodedJWT = jwtUtil.validateToken(token);

        String username = decodedJWT.getSubject();

        Optional<User> optionalUser = userRepository.findByUsernameAndIsActiveAndIsEnabled(username,
                false, false);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }

        User user = optionalUser.get();

        String tenantId = decodedJWT.getClaim("tenant").asString();

        Tenant tenantForUser = tenantService.getTenantForUser(tenantId, user);

        user.setIsActive(true);
        user.setEnabled(true);

        String password = GeneratePasswordUtil.generatePassword(8);

        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);

        return new Triple<User, String, Tenant>() {
            @Override
            public User getFirst() {
                return user;
            }

            @Override
            public String getSecond() {
                return password;
            }

            @Override
            public Tenant getThird() {
                return tenantForUser;
            }
        };

    }

    @Override
    public void sendUserDetails(User user, String password, Tenant tenant) {

        String subject = " Subject: Account Details";

        Optional<NotificationEmailDesign> optional = notificationEmailDesignRepository
                .findByDocumentTypeAndTenant(NotificationEmailDesign.USER_ACCOUNT_DETAILS, tenant);

        if(optional.isEmpty()) throw new EntityNotFoundException("Email Design Not Found");

        NotificationEmailDesign notificationEmailDesign = optional.get();

        String emailBody = notificationEmailDesign.getDocument()
                .replace("{{firstName}}", user.getFirstName())
                .replace("{{lastName}}", user.getLastName())
                .replace("{{tenantName}}", tenant.getName())
                .replace("{{username}}", user.getUsername())
                .replace("{{password}}", password);

        emailService.SendEmail(user.getEmail(),subject, emailBody);


    }

    @Override
    public List<PfDepartment> getPfDepartment() {
        return pfDepartmentRepository.findAllByIsActive(true);
    }

    @Override
    public List<PfDepartmentRoleGroup> getPfDepartmentRoleGroups() {
        return pfDepartmentRoleGroupRepository.findAllByIsActive(true);
    }

}
