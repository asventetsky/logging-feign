package org.sventetsky.logging.feign;

import feign.Response;
import feign.Util;
import java.io.IOException;

class ResponseWrapper {

    private Response response;
    private byte[] bodyData;

    ResponseWrapper(Response response) throws IOException {
        this.response = response;
        this.bodyData = Util.toByteArray(response.body().asInputStream());
    }

    Response getResponse() {
        return response.toBuilder()
                       .body(bodyData)
                       .build();
    }
}
