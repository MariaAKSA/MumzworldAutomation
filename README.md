**Mumzworld Automation Testing**

This project is a Maven-based Java automation testing framework designed to test the Mumzworld e-commerce website. The automation scripts are built using Selenium WebDriver and TestNG.

**Project Overview**

This project includes automated test cases to:

Open the Mumzworld webstore
Search for a product
Add the product to the bag
Go to the shopping bag page
Increase the quantity of items in the bag
Proceed to checkout
Register a new user

**Prerequisites**

Before you begin, ensure you have met the following requirements:

Java Development Kit (JDK) 8 or higher
Maven
ChromeDriver (or other WebDriver implementations as needed)
Git (for version control)


**Setup**

**Clone the Repository**

git clone 

https://github.com/MariaAKSA/MumzworldAutomation.git


**Navigate to the Project Directory**

cd MumzworldAutomation


**Install Dependencies**

Run the following command to install the required dependencies:
mvn install

**Configure WebDriver**

Ensure that you have the appropriate WebDriver (e.g., ChromeDriver) in your system's PATH or specify its location in the project configuration.

**Running Tests**

To run the tests, use the following Maven command:
mvn test

Test results and reports will be generated in the target directory.

**Project Structure**

src/main/java/ - Contains the main source code.
src/test/java/ - Contains the test cases.
pom.xml - Maven configuration file with dependencies and plugins.
test-output/ - Contains TestNG reports.
