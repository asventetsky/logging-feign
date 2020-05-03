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
import static org.sventetsky.logging.feign.appender.Template.REQUEST_LINE_TEMPLATE;

import feign.Request;
import java.util.concurrent.atomic.AtomicInteger;

public class LineRequestAppender implements RequestAppender {

    private static final AtomicInteger counter = new AtomicInteger();

    @Override
    public void append(Request request, StringBuilder message) {
        prepareRequestContext(request);
        String requestLineMessage = getRequestLineMessage();
        message.append(requestLineMessage);
    }

    private String getRequestLineMessage() {
        return String.format(REQUEST_LINE_TEMPLATE, getRequestId(), getMethod(), getUrl());
    }

    private void prepareRequestContext(Request request) {
        setRequestId(valueOf(counter.incrementAndGet()));
        setMethod(request.httpMethod().name());
        setUrl(request.url());
        setStartTime(now());
    }
}
