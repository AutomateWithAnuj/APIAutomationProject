## API Automation RestAssured (in Java)
## Author :- Anuj Rajput

### API Automation Framework - Restful Booker (CRUD Operations)

This framework automates **CRUD (Create, Read, Update, Delete)** operations on the [Restful Booker API](https://restful-booker.herokuapp.com/).  
It is built with a **hybrid framework design** for scalability, reusability, and CI/CD integration.

---

## 📦 Tech Stack
- **Java (JDK > 22)**
- **Rest Assured**
- **TestNG**
- **Maven**
- **Apache POI** (for external test data handling)
- **AssertJ** (advanced assertions)
- **Jackson API & Gson** (payload serialization & deserialization)
- **Log4j** (logging)
- **Allure Report** (reporting)
- **Git, GitHub, Jenkins** (version control + CI/CD)

---

## 📂 Project Structure (Hybrid Framework)
|-- src
| |-- main
| | |-- java
| | |-- base/ # Base setup classes
| | |-- config/ # Configuration management
| | |-- utils/ # Utility classes (Excel, JSON, etc.)
| | |-- payloads/ # Payload builders (Jackson + Gson)
| |
| |-- test
| |-- java
| |-- tests/ # Test classes for CRUD operations
| |-- integration/ # Integration test flows
|
|-- testng.xml # TestNG suite
|-- pom.xml # Maven dependencies & plugins
|-- Jenkinsfile # CI/CD pipeline configuration
|-- README.md # Project documentation

yaml

---

## 🚀 Running the Tests

### 1️⃣ Run Basic Suite
```bash
mvn clean test -DsuiteXmlFile=testng.xml
2️⃣ Integration Tests (Create, Token, Update, Delete Booking)
bash
mvn clean test -DsuiteXmlFile=testng-integration.xml
3️⃣ Parallel Execution
To run in parallel, update testng.xml:

xml

<suite name="All Test Suite" parallel="methods" thread-count="2">
⚙️ Maven Surefire Configuration
Add the following to your pom.xml under <build>:

xml
<plugins>
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.3.0</version>
    <configuration>
      <suiteXmlFiles>
        <suiteXmlFile>${suiteXmlFile}</suiteXmlFile>
      </suiteXmlFiles>
    </configuration>
  </plugin>
</plugins>
📊 Allure Report Setup
Install Allure (Mac)
bash
brew install allure
For other OS, see Allure Docs.

Run Tests & Generate Report
bash
mvn clean test
allure serve target/allure-results/
This opens the report in a browser with test results, screenshots, and logs.

🧪 Example Test Cases (Restful Booker CRUD)
Create a Booking, Update booking name, Get booking by ID and verify.

Create & Delete Booking, then verify deletion with GET.

Get an Existing Booking, Update it, and verify with GET.

Invalid Creation (wrong payload/JSON).

Update Deleted ID (negative scenario).

Validate Response:

Status Code

Headers

Body fields

📜 How to Add New Test Cases
Create a new class in src/test/java/tests/.

Extend BaseTest for pre-configured setup.

Use RestAssured for requests and Jackson/Gson for payloads.

java
Booking booking = new Booking("John", "Doe", 120, true);
Response response = given()
                       .contentType("application/json")
                       .body(booking)
                       .when()
                       .post("/booking");
Add assertions with AssertJ:

java
assertThat(response.statusCode()).isEqualTo(200);
Use Log4j for logging test steps.

Add the test class to testng.xml or testng-integration.xml.

🔄 CI/CD Integration (Jenkins)
Configure a Jenkins pipeline with Jenkinsfile.

Steps:

Checkout code from GitHub

Run mvn clean test -DsuiteXmlFile=testng.xml

Publish Allure Reports

📌 Postman Assignment (Practice Reference)
Create collections for CRUD scenarios on Restful Booker with Auth.

Example:

Create a booking → Update booking → Verify with GET.

Create & Delete booking → Verify it doesn’t exist.

Invalid JSON payloads.

✅ Summary
This framework supports:

CRUD API Testing on Restful Booker

Hybrid design for scalability

CI/CD readiness with Jenkins

Advanced reporting with Allure

Parallel execution with TestNG

yaml
