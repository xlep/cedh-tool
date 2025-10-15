# Copilot Instructions for cedh-tool

## Project Overview
This is a cEDH (Competitive Elder Dragon Highlander) tournament management tool consisting of:
- **Frontend**: React application (Node.js 18)
- **Backend**: Java Spring Boot application (Java 21)
- **Database**: PostgreSQL database managed via Docker Compose
- A web-based database UI available at http://localhost:8081

## Technology Stack

### Frontend
- **Language**: JavaScript (ES6+)
- **Framework**: React 19.1.0
- **Build Tool**: npm with Create React App (react-scripts 5.0.1)
- **Routing**: React Router DOM 7.6.0
- **HTTP Client**: Axios 1.9.0
- **Testing**: Jest, React Testing Library
- **Styling**: CSS
- **Deployment**: Nginx (in Docker)

### Backend
- **Language**: Java 21
- **Framework**: Spring Boot 3.5.0
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Testing**: JUnit 5, Spring Boot Test, AssertJ
- **Security**: Spring Security with BCrypt password encoding
- **API Documentation**: OpenAPI/Swagger

## Code Style and Formatting

### Backend (Java)

#### Required Standards
- Use **google-java-format** for all Java code
- Follow Google Java Style Guide conventions
- Use IntelliJ IDEA with the google-java-format plugin

#### Code Formatting Rules
- Indentation: 2 spaces (no tabs)
- Line length: 100 characters maximum
- Use consistent naming conventions:
  - Classes: PascalCase
  - Methods/Variables: camelCase
  - Constants: UPPER_SNAKE_CASE
  - Packages: lowercase

### Frontend (React/JavaScript)

#### Required Standards
- Follow Create React App conventions and ESLint rules
- Use functional components with hooks (not class components)
- Use modern ES6+ syntax

#### Code Formatting Rules
- Indentation: 2 spaces (no tabs)
- Use consistent naming conventions:
  - Components: PascalCase (e.g., `PlayerManager.jsx`)
  - Files: PascalCase for components, camelCase for utilities
  - Variables/Functions: camelCase
  - Constants: UPPER_SNAKE_CASE
- Prefer arrow functions for component definitions
- Use JSX file extension for React components

## Project Structure

```
cedh-tool/
├── cedh-tool-frontend/          # React frontend application
│   ├── public/                  # Static assets
│   ├── src/
│   │   ├── components/          # React components
│   │   │   ├── LoginPage.jsx
│   │   │   ├── PlayerScores.jsx
│   │   │   ├── Pod.jsx
│   │   │   └── ...
│   │   ├── api/                 # API client modules
│   │   │   ├── PodApi.js
│   │   │   ├── ScoreApi.js
│   │   │   ├── TournamentApi.js
│   │   │   └── UserApi.js
│   │   ├── hooks/               # Custom React hooks
│   │   ├── utils/               # Utility functions
│   │   ├── App.js               # Main app component
│   │   ├── PlayerManager.js     # Player management page
│   │   ├── TournamentManager.js # Tournament management page
│   │   └── Bracket.js           # Tournament bracket view
│   ├── package.json             # npm dependencies
│   ├── Dockerfile               # Frontend container config
│   └── .env                     # Environment variables
├── cedh-tool-backend/           # Spring Boot backend application
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
│   ├── pom.xml                      # Maven configuration
│   └── Dockerfile                   # Backend container config
├── cedh-tool-database/              # Database setup
│   └── resources/
│       ├── schema-prod.sql          # Database schema
│       └── init-db.sh               # Database initialization
├── docker-compose.yml               # Multi-container orchestration
└── build-and-deploy.sh              # Build and deployment script
```

## Development Setup

### Prerequisites
- Java 21
- Maven
- Node.js 18+ and npm
- Docker & Docker Compose
- IntelliJ IDEA (recommended for backend)

### Getting Started with Docker Compose (Recommended)
The easiest way to run the entire application stack:

1. Build and start all services:
   ```bash
   ./build-and-deploy.sh
   ```

This will:
- Build and start the PostgreSQL database on port 5432
- Build and start the Spring Boot backend on port 8080
- Build and start the React frontend on port 80
- Set up networking between all containers

### Development Mode (Individual Services)

#### Frontend Development
1. Navigate to frontend directory:
   ```bash
   cd cedh-tool-frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start development server:
   ```bash
   npm start
   ```
   - Runs on http://localhost:3000
   - Proxies API requests to http://localhost:8080

#### Backend Development
1. Start the database (using Docker Compose):
   ```bash
   docker compose up database
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
   - Runs on http://localhost:8080

