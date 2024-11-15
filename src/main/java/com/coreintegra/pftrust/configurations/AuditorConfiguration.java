package com.coreintegra.pftrust.configurations;

import com.coreintegra.pftrust.entities.administration.User;
import com.coreintegra.pftrust.security.AuditorResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditorConfiguration {

    @Bean
    public AuditorAware<User> auditorProvider() {
        return new AuditorResolver();
    }

}
