package com.springsecurity.securtest.Security;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;


//просто передаємо кодове слово із пропертів

@Data
@Configuration
@ConfigurationProperties("security.jwt")
public class JWTProperties {
    private String secretKey;
    private Duration tokenDuration;
}