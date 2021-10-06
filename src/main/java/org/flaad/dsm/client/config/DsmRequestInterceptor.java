package org.flaad.dsm.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class DsmRequestInterceptor implements RequestInterceptor {

    @Autowired
    private DsmAuthenticationToken authDetails;

    @Override
    public void apply(RequestTemplate template) {
        // TODO: Use the DsmApiInfo object to check over the request and ensure we using the right path,
        //  version within range and header is set for the json

        if (authDetails.getAuthToken() != null) {
            template.query("_sid", authDetails.getAuthToken());
        }
    }

}
