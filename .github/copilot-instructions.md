# DSM API Client

DSM API Client is a Spring Boot library for integrating with Synology DSM (DiskStation Manager) APIs. The library provides Feign clients for authentication and surveillance station operations, with built-in WireMock testing support.

Always reference these instructions first and fallback to search or bash commands only when you encounter unexpected information that does not match the info here.

## Working Effectively

### Bootstrap and Build
Bootstrap, build, and test the repository:
- Ensure Java 17 is installed (required)
- Use the Maven wrapper: `./mvnw` (never use system `mvn` directly)
- `./mvnw clean compile` -- initial compile takes ~2 minutes. NEVER CANCEL. Set timeout to 180+ seconds.
- `./mvnw package` -- full build with tests takes ~15 seconds. NEVER CANCEL. Set timeout to 60+ seconds.
- `./mvnw test` -- test suite takes ~18 seconds. NEVER CANCEL. Set timeout to 60+ seconds.

### Core Commands
- Clean build: `./mvnw clean && ./mvnw package`
- Run tests only: `./mvnw test`
- Skip tests: `./mvnw package -DskipTests`
- Generate coverage report: `./mvnw jacoco:report` (after running tests)
- Checkstyle check: `./mvnw checkstyle:check` -- will fail with 335 violations but checkstyle is disabled in build (`<skip>true</skip>`)

### Build Artifacts
After successful build, artifacts are located in:
- JAR file: `target/dsm-api-client-1.2.0.jar`
- Test reports: `target/surefire-reports/`
- Coverage reports: `target/site/jacoco/index.html`
- Checkstyle reports: `target/checkstyle-result.xml`

## Validation

### Test Execution
- ALWAYS run the full test suite after making changes: `./mvnw test`
- All 15 tests must pass (8 test files testing API clients)
- Tests use WireMock to mock Synology DSM API endpoints
- Test data is in `src/test/resources/stubs/*.json`

### Library Integration Testing
Since this is a library (not a standalone application), validate integration by:
1. Build the library: `./mvnw clean package`
2. Verify the JAR contains expected classes: `jar -tf target/dsm-api-client-1.2.0.jar | grep DsmClient`
3. Check that main client interfaces are present:
   - `org.flaad.dsm.client.client.DsmClient` (API info operations)
   - `org.flaad.dsm.client.client.DsmAuthClient` (authentication)
   - `org.flaad.dsm.client.client.DsmSurveillanceClient` (surveillance operations)
4. Verify annotation is available: `jar -tf target/dsm-api-client-1.2.0.jar | grep EnableDsmApiClient`

### Code Quality Validation
- Run test coverage: `./mvnw test jacoco:report`
- Coverage threshold: minimum 10% line coverage (configured in pom.xml)
- View coverage report: open `target/site/jacoco/index.html` in browser
- Checkstyle: `./mvnw checkstyle:check` will fail but is skipped during build

## Common Tasks

### Repository Structure
```
dsm-api-client/
├── src/main/java/org/flaad/dsm/client/
│   ├── annotation/           # @EnableDsmApiClient annotation
│   ├── client/              # Feign clients (main API interfaces)
│   ├── config/              # Spring configuration and authentication
│   ├── model/               # API response models
│   └── request/             # API request models
├── src/test/java/org/flaad/dsm/client/
│   └── *Test.java           # 8 test classes using WireMock
├── src/test/resources/
│   ├── stubs/               # JSON mock responses for tests
│   └── application-test.yml # Test configuration
├── pom.xml                  # Maven build configuration
├── .circleci/config.yml     # CI/CD pipeline
└── mvnw                     # Maven wrapper
```

### Key Library Components
- **Main Entry Point**: `@EnableDsmApiClient` annotation to enable the library
- **API Clients**:
  - `DsmClient`: General DSM API operations (getApiInfo)
  - `DsmAuthClient`: Authentication operations (login)
  - `DsmSurveillanceClient`: Surveillance Station operations (camera info, snapshots)
- **Configuration**: `DsmConfiguration` auto-configures Feign clients
- **Models**: Request/response models for all API operations

### Integration Guidelines
Developers using this library should:
1. Add dependency in their `pom.xml`
2. Use `@EnableDsmApiClient` annotation on their main class
3. Configure `dsm.url` property pointing to their Synology NAS
4. Inject and use the client interfaces (DsmClient, DsmAuthClient, etc.)

### CI/CD Integration
- CircleCI pipeline runs on every commit
- Build command: `mvn -B -V -DskipTests clean package`
- Test command: `mvn org.jacoco:jacoco-maven-plugin:prepare-agent package org.jacoco:jacoco-maven-plugin:report`
- SonarCloud integration for code quality analysis
- Pipeline expects Java 17 environment

### Common Development Patterns
- All API clients are Spring Cloud OpenFeign interfaces
- Request models use Lombok builders: `AuthRequest.builder().account("user").passwd("pwd").build()`
- Response models wrap data: `DsmApiResponse<T>` with success/error handling
- Tests extend `DsmApiClient` base class for WireMock setup
- Mock responses are JSON files in `src/test/resources/stubs/`

### Troubleshooting
- **Permission denied on mvnw**: Run `chmod +x mvnw`
- **Java version issues**: Ensure Java 17 is installed and JAVA_HOME is set
- **Test failures**: Check if WireMock ports are available (configured for port 9561)
- **Build timeouts**: Increase timeout for initial builds (dependencies download)
- **Checkstyle failures**: Checkstyle check will fail but is disabled in the build process

### Performance Expectations
- Initial build (with dependency download): ~2 minutes
- Subsequent builds: ~15 seconds
- Test execution: ~18 seconds
- Clean builds: ~15 seconds
- Individual test classes: ~3-5 seconds each