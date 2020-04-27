package org.sventetsky.logging.feign.appender;

import static org.sventetsky.logging.feign.RequestContextHolder.getMethod;
import static org.sventetsky.logging.feign.RequestContextHolder.getRequestId;
import static org.sventetsky.logging.feign.RequestContextHolder.getUrl;
import static org.sventetsky.logging.feign.RequestContextHolder.setMethod;
import static org.sventetsky.logging.feign.RequestContextHolder.setRequestId;
import static org.sventetsky.logging.feign.RequestContextHolder.setStartTime;
import static org.sventetsky.logging.feign.RequestContextHolder.setUrl;
import static java.lang.String.valueOf;
import static java.time.LocalDateTime.now;

import feign.Request;
import java.util.concurrent.atomic.AtomicInteger;

public class LineRequestAppender implements RequestAppender {

    private static final AtomicInteger counter = new AtomicInteger();

    @Override
    public void append(Request request, StringBuilder message) {
        prepareRequestContext(request);
        message.append("[RQ:")
               .append(getRequestId())
               .append("][")
               .append(getMethod())
               .append(']')
               .append(getUrl());
    }

    private void prepareRequestContext(Request request) {
        setRequestId(valueOf(counter.incrementAndGet()));
        setMethod(request.method());
        setUrl(request.url());
        setStartTime(now());
    }
}
