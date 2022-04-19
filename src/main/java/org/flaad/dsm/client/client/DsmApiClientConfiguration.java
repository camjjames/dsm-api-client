package org.flaad.dsm.client.client;

import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.flaad.dsm.client.config.DsmDecoder;
import org.flaad.dsm.client.config.DsmRequestInterceptor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DsmApiClientConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new DsmRequestInterceptor();
    }

    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters, ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return new DsmDecoder(new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(messageConverters, customizers))));
    }
}