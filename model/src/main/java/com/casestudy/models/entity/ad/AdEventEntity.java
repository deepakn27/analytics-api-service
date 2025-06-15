package com.casestudy.models.entity.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableMongoAuditing
@Document(collection="ad_events")
public class AdEventEntity {
    private String eventType;
    private String eventId;
    private String tenantId;
    private String userId;
    private String sessionId;
    private Advertisement advertisement;
}
