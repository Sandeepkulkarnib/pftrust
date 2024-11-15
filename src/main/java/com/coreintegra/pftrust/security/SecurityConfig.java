package com.coreintegra.pftrust.security;

import com.coreintegra.pftrust.cache.httprequest.CacheHttpServletRequestBodyFilter;
import com.coreintegra.pftrust.controllers.API;
import com.coreintegra.pftrust.controllers.administration.AdministrationEndpoints;
import com.coreintegra.pftrust.controllers.administration.user.UserServiceEndpoints;
import com.coreintegra.pftrust.controllers.pf.department.DepartmentServiceEndpoints;
import com.coreintegra.pftrust.controllers.pf.employee.EmployeeServiceEndpoints;
import com.coreintegra.pftrust.controllers.pf.files.FileServiceEndpoints;
import com.coreintegra.pftrust.controllers.pf.loan.LoanServiceEndpoints;
import com.coreintegra.pftrust.controllers.pf.settlement.SettlementServiceEndpoints;
import com.coreintegra.pftrust.controllers.pf.transferin.TransferInServiceEndpoints;
import com.coreintegra.pftrust.controllers.pf.transferout.TransferOutServiceEndpoints;
import com.coreintegra.pftrust.repositories.RequestLoggerRepository;
import com.coreintegra.pftrust.security.filters.CustomAuthenticationFilter;
import com.coreintegra.pftrust.security.filters.CustomAuthorizationFilter;
import com.coreintegra.pftrust.security.filters.UserActivityLoggerFilter;
import com.coreintegra.pftrust.services.administration.UserService;
import com.coreintegra.pftrust.services.tenant.TenantService;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final TenantService tenantService;
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final Tracer tracer;
    private final RequestLoggerRepository requestLoggerRepository;

    public SecurityConfig(UserDetailsService userDetailsService,
                          TenantService tenantService,
                          UserService userService, JWTUtil jwtUtil, PasswordEncoder passwordEncoder, Tracer tracer,
                          RequestLoggerRepository requestLoggerRepository) {
        this.userDetailsService = userDetailsService;
        this.tenantService = tenantService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.tracer = tracer;
        this.requestLoggerRepository = requestLoggerRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        UserActivityLoggerFilter userActivityLoggerFilter = new UserActivityLoggerFilter(jwtUtil, requestLoggerRepository, tracer);

        CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter(authenticationManagerBean(),
                        tenantService, jwtUtil);

        customAuthenticationFilter.setFilterProcessesUrl(
                AdministrationEndpoints.AUTH_SERVICE_ENDPOINT + AdministrationEndpoints.LOGIN_ENDPOINT);

        http.cors().configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.addAllowedOrigin("http://localhost:8080/");
            config.addAllowedOrigin("http://3.110.35.227:8081/");



            config.addAllowedOrigin("https://developerspftrust.coreintegra.com/api/v1/");
            config.addAllowedOrigin("http://developerspftrust.coreintegra.com/api/v1/");
            config.addAllowedOrigin("http://developerspftrust.coreintegra.com/");
            config.addAllowedOrigin("https://developerspftrust.coreintegra.com/");

            config.addAllowedOrigin("https://hpcpftrust.coreintegra.com/api/v1/");
            config.addAllowedOrigin("http://hpcpftrust.coreintegra.com/api/v1/");
            config.addAllowedOrigin("http://hpcpftrust.coreintegra.com/");
            config.addAllowedOrigin("https://hpcpftrust.coreintegra.com/");

            config.setAllowCredentials(true);
            return config;
        });

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(AdministrationEndpoints.AUTH_SERVICE_ENDPOINT + API.PATH_SEPARATOR + "**").permitAll();

        http.authorizeRequests().antMatchers(UserServiceEndpoints.USER_SERVICE_ENDPOINT
                + UserServiceEndpoints.VERIFY_USER_ENDPOINT).permitAll();

        http.authorizeRequests().antMatchers("/download/**").permitAll();

        http.authorizeRequests().antMatchers("/images/**").permitAll();

        http.authorizeRequests().antMatchers(FileServiceEndpoints.FILE_SERVICE_ENDPOINT + "/**").permitAll();

//        addServiceAccessRules(http, DashboardServiceEndpoints.DASHBOARD_SERVICE_ENDPOINT);
        addServiceAccessRules(http, EmployeeServiceEndpoints.EMPLOYEE_SERVICE_ENDPOINT);
        addServiceAccessRules(http, AdministrationEndpoints.ACCOUNT_SERVICE_ENDPOINT);
        addServiceAccessRules(http, DepartmentServiceEndpoints.DEPARTMENT_SERVICE_ENDPOINT);
        addServiceAccessRules(http, LoanServiceEndpoints.LOAN_SERVICE_ENDPOINT);
        addServiceAccessRules(http, TransferInServiceEndpoints.TRANSFER_IN_SERVICE_ENDPOINT);
        addServiceAccessRules(http, TransferOutServiceEndpoints.TRANSFER_OUT_SERVICE_ENDPOINT);
        addServiceAccessRules(http, SettlementServiceEndpoints.SETTLEMENT_SERVICE_ENDPOINT);

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(tenantService, userService, jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new CacheHttpServletRequestBodyFilter(), CustomAuthorizationFilter.class);
        http.addFilterAfter(userActivityLoggerFilter, CustomAuthorizationFilter.class);

    }

    private void addServiceAccessRules(HttpSecurity http, String serviceEndpoint) throws Exception {

        http.authorizeRequests().antMatchers(HttpMethod.GET,
                        serviceEndpoint + "/**")
                .hasAnyAuthority(SecurityPrivileges.READ, SecurityPrivileges.CREATE,
                        SecurityPrivileges.UPDATE, SecurityPrivileges.DELETE);

        http.authorizeRequests().antMatchers(HttpMethod.POST,
                        serviceEndpoint + "/**")
                .hasAnyAuthority(SecurityPrivileges.CREATE, SecurityPrivileges.UPDATE,
                        SecurityPrivileges.DELETE);

        http.authorizeRequests().antMatchers(HttpMethod.PUT,
                        serviceEndpoint + "/**")
                .hasAnyAuthority(SecurityPrivileges.UPDATE,
                        SecurityPrivileges.DELETE);

        http.authorizeRequests().antMatchers(HttpMethod.PATCH,
                        serviceEndpoint + "/**")
                .hasAnyAuthority(SecurityPrivileges.UPDATE,
                        SecurityPrivileges.DELETE);

        http.authorizeRequests().antMatchers(HttpMethod.DELETE,
                        serviceEndpoint + "/**")
                .hasAnyAuthority(SecurityPrivileges.DELETE);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

