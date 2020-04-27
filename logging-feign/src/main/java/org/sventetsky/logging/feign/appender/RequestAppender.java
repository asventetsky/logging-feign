package org.sventetsky.logging.feign.appender;

import feign.Request;

public interface RequestAppender {

    void append(Request request, StringBuilder message);
}
