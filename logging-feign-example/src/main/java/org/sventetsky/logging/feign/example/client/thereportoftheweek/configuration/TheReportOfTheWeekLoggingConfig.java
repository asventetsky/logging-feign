package org.sventetsky.logging.feign.example.client.thereportoftheweek.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sventetsky.logging.feign.LoggingConfig;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.JsonPlaceHolderClient;
import org.sventetsky.logging.feign.example.client.thereportoftheweek.TheReportOfTheWeekClient;

public class TheReportOfTheWeekLoggingConfig implements LoggingConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(TheReportOfTheWeekClient.class);

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public boolean isLoggingEnabled() {
        return true;
    }

    @Override
    public boolean isLogPayload() {
        return true;
    }

    @Override
    public boolean isLogHeaders() {
        return false;
    }
}
