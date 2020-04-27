package org.sventetsky.logging.feign;

import org.slf4j.Logger;

public interface LoggingConfig {

    Logger getLogger();

    boolean isLoggingEnabled();

    boolean isLogPayload();

    boolean isLogHeaders();
}
