package org.sventetsky.logging.feign.appender;

import static org.sventetsky.logging.feign.RequestContextHolder.calculateSpentTime;
import static org.sventetsky.logging.feign.RequestContextHolder.getMethod;
import static org.sventetsky.logging.feign.RequestContextHolder.getRequestId;
import static org.sventetsky.logging.feign.RequestContextHolder.getUrl;
import static org.sventetsky.logging.feign.appender.Template.RESPONSE_LINE_TEMPLATE;

import feign.Response;
import java.io.IOException;

public class LineResponseAppender implements ResponseAppender {

    @Override
    public void append(Response response, StringBuilder message) throws IOException {
        String responseLineMessage = getResponseLineMessage(response);
        message.append(responseLineMessage);
    }

    private String getResponseLineMessage(Response response) {
        return String.format(
                RESPONSE_LINE_TEMPLATE,
                getRequestId(),
                getMethod(),
                response.status(),
                getUrl(),
                calculateSpentTime());
    }
}
