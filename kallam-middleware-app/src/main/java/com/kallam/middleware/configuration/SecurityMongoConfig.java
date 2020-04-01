package com.kallam.middleware.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.kallam.middleware.repositry.security",
mongoTemplateRef = "securityTemplate")
public class SecurityMongoConfig {

}
