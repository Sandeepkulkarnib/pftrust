package com.coreintegra.pftrust.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.coreintegra.pftrust.entities.administration.User;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JWTUtil {

    private final Long accessTokenExpireTime;
    private final Long refreshTokenExpireTime;
    private final String issuer;

    public final Algorithm algorithm;

    public JWTUtil(    @Value("${jwt.secret-key}") String secret,
                       @Value("${jwt.expire-time.access-token}") Long accessTokenExpireTime,
                       @Value("${jwt.expire-time.refresh-token}") Long refreshTokenExpireTime,
                       @Value("${spring.application.name}") String issuer) {
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
        algorithm = Algorithm.HMAC256(secret.getBytes());
        this.issuer = issuer;
    }

    public String createAccessToken(User user, Tenant tenant){

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (24*60*60*1000)))
                .withIssuer(issuer)
                .withClaim("tenant", tenant.getTenantId())
                .sign(algorithm);

    }

    public String createRefreshToken(User user, Tenant tenant){

        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("tenant", tenant.getTenantId())
                .withExpiresAt(new Date(System.currentTimeMillis() + (2*24*60*60*1000)))
                .withIssuer(issuer)
                .sign(algorithm);

    }

    public String resolveToken(HttpServletRequest request) {

        String authenticationHeader = request.getHeader(AUTHORIZATION);

        if (authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
            return authenticationHeader.substring("Bearer ".length());
        }
        return null;
    }

    public DecodedJWT validateToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}
