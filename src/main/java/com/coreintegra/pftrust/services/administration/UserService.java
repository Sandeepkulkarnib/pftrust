package com.coreintegra.pftrust.services.administration;

import com.coreintegra.pftrust.entities.administration.*;
import com.coreintegra.pftrust.entities.administration.dtos.ChangePasswordDto;
import com.coreintegra.pftrust.entities.administration.dtos.NewUserDTO;
import com.coreintegra.pftrust.entities.administration.dtos.UserDetailsDto;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.util.Pair;
import com.coreintegra.pftrust.util.Triple;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUsername(String username);

    List<UserTenantPfDepartmentRoleMapping> getUserRolesForTenant(User user, Tenant tenant) throws Exception;

    User createUser(NewUserDTO newUserDTO, Tenant tenant) throws Exception;

    User updateUser(UserDetailsDto userDetailsDto, String username);

    void changePassword(ChangePasswordDto changePasswordDto, String username) throws Exception;

    Page<UserTenantMapping> getUserTenantMapping(Tenant tenant, int page, int size);

    void sendUserInvitation(User user, Tenant tenant);

    Triple<User, String, Tenant> verifyUser(Map<String, String> request) throws Exception;

    void sendUserDetails(User user, String password, Tenant tenant);

    List<PfDepartment> getPfDepartment();

    List<PfDepartmentRoleGroup> getPfDepartmentRoleGroups();

}
