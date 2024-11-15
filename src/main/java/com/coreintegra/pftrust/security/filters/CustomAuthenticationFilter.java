package com.coreintegra.pftrust.security.filters;

import com.coreintegra.pftrust.entities.administration.User;
import com.coreintegra.pftrust.entities.administration.dtos.LoginRequestDto;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.exceptions.AuthException;
import com.coreintegra.pftrust.security.JWTUtil;
import com.coreintegra.pftrust.services.administration.UserService;
import com.coreintegra.pftrust.services.tenant.TenantService;
import com.coreintegra.pftrust.util.ObjectMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;
    private final TenantService tenantService;
    private final JWTUtil jwtUtil;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager,
                                      TenantService tenantService,
                                      JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.tenantService = tenantService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        Optional<LoginRequestDto> optionalLoginRequestDto = getLoginRequestDto(request);

        if(optionalLoginRequestDto.isEmpty()){
            logger.error("Authentication failed");
            throw new AuthException("Authentication failed");
        }

        LoginRequestDto loginRequestDto = optionalLoginRequestDto.get();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(), loginRequestDto.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User user = (User) authenticate.getPrincipal();

        try {
            tenantService.getTenantForUser(loginRequestDto.getTenantId(), user);
        } catch (Exception e) {
            logger.error("Authentication failed");
            throw new AuthException("Authentication failed");
        }

        return authenticate;

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();

        Optional<LoginRequestDto> optionalLoginRequestDto = getLoginRequestDto(request);

        LoginRequestDto loginRequestDto = optionalLoginRequestDto.get();

        try {

            Tenant tenant = tenantService.getTenantForUser(loginRequestDto.getTenantId(), user);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("x-access-token", jwtUtil.createAccessToken(user, tenant));
            tokens.put("x-refresh-token", jwtUtil.createRefreshToken(user, tenant));

            Map<String, Object> details = new HashMap<>();
            details.put("username", user.getUsername());
            details.put("firstName", user.getFirstName());
            details.put("lastName", user.getLastName());
            details.put("email", user.getEmail());
            details.put("tokens", tokens);
            details.put("tenant", tenant.getName());

            response.setContentType(APPLICATION_JSON_VALUE);

            ObjectMapperUtil.objectMapper.writeValue(response.getOutputStream(), details);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        Map<String, Object> errors = new HashMap<>();
        errors.put("timestamp", new Date().toString());
        errors.put("status", HttpStatus.UNAUTHORIZED.value());
        errors.put("error", failed.getMessage());
        ObjectMapperUtil.objectMapper.writeValue(response.getOutputStream(), errors);

    }

    private Optional<LoginRequestDto> getLoginRequestDto(HttpServletRequest request) {
        try {

            String body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);;

            return Optional.of(ObjectMapperUtil.map(body, LoginRequestDto.class));

        }catch (IOException exception){
            exception.printStackTrace();
        }

        return Optional.empty();
    }
}
