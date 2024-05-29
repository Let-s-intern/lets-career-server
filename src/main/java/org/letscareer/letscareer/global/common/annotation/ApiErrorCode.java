package org.letscareer.letscareer.global.common.annotation;

import org.letscareer.letscareer.global.common.entity.SwaggerEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorCode {
    SwaggerEnum[] value();
}
