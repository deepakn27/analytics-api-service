package com.casestudy.analytics.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class MongoRepository {

    private final MongoTemplate mongoTemplate;

    /**
     * Finds all entities matching the given query.
     *
     * @param query the query to execute
     * @param classz the class type of the entities to find
     **/
    public <T> T findOne(Query query, Class<T> classz) {
        return mongoTemplate.findOne(query, classz);
    }

}
