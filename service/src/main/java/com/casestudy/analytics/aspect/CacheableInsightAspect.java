package com.casestudy.analytics.aspect;

import com.casestudy.analytics.dto.response.InsightsResponse;
import com.casestudy.analytics.enums.InsightType;
import com.casestudy.analytics.repository.RedisRepository;
import com.casestudy.analytics.service.InsightResponseConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static com.casestudy.analytics.constant.RedisConstant.redisKeyMap;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class CacheableInsightAspect {

    private final RedisRepository<String, Object> redisRepository;
    private final InsightResponseConverter responseConverter;

    /**
     * Aspect to handle caching of insights responses based on the InsightType.
     * It checks if the response is available in Redis cache before proceeding with the method execution.
     *
     * @param joinPoint the join point representing the method execution
     * @return the cached response or the result of the method execution
     * @throws Throwable if an error occurs during method execution
     */
    @Around("@annotation(cacheableInsight)")
    public Object checkCache(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String campaignId = (String) args[0];
        InsightType insightType = (InsightType) args[1];

        String redisKey = buildRedisKey(campaignId, insightType);
        log.debug("Checking cache for key: {}", redisKey);
        
        Object cachedValue = redisRepository.get(redisKey);

        if (cachedValue != null) {
            log.info("Cache hit for campaignId: {} and insightType: {}", campaignId, insightType);
            return responseConverter.convert(cachedValue, insightType);
        }

        log.info("Cache miss for campaignId: {} and insightType: {}", campaignId, insightType);
        var result = joinPoint.proceed();
        if (result instanceof InsightsResponse insightsResponse) {
            Object countToCache = getCountToCache(insightsResponse, insightType);
            redisRepository.save(redisKey, countToCache);
            log.info("Cached insights for campaignId: {} and insightType: {} with count: {}", 
                    campaignId, insightType, countToCache);
        }
        return result;
    }

    /**
     * Builds the Redis key for caching based on the campaign ID and insight type.
     *
     * @param campaignId the ID of the campaign
     * @param insightType the type of insight
     * @return the constructed Redis key
     */
    private String buildRedisKey(String campaignId, InsightType insightType) {
        String key = redisKeyMap.get(insightType) + campaignId;
        log.debug("Built Redis key: {} for campaignId: {} and insightType: {}", key, campaignId, insightType);
        return key;
    }

    /**
     * Retrieves the count to cache based on the insight type from the InsightsResponse.
     *
     * @param response the InsightsResponse containing the counts
     * @param insightType the type of insight to retrieve the count for
     * @return the count to cache
     */
    private Object getCountToCache(InsightsResponse response, InsightType insightType) {
        Object count = switch (insightType) {
            case CLICK_TO_BASKET -> response.getClickToBasketCount();
            case AD_VIEW_COUNT -> response.getViewCount();
            case AD_CLICK_COUNT -> response.getClickCount();
        };
        log.debug("Retrieved count: {} for insightType: {}", count, insightType);
        return count;
    }
} 