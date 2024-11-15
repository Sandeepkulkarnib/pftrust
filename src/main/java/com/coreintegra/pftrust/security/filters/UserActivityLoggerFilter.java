package com.coreintegra.pftrust.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.coreintegra.pftrust.entities.RequestLogger;
import com.coreintegra.pftrust.repositories.RequestLoggerRepository;
import com.coreintegra.pftrust.security.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class UserActivityLoggerFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(UserActivityLoggerFilter.class);

    private final JWTUtil jwtUtil;
    private final RequestLoggerRepository requestLoggerRepository;

    private final Tracer tracer;

    public UserActivityLoggerFilter(JWTUtil jwtUtil, RequestLoggerRepository requestLoggerRepository, Tracer tracer) {
        this.jwtUtil = jwtUtil;
        this.requestLoggerRepository = requestLoggerRepository;
        this.tracer = tracer;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);

        String username = "LOGIN_REQUEST_USER";
        String tenantId = "NA";
        String traceId = "NA";

        if(token != null){

            try {

                DecodedJWT decodedJWT = jwtUtil.validateToken(token);
                username = decodedJWT.getSubject();
                tenantId = decodedJWT.getClaim("tenant").asString();

            }catch (Exception exception){
                logger.info(exception.getMessage());
            }

        }

        Span span = tracer.currentSpan();

        if(span != null){
            traceId = span.context().traceId();
        }

        String servletPath = request.getServletPath();

        RequestLogger requestLog = new RequestLogger(UUID.randomUUID().toString(), servletPath,
                username, tenantId, traceId, new Date());

        requestLoggerRepository.save(requestLog);

        filterChain.doFilter(request, response);
    }

}
