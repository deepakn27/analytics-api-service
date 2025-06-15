package com.casestudy.analytics.factory;

import com.casestudy.analytics.annotation.InsightSelector;
import com.casestudy.analytics.enums.InsightType;
import com.casestudy.analytics.strategy.InsightStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class to retrieve the appropriate InsightStrategy based on the InsightType.
 * It uses Spring's ApplicationContext to dynamically load beans annotated with @InsightSelector.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class InsightStrategyFactory {

    private final Map<InsightType, InsightStrategy<?>> insightStrategyMap = new HashMap<>();

    @Autowired
    public InsightStrategyFactory(ApplicationContext context) {
        log.info("Initializing InsightStrategyFactory");
        Map<String, Object> beans = context.getBeansWithAnnotation(InsightSelector.class);
        for (Object bean : beans.values()) {
            InsightSelector annotation = bean.getClass().getAnnotation(InsightSelector.class);
            insightStrategyMap.put(annotation.value(), (InsightStrategy<?>) bean);
            log.debug("Registered strategy: {} for insightType: {}", 
                    bean.getClass().getSimpleName(), annotation.value());
        }
        log.info("Initialized {} insight strategies", insightStrategyMap.size());
    }

    @SuppressWarnings("unchecked")
    public <T> InsightStrategy<T> getInsight(InsightType type) {
        log.debug("Retrieving strategy for insightType: {}", type);
        InsightStrategy<T> strategy = (InsightStrategy<T>) insightStrategyMap.get(type);
        if (strategy == null) {
            log.warn("No strategy found for insightType: {}", type);
        } else {
            log.debug("Found strategy: {} for insightType: {}", 
                    strategy.getClass().getSimpleName(), type);
        }
        return strategy;
    }
} 