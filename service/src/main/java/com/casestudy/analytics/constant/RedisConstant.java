package com.casestudy.analytics.constant;

import com.casestudy.analytics.enums.InsightType;

import java.util.HashMap;
import java.util.Map;

public class RedisConstant {
    public static final String REDIS_KEY_PREFIX = "event-aggregation:";
    public static final String AD_CLICK_COUNT = REDIS_KEY_PREFIX + "ad-click-count:";
    public static final String AD_VIEW_COUNT = REDIS_KEY_PREFIX + "ad-view-count:";
    public static final String CLICK_TO_BASKET_COUNT = REDIS_KEY_PREFIX + "click-to-basket-count:";

    public static Map<InsightType, String> redisKeyMap = new HashMap<>() {{
        put(InsightType.AD_VIEW_COUNT, AD_VIEW_COUNT);
        put(InsightType.AD_CLICK_COUNT, AD_CLICK_COUNT);
        put(InsightType.CLICK_TO_BASKET, CLICK_TO_BASKET_COUNT);
    }};
}
