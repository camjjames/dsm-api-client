# DSM API Client - Spring Boot Library

DSM API Client is a Spring Boot library for integration with Synology DSM (DiskStation Manager) APIs. The library provides authentication and surveillance station API access through Feign clients with comprehensive testing using WireMock.

Always reference these instructions first and fallback to search or bash commands only when you encounter unexpected information that does not match the info here.

## Working Effectively

### Prerequisites and Setup
- Ensure Java 17 is installed and available
- Maven 3.5.0+ is required (project uses Maven 3.9.11)
- This is a **library project** - it produces a JAR for consumption by other applications, not a runnable application

### Build and Test the Repository
- **NEVER CANCEL builds or tests** - First build downloads dependencies (75+ seconds), subsequent builds are faster. NEVER CANCEL. Set timeout to 2+ minutes.
- Full clean build with tests: `mvn clean package` -- takes 17 seconds (after dependencies cached). NEVER CANCEL. Set timeout to 2+ minutes.
- Compile only: `mvn clean compile` -- takes 5 seconds (after dependencies cached). NEVER CANCEL. Set timeout to 2+ minutes.
- Test only: `mvn test` -- takes 15 seconds, runs 15 tests. NEVER CANCEL. Set timeout to 2+ minutes.
- Package without tests: `mvn clean package -DskipTests` -- takes 6 seconds (after dependencies cached). NEVER CANCEL. Set timeout to 2+ minutes.

### Code Quality and Validation
- **ALWAYS run validation before committing**: `mvn clean package` (includes tests, JaCoCo coverage, enforcer rules)
- Checkstyle is configured but currently **skipped** with 335 violations - do not run `mvn checkstyle:check` as it will fail
- JaCoCo coverage is automatically generated during `mvn package` - reports are in `target/site/jacoco/`
- SonarCloud integration runs in CircleCI - check quality gates there

### Testing Framework
- Uses Spring Boot Test with WireMock for API mocking
- Tests use **fixed ports** (port 9561) which causes dirty context warnings - this is expected
- All test stubs are in `src/test/resources/stubs/` with JSON response files
- Test configuration in `src/test/resources/application-test.yml`
- Base test class: `org.flaad.dsm.client.DsmApiClient` provides WireMock setup methods

## Validation Scenarios

### Core Functionality Testing
After making changes, ALWAYS validate these scenarios by running the full test suite:
1. **Authentication flow**: AuthApiClientTest validates login with correct/incorrect credentials
2. **API info retrieval**: InfoApiClientTest validates DSM API discovery and metadata
3. **Surveillance info**: SurveillanceInfoApiClientTest validates surveillance station information
4. **Camera operations**: SurveillanceCameraInfoApiClientTest validates camera info retrieval

### Manual Validation Steps
1. Run `mvn clean package` and verify all 15 tests pass
2. Check `target/dsm-api-client-1.2.0.jar` is created successfully  
3. Verify JaCoCo coverage report is generated in `target/site/jacoco/jacoco.xml`
4. Review test output for any WireMock warnings (fixed port warnings are expected)

## Project Structure

### Key Source Directories
- `src/main/java/org/flaad/dsm/client/`
  - `client/` - Feign client interfaces (DsmClient, DsmAuthClient, DsmSurveillanceClient)
  - `config/` - Spring configuration and Feign decoders/interceptors
  - `model/` - Response DTOs (ApiInfo, AuthSessionToken, SurveillanceStationInfo, etc.)
  - `request/` - Request DTOs (AuthRequest, ApiInfoRequest, etc.)
  - `annotation/` - EnableDsmApiClient for auto-configuration

### Key Test Files
- `src/test/java/org/flaad/dsm/client/DsmApiClient.java` - Base test class with WireMock setup
- `src/test/java/org/flaad/dsm/client/*Test.java` - Test classes for each client
- `src/test/resources/stubs/` - JSON response stubs for WireMock
- `src/test/resources/application-test.yml` - Test configuration

### Build Artifacts
- `target/dsm-api-client-1.2.0.jar` - Main library JAR
- `target/site/jacoco/` - Code coverage reports
- `target/surefire-reports/` - Test execution reports

## CI/CD Pipeline

### CircleCI Configuration
- Located in `.circleci/config.yml`
- Uses `cimg/openjdk:17.0` Docker image
- Build command: `mvn -B -V -DskipTests clean package`
- Test command: `mvn org.jacoco:jacoco-maven-plugin:prepare-agent package org.jacoco:jacoco-maven-plugin:report`
- SonarCloud analysis runs automatically with quality gates

### Quality Gates
- SonarCloud integration with badges in README.md
- JaCoCo minimum coverage: 10% line coverage (configured in pom.xml)
- Maven enforcer requires Java 17+ and Maven 3.5.0+
- Checkstyle is configured but currently disabled (skip=true)

## Common Tasks

### Adding New API Endpoints
1. Create request DTO in `src/main/java/org/flaad/dsm/client/request/`
2. Create response model in `src/main/java/org/flaad/dsm/client/model/`
3. Add method to appropriate client interface in `src/main/java/org/flaad/dsm/client/client/`
4. Create test in `src/test/java/org/flaad/dsm/client/` 
5. Add JSON stub in `src/test/resources/stubs/`
6. **ALWAYS run `mvn clean package` to validate changes**

### Debugging Test Failures
1. Check WireMock stub files in `src/test/resources/stubs/`
2. Verify test configuration in `application-test.yml`
3. Review base test class setup methods in `DsmApiClient.java`
4. Run single test: `mvn test -Dtest=AuthApiClientTest`

### Dependencies and Libraries
- Spring Boot 3.0.2 with Spring Cloud OpenFeign for HTTP clients
- Jackson for JSON serialization/deserialization  
- Lombok for boilerplate code generation
- WireMock for testing HTTP endpoints
- Hamcrest and JUnit 5 for assertions

## Repository Information

### Root Directory Contents
```
.circleci/          - CircleCI build configuration
.mvn/wrapper/       - Maven wrapper files  
src/main/java/      - Library source code
src/test/java/      - Test source code
src/test/resources/ - Test configuration and stubs
target/             - Build output directory
pom.xml             - Maven project configuration
README.md           - Project overview with badges
lombok.config       - Lombok configuration
sonar-project.properties - SonarCloud configuration
```

### Maven Configuration Highlights
```xml
<!-- Key properties from pom.xml -->
<java.version>17</java.version>
<spring-boot.version>3.0.2</spring-boot.version>
<spring-cloud-openfeign.version>4.0.0</spring-cloud-openfeign.version>
<wiremock.version>2.27.2</wiremock.version>
<jacoco.minimum.coverage>0.10</jacoco.minimum.coverage>
```

Always build and test your changes with `mvn clean package` before committing to ensure all quality gates pass.