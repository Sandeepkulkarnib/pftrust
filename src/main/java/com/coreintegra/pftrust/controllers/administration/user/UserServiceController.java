package com.coreintegra.pftrust.controllers.administration.user;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.administration.User;
import com.coreintegra.pftrust.entities.administration.UserTenantMapping;
import com.coreintegra.pftrust.entities.administration.dtos.NewUserDTO;
import com.coreintegra.pftrust.entities.administration.dtos.UserDTO;
import com.coreintegra.pftrust.entities.administration.dtos.UserDetailsDto;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.services.administration.UserService;
import com.coreintegra.pftrust.util.Response;
import com.coreintegra.pftrust.util.Triple;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UserServiceEndpoints.USER_SERVICE_ENDPOINT)
public class UserServiceController {

    private final UserService userService;

    public UserServiceController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getUsers(
            @RequestParam(value = "search", required = false, defaultValue = "username=='*'")String search,
            @RequestParam(value = "page", required = false, defaultValue = "1")Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10")Integer size){

        Page<UserTenantMapping> userTenantMappingList = userService.getUserTenantMapping(
                TenantContext.getCurrentTenant(), page, size);

        Page<UserDTO> userDTOPage = userTenantMappingList.map(mapping -> {

            User user = mapping.getUser();

            UserDTO userDTO = new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(),
                    mapping.getIsActive(), mapping.getCreatedAt(), mapping.getUpdatedAt(),
                    "", "", user.getEntityId());

            userDTO.setCreatedBy(mapping.getCreatedByUser() != null ? mapping.getCreatedByUser().getUsername() : "");
            userDTO.setCreatedBy(mapping.getLastModifiedByUser() != null ? mapping.getLastModifiedByUser().getUsername() : "");

            List<String> roles = user.getUserTenantPfDepartmentRoleMappingList().stream()
                    .filter(roleMapping -> roleMapping.getTenant().getId().equals(TenantContext.getCurrentTenant().getId()))
                    .map(roleMapping -> roleMapping.getPfDepartmentRoleGroup().getName())
                    .collect(Collectors.toList());

            userDTO.setRoles(roles);

            return userDTO;
        });

        return Response.success(userDTOPage);

    }


    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody NewUserDTO newUserDTO) throws Exception {

        Tenant currentTenant = TenantContext.getCurrentTenant();

        User user = userService.createUser(newUserDTO, currentTenant);

        userService.sendUserInvitation(user, currentTenant);

        return Response.success(UserDetailsDto.build(user, currentTenant));

    }

    @PostMapping(UserServiceEndpoints.VERIFY_USER_ENDPOINT)
    public ResponseEntity<Object> verify(@RequestBody Map<String, String> request) throws Exception {

        Triple<User, String, Tenant> triple = userService.verifyUser(request);

        User user = triple.getFirst();

        String password = triple.getSecond();

        Tenant tenant = triple.getThird();

        userService.sendUserDetails(user, password, tenant);

        return Response.success(new UserDetailsDto(user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getUsername(), tenant.getName(), user.getProfileImage()));

    }


}
