package org.sventetsky.logging.feign.appender;

import feign.Request;

import static java.lang.String.format;
import static org.sventetsky.logging.feign.appender.Template.PAYLOAD_TEMPLATE;

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
        return format(PAYLOAD_TEMPLATE, payload);
    }
}
