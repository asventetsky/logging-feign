package org.sventetsky.logging.feign.appender;

import feign.Request;

public class PayloadRequestAppender implements RequestAppender {

    @Override
    public void append(Request request, StringBuilder message) {
        if (request.body() == null) {
            return;
        }
        String payloadMessage = getPayloadMessage(request);
        message.append(payloadMessage);
    }

    private String getPayloadMessage(Request request) {
        String payload = request.charset() != null ? new String(request.body(), request.charset()) : new String(request.body());
        return String.join(payload, "\n||||BODY:", "||||");
    }
}
