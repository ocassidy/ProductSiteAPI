package com.productsite.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@Profile("dev")
public class RequestLoggingConfig {
    private static final int CONTENT_CACHE_LIMIT = 500;

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setMaxPayloadLength(CONTENT_CACHE_LIMIT);
        return loggingFilter;
    }
}
