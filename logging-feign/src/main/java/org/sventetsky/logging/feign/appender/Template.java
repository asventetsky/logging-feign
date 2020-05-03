package org.sventetsky.logging.feign.appender;

public final class Template {

    public static final String REQUEST_LINE_TEMPLATE = "[RQ:%s][%s]%s";
    public static final String RESPONSE_LINE_TEMPLATE = "[RS:%s][%s][%s]%s in %s ms";
    public static final String HEADERS_TEMPLATE = "\n||||HEADERS:%s||||";
    public static final String PAYLOAD_TEMPLATE = "\n||||BODY:%s||||";

    private Template() {
    }
}
