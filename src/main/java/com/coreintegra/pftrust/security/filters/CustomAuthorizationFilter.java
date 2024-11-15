package com.coreintegra.pftrust.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.administration.PfDepartmentRole;
import com.coreintegra.pftrust.entities.administration.Privilege;
import com.coreintegra.pftrust.entities.administration.User;
import com.coreintegra.pftrust.entities.administration.UserTenantPfDepartmentRoleMapping;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.security.JWTUtil;
import com.coreintegra.pftrust.services.administration.UserService;
import com.coreintegra.pftrust.services.tenant.TenantService;
import com.coreintegra.pftrust.util.ObjectMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(CustomAuthorizationFilter.class);

    private final TenantService tenantService;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    public CustomAuthorizationFilter(TenantService tenantService, UserService userService, JWTUtil jwtUtil) {
        this.tenantService = tenantService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);

        if (token != null) {

            try {

                DecodedJWT decodedJWT = jwtUtil.validateToken(token);

                String username = decodedJWT.getSubject();

                UserDetailsService userDetailsService = (UserDetailsService) userService;

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                String tenantId = decodedJWT.getClaim("tenant").asString();

                Tenant tenant = tenantService.getTenantForUser(tenantId, (User) userDetails);

                TenantContext.setCurrentTenant(tenant);

                List<UserTenantPfDepartmentRoleMapping> userRolesForTenant = userService.getUserRolesForTenant((User) userDetails, tenant);

                String servletPath = request.getServletPath();

                String department = getDepartment(servletPath);

                List<PfDepartmentRole> pfDepartmentRoles = new ArrayList<>();

                userRolesForTenant.forEach(roleMapping -> pfDepartmentRoles.addAll(roleMapping
                        .getPfDepartmentRoleGroup().getPfDepartmentRoles()));

                List<List<Privilege>> departmentPrivileges = pfDepartmentRoles.stream()
                        .filter(role -> role.getPath().toLowerCase().contains(department))
                        .map(role -> role.getRole().getPrivileges())
                        .collect(Collectors.toList());

                if(departmentPrivileges.isEmpty()){
                    throw new AccessDeniedException("not authorized the access this resource");
                }

                List<String> privileges = departmentPrivileges.get(0).stream().map(Privilege::getName).collect(Collectors.toList());

                Collection<SimpleGrantedAuthority> authorities = new HashSet<>();

                privileges.forEach(privilege -> authorities.add(new SimpleGrantedAuthority(privilege)));

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                filterChain.doFilter(request, response);

            } catch (Exception e) {

                e.printStackTrace();

                logger.error("Error logging in {}", e.getMessage());

                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType(APPLICATION_JSON_VALUE);
                Map<String, Object> errors = new HashMap<>();
                errors.put("timestamp", new Date().toString());
                errors.put("status", HttpStatus.FORBIDDEN.value());
                errors.put("error", e.getMessage());
                ObjectMapperUtil.objectMapper.writeValue(response.getOutputStream(), errors);

            }

        }else {
            filterChain.doFilter(request, response);
        }

    }

    private String getDepartment(String requestPath){
        String[] split = requestPath.split("/");
        return split[3];
    }

}
