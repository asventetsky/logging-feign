package org.sventetsky.logging.feign;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignLoggerFactory;

/**
 * Usage:
 * 1. create @Bean which implement LoggingConfig interface
 * 2. create @Bean OtsFeignLoggerFactory which accepts config created in first paragraph
 */
public class OtsFeignLoggerFactory implements FeignLoggerFactory {

    private LoggingConfig loggingConfig;

    public OtsFeignLoggerFactory(LoggingConfig loggingConfig) {
        this.loggingConfig = loggingConfig;
    }

    @Override
    public Logger create(Class<?> type) {
        return new OtsFeignLogger(loggingConfig);
    }
}
