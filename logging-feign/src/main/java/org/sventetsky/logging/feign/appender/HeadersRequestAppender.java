package org.sventetsky.logging.feign.appender;

import feign.Request;

import static org.sventetsky.logging.feign.appender.Template.HEADERS_TEMPLATE;

public class HeadersRequestAppender implements RequestAppender {

    @Override
    public void append(Request request, StringBuilder message) {
        String headersMessage = getHeadersMessage(request);
        message.append(headersMessage);
    }

    private String getHeadersMessage(Request request) {
        return String.format(HEADERS_TEMPLATE, request.headers().toString());
    }
}
