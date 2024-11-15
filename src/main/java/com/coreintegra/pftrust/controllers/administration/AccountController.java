package com.coreintegra.pftrust.controllers.administration;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.controllers.API;
import com.coreintegra.pftrust.entities.administration.*;
import com.coreintegra.pftrust.entities.administration.dtos.*;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.services.administration.UserService;
import com.coreintegra.pftrust.services.pf.jobs.JobLaunchService;
import com.coreintegra.pftrust.util.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.coreintegra.pftrust.controllers.administration.AdministrationEndpoints.*;

@RestController
@RequestMapping(ACCOUNT_SERVICE_ENDPOINT)
public class AccountController {

    private final UserService userService;
    private final JobLaunchService jobLaunchService;

    public AccountController(UserService userService, JobLaunchService jobLaunchService) {
        this.userService = userService;
        this.jobLaunchService = jobLaunchService;
    }

    @GetMapping(USER_PROFILE_ENDPOINT)
    public ResponseEntity<Object> get() throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();
        
        Tenant currentTenant = TenantContext.getCurrentTenant();

        UserDetailsDto userDetailsDto = UserDetailsDto.build(user, currentTenant);

        List<UserTenantPfDepartmentRoleMapping> userRolesForTenant = userService.getUserRolesForTenant(user, currentTenant);

        List<String> roles = userRolesForTenant.stream().map(roleMapping -> roleMapping.getPfDepartmentRoleGroup().getName())
                .collect(Collectors.toList());

        userDetailsDto.setRoles(roles);

