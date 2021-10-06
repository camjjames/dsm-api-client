package org.flaad.dsm.client.config;

import org.flaad.dsm.client.client.DsmAuthClient;
import org.flaad.dsm.client.client.DsmClient;
import org.flaad.dsm.client.client.DsmSurveillanceClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = { DsmClient.class, DsmAuthClient.class, DsmSurveillanceClient.class})
public class DsmConfiguration {

    @Bean
    public DsmAuthenticationToken setupAuthDetails() {
        return new DsmAuthenticationToken();
    }

    @Bean
    public DsmApiInformation setupApiInfDetails() {
        return new DsmApiInformation();
    }

}
