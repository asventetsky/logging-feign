package org.sventetsky.logging.feign.appender;

import static org.sventetsky.logging.feign.RequestContextHolder.calculateSpentTime;
import static org.sventetsky.logging.feign.RequestContextHolder.getMethod;
import static org.sventetsky.logging.feign.RequestContextHolder.getRequestId;
import static org.sventetsky.logging.feign.RequestContextHolder.getUrl;

import feign.Response;
import java.io.IOException;

public class LineResponseAppender implements ResponseAppender {

    @Override
    public void append(Response response, StringBuilder message) throws IOException {
        message.append("[RS:")
               .append(getRequestId())
               .append("][")
               .append(getMethod())
               .append("][")
               .append(response.status())
               .append("]")
               .append(getUrl())
               .append(" in ")
               .append(calculateSpentTime())
               .append(" ms");
    }
}
