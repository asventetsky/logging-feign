package org.sventetsky.logging.feign.appender;

import feign.Response;
import java.io.IOException;

public class HeadersResponseAppender implements ResponseAppender {

    @Override
    public void append(Response response, StringBuilder message) throws IOException {
        String headersMessage = getHeadersMessage(response);
        message.append(headersMessage);
    }

    private String getHeadersMessage(Response response) {
        return String.join(response.headers().toString(), "\n||||HEADERS:", "||||");
    }
}
