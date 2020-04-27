package org.sventetsky.logging.feign;

import static java.lang.String.valueOf;
import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.MILLIS;

import java.time.LocalDateTime;

public class RequestContextHolder {

    private static final ThreadLocal<RequestContext> contextHolder = new ThreadLocal<>();

    public static String getRequestId() {
        return getContext().getRequestId();
    }

    public static void setRequestId(String requestId) {
        getContext().setRequestId(requestId);
    }

    public static String getMethod() {
        return getContext().getMethod();
    }

    public static void setMethod(String method) {
        getContext().setMethod(method);
    }

    public static String getUrl() {
        return getContext().getUrl();
    }

    public static void setUrl(String url) {
        getContext().setUrl(url);
    }

    public static void setStartTime(LocalDateTime startTime) {
        getContext().setStartTime(startTime);
    }

    public static String calculateSpentTime() {
        LocalDateTime startTime = getContext().getStartTime();
        return valueOf(MILLIS.between(startTime, now()));
    }

    private static RequestContext getContext() {
        RequestContext context = contextHolder.get();
        if (context == null) {
            context = new RequestContext();
            contextHolder.set(context);
        }

        return context;
    }
}
