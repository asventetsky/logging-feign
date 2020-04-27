package org.sventetsky.logging.feign.appender;

import feign.Response;
import java.io.IOException;

public interface ResponseAppender {

    void append(Response response, StringBuilder message) throws IOException;
}
