package com.trunarrative.companysearch.client;

import com.trunarrative.companysearch.exception.TruApiException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class TruErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
            return new TruApiException("The TruNarrative API has return status " + response.status());
    }
}
