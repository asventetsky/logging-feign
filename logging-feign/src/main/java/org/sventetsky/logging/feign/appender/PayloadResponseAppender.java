package org.sventetsky.logging.feign.appender;

import static feign.Util.UTF_8;
import static feign.Util.decodeOrDefault;
import static java.lang.String.format;
import static org.sventetsky.logging.feign.appender.Template.PAYLOAD_TEMPLATE;

import feign.Response;
import feign.Util;
import java.io.IOException;

public class PayloadResponseAppender implements ResponseAppender {

    @Override
    public void append(Response response, StringBuilder message) throws IOException {
        if (response.body() == null) {
            return;
        }
        String payloadMessage = getPayloadMessage(response);
        message.append(payloadMessage);
    }

    private String getPayloadMessage(Response response) throws IOException {
        byte[] bodyData = Util.toByteArray(response.body().asInputStream());
        String payload = decodeOrDefault(bodyData, UTF_8, "Binary data");
        return format(PAYLOAD_TEMPLATE, payload);
    }
}
