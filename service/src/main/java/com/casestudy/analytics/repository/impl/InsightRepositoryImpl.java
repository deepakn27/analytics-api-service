package com.casestudy.analytics.repository.impl;

import com.casestudy.analytics.repository.InsightRepository;
import com.casestudy.analytics.repository.MongoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@AllArgsConstructor
public class InsightRepositoryImpl implements InsightRepository {

    private final MongoRepository mongoRepository;

    @Override
    public <T> T getInsights(String campaignId, Class<T> insightType) {
        log.debug("Fetching insights from MongoDB for campaignId: {} and type: {}", 
                campaignId, insightType.getSimpleName());
        
        Query query = new Query(Criteria.where("campaignId").is(campaignId));
        T result = mongoRepository.findOne(query, insightType);
        
        if (result == null) {
            log.info("No insights found in MongoDB for campaignId: {} and type: {}", 
                    campaignId, insightType.getSimpleName());
        } else {
            log.debug("Successfully retrieved insights from MongoDB for campaignId: {} and type: {}", 
                    campaignId, insightType.getSimpleName());
        }
        
        return result;
    }
}
