====================
FRAMEWORK OVERVIEW
====================

Selenium 2 (Webdriver) Framework
----------------------------------

Setting up Selenium Automation for the first time or running it for separate web applications, you will require some bit of study
to get started. You can read up a lot of documentation, experiment with scripts, and repeat the process every time your
testing object changes. Or you can simply use our Selenium 2 (Webdriver) Framework.
This Selenium 2 (Webdriver) Framework is a set of guidelines, including coding standards, test-data handling, object repository
treatment etc., which when followed will not only save precious time but will also provide additional benefits including increased
code re-usage, higher portability, and reduced script maintenance cost.

Our Approach
-------------------------------------
For our framework we have used ‘Data-Driven Framework’ with Selenium 2 (Webdriver) and Java Programming
language.
A data-driven framework is where test input and output values are read from data files (ODBC sources, CVS files, Excel files
and DAO objects) and are loaded into variables in captured or manually coded scripts.
In this framework, variables are used for both input values and output verification values. Navigation through the program,
reading of the data files, and logging of test status and information are all coded in the test script.

Framework Features
--------------------------------------
•• Well defined architectural design
•• Less time to test large data
•• Script execution in multiple environments
•• Easier, faster and efficient analysis of result logs
•• Communication of results
•• Easy debugging and script maintenance
•• Robust and stable due to error and exception handling
•• 100% reliability of utility scripts, online execution, report packs

Architecture Explanation
---------------------------------------
Architecture forms the foundation of any software application. It should be robust enough to handle the desired functions
efficiently and effectively. In this approach, the goal is to develop an application-independent reusable Data-Driven Framework
that can be used directly across any application without spending any extra time on it.
In order to make all the components of the system work in sync, it is important to define the components and its
functionalities, as well as the binding relationship between them.

This Package Includes:

Config - Keeps all the configuration files such as property files
InputTestData - Has files containing input data for application
OutputData - Contains downloaded docs/images, fetched data in excel
TestReports - Contains ANT generated reports
Util - Utility package contains all generic functions & business functions such as email configuration setting
and all other utilities
TestLogs - Contains log file corresponding to tests
DAO - Classes for accessing persistent storage, such as to a database
Pages - Page classes for particular pages

Some amazing and distinct features
-------------------------------------------
Selenium 2 (Webdriver) Framework Version 3 has all new features, which will make testing a whole lot easier. Key new features
include:

1) Parameterization of Different Input Types:
In the earlier versions, we did parameterization only for textboxes. But in version 3.0 we have also implemented it for other
input types like radio button, drop downs, check boxes, auto-suggest search drop down, and file upload.

2) Auto-suggest Search Dropdown:
The common Webdriver code for selecting drop down is:

Select gender = new Select(driver.findElement(By.id(“gender”)));
gender.selectByVisibleText(“Female”);

This does not work for auto-suggest search dropdowns
Therefore, we have used following code in our framework for these types of dropdowns:

driver.findElement(locator).sendKeys(value);
driver.findElement(locator).sendKeys(Keys.TAB);

3) Image Comparison:
This feature helps you to take screenshots of images and compare them with sample images. In our code, we have added
3 sample images in the ‘InputTestData’ folder of the Project and compared them with the screenshots that are stored in the
‘OutputData’ folder.

4) Drag and Drop Functionality:
Drag and drop is a very common feature. It is when you “grab” an object and drag it to a different location. We have also
also added this functionality using the Actions class:

WebElement element = driver.findElement(By.name(“source”));
WebElement target = driver.findElement(By.name(“target”));
(new Actions(driver)).dragAndDrop(element, target).perform();

5) Sorting Values:
Data sort is a common functionality used in different websites especially Business to Consumer sites. You can use framework’s
sorting functionality to sort values and check if the sorted order is correct or not. In the test script – SortingTest.java,
we have checked sorting of table rows for three columns.

6) Auto-Focusing On New Tab:
Some links on the websites open in a new tab but when we click on them via automation script, a new window opens instead
of tab; and the automation script breaks. We have now added code in our framework for managing this new window
using webdriver’s “switchTo” method.

7) Check and Delete Cookies:
Webdriver has amazing feature of cookie handling that we have used in our framework to check and delete cookies
for a specific domain.
