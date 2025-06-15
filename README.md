# Analytics API Service

A Spring Boot-based analytics service that provides insights for advertising campaigns, including ad views, clicks, and click-to-basket conversions. The service implements caching with Redis and uses MongoDB for persistent storage.

## Features

- **Campaign Insights**: Track various metrics for advertising campaigns
  - Ad View Counts
  - Ad Click Counts
  - Click-to-Basket Conversions
- **Caching**: Redis-based caching for improved performance
- **Strategy Pattern**: Flexible implementation for different insight types
- **Exception Handling**: Comprehensive error handling with custom exceptions
- **Logging**: Detailed logging for monitoring and debugging

## Architecture

### Components

1. **Controllers**
   - `InsightsController`: REST endpoints for retrieving campaign insights

2. **Services**
   - `InsightsService`: Core service interface for retrieving insights
   - `InsightResponseConverter`: Converts cached values to response DTOs

3. **Repositories**
   - `InsightRepository`: Interface for data access
   - `RedisRepository`: Redis caching operations
   - `MongoRepository`: MongoDB data access

4. **Strategies**
   - `AdClickStrategy`: Handles ad click insights
   - `AdViewStrategy`: Handles ad view insights
   - `ClickToBasketStrategy`: Handles click-to-basket insights

5. **Aspects**
   - `CacheableInsightAspect`: AOP aspect for caching insights

### Design Patterns

- **Strategy Pattern**: For different insight types
- **Factory Pattern**: For strategy creation
- **Aspect-Oriented Programming**: For caching
- **Repository Pattern**: For data access

## API Endpoints

### Get Click-to-Basket Insights
```http
GET /ad/{campaignId}/clickToBasket
```

Response:
```json
{
    "campaignId": "string",
    "clickToBasketCount": "number"
}
```

## Error Handling

The service provides consistent error responses:

```json
{
    "timestamp": "2024-03-14T10:30:00",
    "message": "Error message",
    "error": "Error type",
    "path": "/api/path"
}
```

Error Types:
- `InsightNotFoundException`: When insights are not found
- `InvalidInsightTypeException`: When an invalid insight type is provided
- `InsightException`: Base exception for all insight-related errors

## Caching

- Redis is used for caching insights
- Cache keys are prefixed with `event-aggregation:`
- Cache timeout is set to 1 hour
- Cache keys are structured as: `{prefix}:{insight-type}:{campaign-id}`

## Logging

The service implements comprehensive logging:
- DEBUG: Detailed information for development
- INFO: Important business events
- WARN: Potentially harmful situations
- ERROR: Exceptional conditions

## Prerequisites

- Java 17 or higher
- MongoDB
- Redis
- Gradle

## Dependencies

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-redis'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-aop'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

## Building and Running

1. Clone the repository
2. Build the project:
   ```bash
   ./gradlew build
   ```
3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

## Testing

Run tests using:
```bash
./gradlew test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 