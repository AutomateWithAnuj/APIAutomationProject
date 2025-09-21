# 🚀 API Automation Framework - RestAssured
## 👨‍💻 Author: Anuj Rajput

### 🎯 Restful Booker API Automation (CRUD Operations)

A **production-ready hybrid framework** for automating CRUD operations on [Restful Booker API](https://restful-booker.herokuapp.com/) with comprehensive test coverage, advanced payload management, and detailed reporting.

---

## 📦 Tech Stack
- **Java 22+** - Core programming language
- **RestAssured 5.5.6** - API testing library
- **TestNG 7.11.0** - Test execution framework
- **Maven** - Build & dependency management
- **Gson 2.13.2** - JSON serialization/deserialization
- **JavaFaker 1.0.2** - Random test data generation
- **AssertJ 4.0.0** - Advanced assertions
- **Allure Reports 2.30.0** - Test reporting
- **Apache POI 5.4.1** - Excel data handling
- **Log4j 3.0.0** - Logging framework

---

## 📂 Framework Architecture
```
APIAutomationProject/
├── src/
│   ├── main/java/com/AutomateWithAnuj/
│   │   ├── endpoints/
│   │   │   └── APIConstants.java          # API endpoints constants
│   │   ├── modules/
│   │   │   └── PayloadManager.java        # Payload creation & management
│   │   └── Pojos/
│   │       ├── Request/                   # Request POJOs
│   │       │   ├── Auth.java
│   │       │   ├── Booking.java
│   │       │   └── Bookingdates.java
│   │       └── Response/                  # Response POJOs
│   │           ├── BookingResponse.java
│   │           └── TokenResponse.java
│   └── test/java/com/AutomateWithAnuj/
│       ├── Base/
│       │   └── BaseTest.java              # Test base setup
│       ├── Asserts/
│       │   └── AssertActions.java         # Custom assertions
│       ├── tests/
│       │   ├── crud/                      # CRUD test cases
│       │   │   ├── TestCreateBooking.java
│       │   │   ├── TestCreateToken.java
│       │   │   └── TestHealthCheck.java
│       │   └── e2e_Integrations/          # End-to-end flows
│       │       ├── TestIntegrationFlow1.java
│       │       ├── TestIntegrationFlow2.java
│       │       └── TestIntegrationSample.java
│       └── utils/                         # Test utilities
├── target/
│   ├── allure-results/                    # Allure test results
│   └── surefire-reports/                  # TestNG reports
├── testng.xml                             # TestNG suite configuration
├── testng_e2eIntegeration1.xml           # Integration test suite
└── pom.xml                                # Maven configuration
```

---

## ⭐ Key Features

### 🔧 PayloadManager Capabilities
- **Multiple Payload Types**: Static, Chinese characters, Random data with Faker
- **Serialization/Deserialization**: Java objects ↔ JSON conversion
- **Authentication**: Token generation and management
- **Data Validation**: Response parsing and verification

### 💡 Test Data Management
```java
// Static payload
payloadManager.createPayloadBookingAsString()

// Random data with Faker
payloadManager.createPayloadBookingAsString_Random_FakerJS()

// Chinese character support
payloadManager.createPayloadBookingAsStringChinese()

// Authentication
payloadManager.setAuthPayload()
```

### 🎲 Advanced Test Scenarios
- **Positive Testing**: Valid CRUD operations
- **Negative Testing**: Invalid payloads, error handling
- **Data Variations**: Unicode characters, edge cases
- **Integration Flows**: End-to-end booking lifecycle

---

## 🚀 Running Tests

### Basic Execution
```bash
# Run all tests
mvn clean test

# Run specific suite
mvn clean test -DsuiteXmlFile=testng.xml

# Run integration tests
mvn clean test -DsuiteXmlFile=testng_e2eIntegeration1.xml

# Parallel execution
mvn clean test -Dparallel=methods -DthreadCount=3
```

### 📊 Allure Reports
```bash
# Generate reports
mvn clean test
allure serve target/allure-results/

# For Windows (if Allure installed)
allure generate target/allure-results/ --clean
allure open
```

---

## 🧪 Test Coverage

### ✅ CRUD Operations
- **CREATE**: Booking creation with various payloads
- **READ**: Get booking by ID and verify details  
- **UPDATE**: Modify booking and validate changes
- **DELETE**: Remove booking and confirm deletion

### 🔐 Authentication Tests
- Token generation and validation
- Auth payload creation and verification
- Token extraction from responses

### 🌐 Data Variations
- **Static Data**: Predefined test data
- **Random Data**: Faker-generated dynamic data
- **Unicode Support**: Chinese character testing
- **Edge Cases**: Empty payloads, invalid JSON

### 📝 Test Examples
```java
@Test
public void testCreateBookingPositive() {
    requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
    response = RestAssured.given(requestSpecification)
        .when()
        .body(payloadManager.createPayloadBookingAsString())
        .post();
    
    BookingResponse bookingResponse = (BookingResponse) 
        payloadManager.createPayloadBookingAsObject(response.asString());
    
    assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Anuj");
}
```

---

## 🔄 CI/CD Integration

### Maven Surefire Configuration
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.1.2</version>
    <configuration>
        <suiteXmlFiles>
            <suiteXmlFile>testng.xml</suiteXmlFile>
        </suiteXmlFiles>
    </configuration>
</plugin>
```

### Jenkins Pipeline
```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
}
```

---

## 📋 Adding New Tests

1. **Create Test Class**: Extend `BaseTest` for setup
2. **Use PayloadManager**: For request/response handling
3. **Add Assertions**: Use `AssertActions` for validations
4. **Configure Suite**: Add to `testng.xml`

```java
public class TestNewFeature extends BaseTest {
    @Test
    @Description("Test new booking feature")
    public void testNewFeature() {
        // Test implementation
    }
}
```

---

## 🏆 Framework Benefits

- ✅ **Hybrid Architecture**: Scalable and maintainable design
- ✅ **Comprehensive Coverage**: CRUD + Integration + Negative testing
- ✅ **Advanced Reporting**: Allure reports with detailed insights
- ✅ **Data Flexibility**: Static, random, and Unicode data support
- ✅ **CI/CD Ready**: Maven + Jenkins integration
- ✅ **Parallel Execution**: TestNG parallel test execution
- ✅ **Best Practices**: Clean code with proper separation of concerns

---

## 📞 Contact & Support
**Framework Developer**: Anuj Rajput  
**Project**: API Automation Framework  
**Version**: 1.0-SNAPSHOT

---

*Built with ❤️ for robust API automation testing*