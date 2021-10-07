package org.flaad.dsm.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class DsmRequestInterceptor implements RequestInterceptor {

    @Autowired
    private DsmAuthenticationToken authDetails;

    @Override
    public void apply(RequestTemplate template) {
        // Add the _sid request parm to each request if it exists.
        if (authDetails.getAuthToken() != null) {
            template.query("_sid", authDetails.getAuthToken());
        }
    }

}
