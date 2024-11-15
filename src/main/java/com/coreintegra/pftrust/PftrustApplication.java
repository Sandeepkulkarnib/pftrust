package com.coreintegra.pftrust;

import com.coreintegra.pftrust.services.storage.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class PftrustApplication {

    public static void main(String[] args) {
        SpringApplication.run(PftrustApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        File uploaded_files = new File("uploaded_files");
        if(!uploaded_files.exists()){
            boolean mkdir = uploaded_files.mkdir();
            if(mkdir){
                System.out.println("created files folder");
            }
        }
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(5);
        executor.setThreadNamePrefix("BACKGROUND-TASKS-");
        executor.initialize();
        return new DelegatingSecurityContextAsyncTaskExecutor(executor);
    }

    @Bean
    public JavaMailSender javaMailService() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("email-smtp.ap-south-1.amazonaws.com");
        javaMailSender.setPort(465);
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setUsername("AKIAQOZ6HYTDNU2AUPPC");
        javaMailSender.setPassword("BFiGP7TV3ai9XYWJhKBPX1Po0bQ23+Ri3jvUpvHeFtvG");

        return javaMailSender;

    }

    private Properties getMailProperties() {

        Properties properties = new Properties();

        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.ssl.enable","true");
        properties.setProperty("mail.test-connection","true");

        return properties;

    }

}
