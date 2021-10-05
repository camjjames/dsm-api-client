package org.flaad.dsm.client.config;

import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DsmApiClientConfiguration {

    @Autowired
    ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new DsmRequestInterceptor();
    }

    @Bean
    public Decoder feignDecoder() {
        return new DsmDecoder(new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters))));
    }
}