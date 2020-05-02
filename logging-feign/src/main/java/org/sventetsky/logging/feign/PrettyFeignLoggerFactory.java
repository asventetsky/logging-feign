package org.sventetsky.logging.feign;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignLoggerFactory;

public class PrettyFeignLoggerFactory implements FeignLoggerFactory {

    private LoggingConfig loggingConfig;

    public PrettyFeignLoggerFactory(LoggingConfig loggingConfig) {
        this.loggingConfig = loggingConfig;
    }

    @Override
    public Logger create(Class<?> type) {
        return new PrettyFeignLogger(loggingConfig);
    }
}
