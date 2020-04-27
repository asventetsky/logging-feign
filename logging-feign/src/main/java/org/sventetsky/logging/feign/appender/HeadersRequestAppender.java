package org.sventetsky.logging.feign.appender;

import feign.Request;

public class HeadersRequestAppender implements RequestAppender {

    @Override
    public void append(Request request, StringBuilder message) {
        String headersMessage = getHeadersMessage(request);
        message.append(headersMessage);
    }

    private String getHeadersMessage(Request request) {
        return String.join(request.headers().toString(), "\n||||HEADERS:", "||||");
    }
}
