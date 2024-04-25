**Introduction**:
====================================
This test framework is designed to automate the testing of RESTful APIs using REST Assured, TestNG, and Maven. It provides a structured and scalable approach to API testing, allowing for easy test creation, execution, and reporting.

**Prerequisites**:
====================================
Ensure you have the following installed on your machine:

Java Development Kit (JDK) - Download
Maven - Download
IDE (Eclipse, IntelliJ, etc.) - Optional but recommended

**Installation**
====================================
Clone this repository to your local machine:

git clone https://github.com/yourusername/your-repository.git


Navigate to the project directory:

cd your-repository


Build the project using Maven:

mvn clean install


**Usage**
====================================
To run the tests, execute the following Maven command:

mvn test

You can also run specific tests or groups of tests using TestNG's XML suite file or test annotations.

**Tests**
====================================
The test cases are organized in the src/test/java directory. Each test class corresponds to a specific API endpoint or functionality.

**Reporting**
====================================
TestNG generates HTML reports by default. You can find the test reports in the target/surefire-reports directory after running the tests. Additionally, custom reporting plugins or integrations can be added as needed.
