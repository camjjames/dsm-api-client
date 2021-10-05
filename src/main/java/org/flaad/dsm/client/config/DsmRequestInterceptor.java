package org.flaad.dsm.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class DsmRequestInterceptor implements RequestInterceptor {

    @Autowired
    private DsmAuthenticationToken authDetails;

    @Override
    public void apply(RequestTemplate template) {
        if (authDetails.getAuthToken() != null) {
            template.query("_sid", authDetails.getAuthToken());
        }
    }

}