### Testing

#### Backend Tests
- Run all tests: `./mvnw test`
- Test files follow Spring Boot conventions with `@SpringBootTest` annotation
- Use `@TestPropertySource(locations = "classpath:application-test.properties")` for test configuration
- Use `@Transactional` on test classes to rollback database changes after each test

#### Frontend Tests
- Run all tests: `npm test`
- Test files use React Testing Library and Jest
- Follow naming convention: `*.test.js` or `*.test.jsx`
- Tests should be colocated with components or in `__tests__` directories

## Coding Guidelines

### React/Frontend Patterns

#### Component Structure
- Use functional components with hooks (not class components)
- Organize components by feature or domain (e.g., player management, tournament management)
- Keep components focused and single-purpose
- Extract reusable logic into custom hooks

#### State Management
- Use React hooks (`useState`, `useEffect`, `useContext`) for state management
- Use `ResultsContext` for sharing tournament results across components
- Store authentication state in localStorage
- Use `useNavigate` for programmatic navigation

#### API Integration
- API client modules are organized by domain in the `src/api/` directory
- Use Axios for HTTP requests
- Handle authentication by including credentials in requests
- Use toast notifications for user feedback (react-toastify)

#### Routing
- Use React Router DOM for navigation
- Protected routes check `loggedIn` state
- Main routes:
  - `/login` - Login page
  - `/PlayerManager` - Player management
  - `/TournamentManager` - Tournament management
  - `/Bracket` - Tournament bracket view
  - `/PlayerScores` - Player scoring view

#### Styling
- Component-specific styles in corresponding CSS files
- Global styles in `App.css` and `index.css`
- Use CSS classes with descriptive names
- Maintain consistent spacing and layout patterns

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

#### Backend Tests (Java)
- Test classes should mirror the package structure of source code
- Use AssertJ for assertions: `assertThat(...)`
- Test methods should have descriptive names describing what they test
- Use `@Disabled` annotation for temporarily disabled tests
- Create test data using builder patterns or helper methods
- Clean up test data using `@Transactional` rollback

#### Frontend Tests (React)
- Test files should be named `ComponentName.test.jsx` or `ComponentName.test.js`
- Use React Testing Library for component testing
- Test user interactions and component behavior, not implementation details
- Use `@testing-library/user-event` for simulating user interactions
- Mock API calls using Jest mocks
- Test accessibility and screen reader compatibility when relevant

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

### Backend API
- RESTful endpoints under `/api/v1/`
- OpenAPI/Swagger documentation available
- Use HTTP status codes appropriately:
  - 200 OK for successful GET/PUT/PATCH
  - 201 Created for successful POST
  - 204 No Content for successful DELETE
  - 400 Bad Request for validation errors
  - 401 Unauthorized for authentication failures
  - 404 Not Found for missing resources

### Frontend API Integration
- API clients organized by domain in `src/api/` directory:
  - `UserApi.js` - User authentication
  - `TournamentApi.js` - Tournament operations
  - `PodApi.js` - Pod management
  - `ScoreApi.js` - Scoring operations
  - `TournamentPlayerApi.js` - Player-tournament relationships
- Base URL configured via proxy in development (`http://localhost:8080`)
- In production, backend accessible at the same origin (containerized setup)

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

## Docker and Deployment

### Container Architecture
- **Frontend Container**: Nginx serving React production build on port 80
- **Backend Container**: Spring Boot application on port 8080
- **Database Container**: PostgreSQL on port 5432
- All containers communicate via `cedh-tool-network` bridge network

### Environment Configuration
- Frontend environment variables in `.env` and `.env.production`
- Backend profiles: `dev`, `prod`, `test`
- Backend uses `SPRING_PROFILES_ACTIVE=prod` in Docker
- CORS configured via `ALLOWED_ORIGIN` environment variable

### Build Process
- Frontend: Multi-stage Docker build (Node.js build → Nginx production)
- Backend: Maven build with Spring Boot Docker layering
- Database: PostgreSQL with initialization script (`init-db.sh`)

## Notes
- The database schema is defined in `cedh-tool-database/resources/schema-prod.sql`
- Database initialization script: `cedh-tool-database/resources/init-db.sh`
- Database runs on port 5432 (PostgreSQL)
- Frontend runs on port 80 (production) or 3000 (development)
- Backend runs on port 8080
- Some backend test methods are disabled (`@Disabled`) - check if they need to remain disabled before enabling them
- The `build-and-deploy.sh` script handles the complete build and deployment process
