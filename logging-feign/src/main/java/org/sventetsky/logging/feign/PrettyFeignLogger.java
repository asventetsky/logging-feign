package org.sventetsky.logging.feign;

import org.sventetsky.logging.feign.appender.HeadersRequestAppender;
import org.sventetsky.logging.feign.appender.HeadersResponseAppender;
import org.sventetsky.logging.feign.appender.LineRequestAppender;
import org.sventetsky.logging.feign.appender.LineResponseAppender;
import org.sventetsky.logging.feign.appender.PayloadRequestAppender;
import org.sventetsky.logging.feign.appender.PayloadResponseAppender;
import org.sventetsky.logging.feign.appender.RequestAppender;
import org.sventetsky.logging.feign.appender.ResponseAppender;
import feign.Logger;
import feign.Request;
import feign.Response;

import java.io.IOException;

class PrettyFeignLogger extends Logger {

    private LoggingConfig loggingConfig;

    private RequestAppender lineRequestAppender = new LineRequestAppender();
    private RequestAppender headersRequestAppender = new HeadersRequestAppender();
    private RequestAppender payloadRequestAppender = new PayloadRequestAppender();

    private ResponseAppender lineResponseAppender = new LineResponseAppender();
    private ResponseAppender headersResponseAppender = new HeadersResponseAppender();
    private ResponseAppender payloadResponseAppender = new PayloadResponseAppender();

    PrettyFeignLogger(LoggingConfig loggingConfig) {
        this.loggingConfig = loggingConfig;
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        if (!loggingConfig.isLoggingEnabled()) {
            return;
        }
        StringBuilder message = new StringBuilder();
        lineRequestAppender.append(request, message);
        if (loggingConfig.isLogHeaders()) {
            headersRequestAppender.append(request, message);
        }
        if (loggingConfig.isLogPayload()) {
            payloadRequestAppender.append(request, message);
        }
        loggingConfig.getLogger().info(message.toString());
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        if (!loggingConfig.isLoggingEnabled()) {
            return response;
        }
        StringBuilder message = new StringBuilder();
        lineResponseAppender.append(response, message);
        if (loggingConfig.isLogHeaders()) {
            headersResponseAppender.append(response, message);
        }
        if (loggingConfig.isLogPayload()) {
            response = logResponse(response, message);
        }
        loggingConfig.getLogger().info(message.toString());
        return response;
    }

    private Response logResponse(Response response, StringBuilder message) throws IOException {
        ResponseWrapper responseWrapper = new ResponseWrapper(response);
        payloadResponseAppender.append(responseWrapper.getResponse(), message);
        return responseWrapper.getResponse();
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        // ignore default implementation of feign logger
    }
}