        return Response.success(userDetailsDto);
    }

    @PutMapping("")
    public ResponseEntity<Object> update(@RequestBody UserDetailsDto userDetailsDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.updateUser(userDetailsDto, authentication.getName());

        Tenant currentTenant = TenantContext.getCurrentTenant();

        return Response.success(UserDetailsDto.build(user, currentTenant));

    }

    @PutMapping(CHANGE_PASSWORD_ENDPOINT)
    public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordDto changePasswordDto) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        userService.changePassword(changePasswordDto, authentication.getName());

        return Response.success("Password Updated Successfully.");

    }

    @GetMapping(USER_PERMISSIONS_ENDPOINT)
    @ApplyTenantFilter
    public ResponseEntity<Object> getPermissions() throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        System.out.println("im called");
        
        Tenant currentTenant = TenantContext.getCurrentTenant();

        List<UserTenantPfDepartmentRoleMapping> userRolesForTenant = userService.getUserRolesForTenant(user, currentTenant);

        List<PfDepartmentRole> pfDepartmentRoles = new ArrayList<>();

        userRolesForTenant.stream().map(UserTenantPfDepartmentRoleMapping::getPfDepartmentRoleGroup)
                .forEach(pfDepartmentRoleGroup -> pfDepartmentRoles.addAll(pfDepartmentRoleGroup.getPfDepartmentRoles()));

        List<UserDepartmentPermissionsDto> userDepartmentPermissionsDtoList = pfDepartmentRoles.stream()
                .map(pfDepartmentRole -> {

                    PfDepartment pfDepartment = pfDepartmentRole.getPfDepartment();
                    List<Privilege> privileges = pfDepartmentRole.getRole().getPrivileges();

                    List<String> privilegesList = privileges.stream().map(Privilege::getName).collect(Collectors.toList());

                    List<SubDepartmentDto> subDepartmentDtoList = pfDepartment.getSubDepartments().stream()
                            .filter(BaseAuditingEntity::getIsActive)
                            .filter(department -> checkPermission(privilegesList, department.getPermission()))
                            .map(department -> new SubDepartmentDto(department.getName(), department.getLabel(),
                                    department.getType(), department.getMdiIcon(),
                                    department.getPath(), department.getPermission()))
                            .collect(Collectors.toList());

                    return new UserDepartmentPermissionsDto(pfDepartment.getName(),
                            pfDepartment.getLabel(), pfDepartment.getType(), pfDepartment.getMdiIcon(),
                            pfDepartment.getPath(), privilegesList, subDepartmentDtoList);

                })
                .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("logo", TenantContext.getCurrentTenant().getLogoImage());
        map.put("permissions", userDepartmentPermissionsDtoList);
        map.put("theme", TenantContext.getCurrentTenant().getTheme());
        map.put("clock", TenantContext.getCurrentTenant().getClock());
        map.put("logoWidth", TenantContext.getCurrentTenant().getWidth());
        map.put("logoHeight", TenantContext.getCurrentTenant().getHeight());
        map.put("logoStyleClasses", TenantContext.getCurrentTenant().getStyleClasses());

        List<Map<String, String>> activeJobs = new ArrayList<>();

        jobLaunchService.getJobs().forEach(job -> {
            JSONObject jobDetailsJson = job.getJobDetailsJson();
            String status = jobDetailsJson.getString("status");

            if(status.equalsIgnoreCase("started")){

                Map<String, String> activeJob = new HashMap<>();
                activeJob.put("status", status);
                activeJob.put("type", job.getName());

                switch (job.getName()) {
                    case "fetch_employees_job":
                        activeJob.put("to", "/jobs/fetch-employees?job-id=" + job.getEntityId());
                        break;
                    case "fetch_loan_job":
                        activeJob.put("to", "/jobs/fetch-loans?job-id=" + job.getEntityId());
                        break;
                    case "fetch_transfer_in_job":
                        activeJob.put("to", "/jobs/fetch-transfer-in?job-id=" + job.getEntityId());
                        break;
                    case "fetch_settlement_job":
                        activeJob.put("to", "/jobs/fetch-settlements?job-id=" + job.getEntityId());
                        break;
                    case "fetch_contribution_job":
                        activeJob.put("to", "/jobs/fetch-contributions?job-id=" + job.getEntityId());
                        break;
                    case "hire_new_employees_job":
                        activeJob.put("to", "/employees/hire?job-id=" + job.getEntityId());
                        break;
                    case "monthly_status_process_job":
                        activeJob.put("to", "/employees/monthly-status-process?job-id=" + job.getEntityId());
                        break;
                    default:
                        activeJob.put("to", "#");
                        break;
                }

                activeJob.put("entityId", job.getEntityId());
                activeJobs.add(activeJob);
            }

        });

        map.put("activeJobs", activeJobs);

        return Response.success(map);

    }

    @GetMapping(DEPARTMENTS)
    @ApplyTenantFilter
    public ResponseEntity<Object> getDepartments(){

        List<PfDepartmentRoleDTO> pfDepartmentDTOList = userService.getPfDepartmentRoleGroups().stream()
                .map(group -> new PfDepartmentRoleDTO(group.getName(), group.getEntityId()))
                .collect(Collectors.toList());

        return Response.success(pfDepartmentDTOList);

    }

    @GetMapping(AdministrationEndpoints.VALIDATE_PERMISSION)
    @ApplyTenantFilter
    public ResponseEntity<Object> validatePermission(@RequestParam String name, @RequestParam String sub) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Tenant currentTenant = TenantContext.getCurrentTenant();

        List<UserTenantPfDepartmentRoleMapping> userRolesForTenant = userService.getUserRolesForTenant(user, currentTenant);

        List<PfDepartmentRole> pfDepartmentRoles = new ArrayList<>();

        userRolesForTenant.stream().map(UserTenantPfDepartmentRoleMapping::getPfDepartmentRoleGroup)
                .forEach(pfDepartmentRoleGroup -> pfDepartmentRoles.addAll(pfDepartmentRoleGroup.getPfDepartmentRoles()));

        List<UserDepartmentPermissionsDto> userDepartmentPermissionsDtoList = pfDepartmentRoles.stream()
                .map(pfDepartmentRole -> {

                    PfDepartment pfDepartment = pfDepartmentRole.getPfDepartment();
                    List<Privilege> privileges = pfDepartmentRole.getRole().getPrivileges();

                    List<String> privilegesList = privileges.stream().map(Privilege::getName).collect(Collectors.toList());

                    List<SubDepartmentDto> subDepartmentDtoList = pfDepartment.getSubDepartments().stream()
                            .filter(BaseAuditingEntity::getIsActive)
                            .filter(department -> checkPermission(privilegesList, department.getPermission()))
                            .map(department -> new SubDepartmentDto(department.getName(), department.getLabel(),
                                    department.getType(), department.getMdiIcon(),
                                    department.getPath(), department.getPermission()))
                            .collect(Collectors.toList());

                    return new UserDepartmentPermissionsDto(pfDepartment.getName(),
                            pfDepartment.getLabel(), pfDepartment.getType(), pfDepartment.getMdiIcon(),
                            pfDepartment.getPath(), privilegesList, subDepartmentDtoList);

                })
                .collect(Collectors.toList());

        List<UserDepartmentPermissionsDto> selectedDepartments = userDepartmentPermissionsDtoList.stream()
                .filter(userDepartmentPermissionsDto -> userDepartmentPermissionsDto.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        if(selectedDepartments.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserDepartmentPermissionsDto userDepartmentPermissionsDto = selectedDepartments.get(0);

        if((sub == null || sub.equalsIgnoreCase("null")) && userDepartmentPermissionsDto.getSubDepartments().isEmpty()){
            return ResponseEntity.ok().build();
        }

        List<SubDepartmentDto> selectedSubDepartments = userDepartmentPermissionsDto.getSubDepartments().stream()
                .filter(subDepartmentDto -> subDepartmentDto.getName().equalsIgnoreCase(sub))
                .collect(Collectors.toList());

        if(selectedSubDepartments.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        SubDepartmentDto subDepartmentDto = selectedSubDepartments.get(0);

        String[] permissions = subDepartmentDto.getPermission().split("\\|");

        List<String> userPermissions = userDepartmentPermissionsDto.getPermissions();

        for (String permission:permissions) {
            for (String userPermission:userPermissions) {
                if(permission.equalsIgnoreCase(userPermission)){
                    return ResponseEntity.ok().build();
                }
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    private boolean checkPermission(List<String> privilegesList, String requiredPermissions){

        String[] split = requiredPermissions.split("\\|");

        return Arrays.stream(split).anyMatch(privilegesList::contains);

    }

}
