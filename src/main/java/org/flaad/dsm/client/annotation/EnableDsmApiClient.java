package org.flaad.dsm.client.annotation;

import org.flaad.dsm.client.config.DsmConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DsmConfiguration.class)
public @interface EnableDsmApiClient {}
