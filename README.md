Automation Testing - Sauce Demo Automation

--- Overview ---
1) Project Name:- Sauce Demo Automation Testing
2) Website:- https://www.saucedemo.com/v1/
3) Objective:-	Automate the testing of key functionalities on the sauce demo/Swag labs website
4) Tools / Tech Stack:- Java, Selenium WebDriver, TestNG, Apache POI
5) Data Source:-	Excel (.xlsx)

--- Environment Setup ---
1) OS:-	Windows 10
2) Browser:-	Chrome
3) Java Version:-	Java 8
4) Selenium Version:-	4.28.1
5) TestNG Version:-	7.11.0
6) IDE:-	Eclipse
7) Others:-	Apache POI (for Excel handling)

--- Automated test script for: ---
1) LoginTest 
2) ProductFilterTest
3) CartOperationTest
4) CheckoutTest

--- How to run tests ---
1) Clone the repository
2) Import the project into Eclipse as a Maven project
3) Install dependencies - mvn clean install
4) Directly run testng.xml from your IDE
   
--- Reports ---
1) Test reports are generated and stored in the test-output directory.
2) Screenshots are captured and stored in the screenshots folder.

--- Test Data ---
1) Excel file is used for data-driven tests are located in the testdata folder.
2) ExcelUtils.java handles the reading of Excel files.


