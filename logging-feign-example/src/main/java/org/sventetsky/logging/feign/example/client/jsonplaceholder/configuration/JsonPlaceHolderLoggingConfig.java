package org.sventetsky.logging.feign.example.client.jsonplaceholder.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sventetsky.logging.feign.LoggingConfig;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.JsonPlaceHolderClient;

public class JsonPlaceHolderLoggingConfig implements LoggingConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(JsonPlaceHolderClient.class);

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
        return true;
    }
}
