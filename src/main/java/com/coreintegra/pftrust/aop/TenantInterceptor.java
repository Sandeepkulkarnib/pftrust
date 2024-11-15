package com.coreintegra.pftrust.aop;

import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.repositories.tenant.TenantRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

@Aspect
@Component
public class TenantInterceptor {

    private final Logger logger = LoggerFactory.getLogger(TenantInterceptor.class);

    private final TenantRepository tenantRepository;

    public TenantInterceptor(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Around("@annotation(InboundRequest)")
    public Object logInboundRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Intercepting inbound request...");

        logger.info("Extracting tenant id from method arguments!");
        String tenantId = extractTenantId(joinPoint)
                .orElseThrow(RuntimeException::new);

        logger.info("Finding tenant by id!");
        Tenant tenant = tenantRepository
                .findByTenantIdAndIsActive(tenantId, true)
                .orElseThrow(RuntimeException::new);

        logger.info("Setting current tenant to Thread local variable!");
        TenantContext.setCurrentTenant(tenant);

        try {
            logger.info("Continuing with the execution!");
            return joinPoint.proceed();
        } finally {
            logger.info(" Clearing tenant context!");
            TenantContext.clear();
        }
    }

    public Optional<String> extractTenantId(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Method method = ((MethodSignature) signature).getMethod();
        Annotation[][] annotationMatrix = method.getParameterAnnotations();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            Annotation[] annotations = annotationMatrix[i];
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(TenantId.class)) {
                    return Optional.of((String) joinPoint.getArgs()[i]);
                }
            }
        }
        return Optional.empty();
    }
}
