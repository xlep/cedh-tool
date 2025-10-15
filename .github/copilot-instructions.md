# Copilot Instructions for cedh-tool

## Project Overview
This is a cEDH (Competitive Elder Dragon Highlander) tournament management tool consisting of:
- **Backend**: Java Spring Boot application (Java 21)
- **Database**: PostgreSQL database managed via Docker Compose
- A web-based database UI available at http://localhost:8081

## Technology Stack
- **Language**: Java 21
- **Framework**: Spring Boot 3.5.0
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Testing**: JUnit 5, Spring Boot Test, AssertJ
- **Security**: Spring Security with BCrypt password encoding
- **API Documentation**: OpenAPI/Swagger

## Code Style and Formatting

### Required Standards
- Use **google-java-format** for all Java code
- Follow Google Java Style Guide conventions
- Use IntelliJ IDEA with the google-java-format plugin

### Code Formatting Rules
- Indentation: 2 spaces (no tabs)
- Line length: 100 characters maximum
- Use consistent naming conventions:
  - Classes: PascalCase
  - Methods/Variables: camelCase
  - Constants: UPPER_SNAKE_CASE
  - Packages: lowercase

## Project Structure

```
cedh-tool/
├── cedh-tool-backend/       # Spring Boot backend application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/de/balloncon/cedh_tool_backend/
│   │   │   │   ├── config/          # Configuration classes
│   │   │   │   ├── player/          # Player domain
│   │   │   │   ├── tournament/      # Tournament domain
│   │   │   │   ├── pod/             # Pod (game group) domain
│   │   │   │   ├── seat/            # Seat assignment domain
│   │   │   │   ├── score/           # Scoring logic
│   │   │   │   ├── security/        # Security configuration
│   │   │   │   └── util/            # Utility classes
│   │   │   └── resources/           # Application properties
│   │   └── test/                    # Test classes
│   └── pom.xml                      # Maven configuration
└── cedh-tool-database/              # Database setup
    └── resources/
        └── cedh-tool.sql            # Database schema
```

## Development Setup

### Prerequisites
- Java 21
- Maven
- Docker & Docker Compose
- IntelliJ IDEA (recommended)

### Getting Started
1. Start the database:
   ```bash
   docker compose up
   ```
2. Build the backend:
   ```bash
   cd cedh-tool-backend
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Testing
- Run all tests: `./mvnw test`
- Test files follow Spring Boot conventions with `@SpringBootTest` annotation
- Use `@TestPropertySource(locations = "classpath:application-test.properties")` for test configuration
- Use `@Transactional` on test classes to rollback database changes after each test

## Coding Guidelines

### Spring Boot Patterns
- Use `@Component`, `@Service`, `@Repository` annotations appropriately
- Dependency injection via constructor injection (preferred) or `@Autowired`
- REST controllers should use `@RestController` and follow RESTful conventions
- Use Spring Data JPA repositories extending `JpaRepository<Entity, ID>`
- Custom queries use `@Query` annotation with JPQL

### Repository Patterns
- Repository interfaces extend `JpaRepository<Entity, UUID>`
- Use UUID as primary key type for entities
- Custom queries use `@Query` with JPQL syntax
- Use `@Param` for named parameters in queries
- Prefer fetching strategies: `LEFT JOIN FETCH` for eager loading when needed

### Service Layer
- Service classes should be annotated with `@Service` or `@Component`
- Keep business logic in service layer, not in controllers or repositories
- Use `BigDecimal` for monetary/score calculations
- Constants should be declared as `public static final` in appropriate service classes

### Security
- Authentication uses Spring Security with HTTP Basic Auth
- Passwords are encrypted using BCryptPasswordEncoder
- CORS is configured to allow specific origins
- Security configuration is in `SecurityConfig.java`

### Testing Conventions
- Test classes should mirror the package structure of source code
- Use AssertJ for assertions: `assertThat(...)`
- Test methods should have descriptive names describing what they test
- Use `@Disabled` annotation for temporarily disabled tests
- Create test data using builder patterns or helper methods
- Clean up test data using `@Transactional` rollback

### Database Operations
- Use JPA entities with proper annotations: `@Entity`, `@Table`, `@Column`
- Relationships: `@ManyToOne`, `@OneToMany`, `@ManyToMany` with appropriate fetch strategies
- Use `@Transactional` for operations that modify data
- Avoid N+1 queries by using JOIN FETCH in queries

## Common Patterns in This Project

### Scoring System
- Uses Hareruya scoring algorithm
- Starting score: 1000.00
- Wager amount: 0.07
- Seat multipliers vary by position (1-4)
- All calculations use `BigDecimal` for precision

### Tournament Management
- Tournaments consist of multiple rounds
- Each round has pods (groups of 4 players)
- Seats are assigned within pods
- Scores are calculated based on pod results

### Utility Classes
- `ShuffleUtil`: Uses `SecureRandom` for secure randomization
- Random seat assignments and list shuffling

## API Design
- RESTful endpoints under `/api/v1/`
- OpenAPI/Swagger documentation available
- Use HTTP status codes appropriately:
  - 200 OK for successful GET/PUT/PATCH
  - 201 Created for successful POST
  - 204 No Content for successful DELETE
  - 400 Bad Request for validation errors
  - 401 Unauthorized for authentication failures
  - 404 Not Found for missing resources

## Error Handling
- Use Spring's exception handling mechanisms
- Return appropriate HTTP status codes
- Provide meaningful error messages

## When Making Changes
1. **Preserve existing functionality**: Don't break working code
2. **Follow existing patterns**: Match the style of surrounding code
3. **Add tests**: New functionality should have corresponding tests
4. **Update documentation**: Keep README and comments up to date
5. **Use consistent formatting**: Apply google-java-format to all changes
6. **Minimal changes**: Make the smallest change that solves the problem

## Notes
- The database schema is defined in `cedh-tool-database/resources/cedh-tool.sql`
- Database runs on port 5432 (PostgreSQL)
- Database UI (Adminer) runs on port 8081
- Application profiles: `dev`, `prod`, `test`
- Some test methods are disabled (`@Disabled`) - check if they need to remain disabled before enabling them
