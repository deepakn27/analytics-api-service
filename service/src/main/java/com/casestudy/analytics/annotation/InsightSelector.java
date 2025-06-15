package com.casestudy.analytics.annotation;

import com.casestudy.analytics.enums.InsightType;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InsightSelector {
    InsightType value();
}