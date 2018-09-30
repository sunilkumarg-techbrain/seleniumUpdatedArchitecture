# SeleniumUpdatedArchitecture using testng 
testng data provider using selenium example for google search and return result 
download the project and run 
mvn clean install usign eclipse or in command line 

Features of this framework 
1. Contains updated professional Page object architecture including methods to read data from notepad and covnert into hashmaps.
Pages created using findby and initialised in the constructor using page factory. The methods and classes are fully commented
2. The test cases are labelled with test case number, project number, high level description. The story, description annotation are provided to enable easy identification of the story and description in allure report. 
3. The test data is provided in notepad and excel format with same name as test case name and placed in the same package structure as in the test. 
4. The test cases read the data from notepad and convert them into two dimensional hash maps which are individually read in the test case. This feature simplifies the passing of multiple parameters (>20) as a single hash map object and provides easy calling of test data values in the test case methods.
5. Package Base Page, super Base Page, Package base test and super base tests included and contain the methods to launch the web driver session and to logoff the web driver sessions. Other uitility methods inlcluding highlight, takescreenshot, get currentTime stamp are provided.
6. Allure report with test listener implementing iTestListener. the listener is inlcuded in the test using Lsitener annotation and if the test fails in a particular element action, the screenshot is taken and attached to the Allure report.
7. The test suite is provided with groups to execute the test classes based on groups
8. Selenium grid execution capability is provided. Simply set the remote = true in the suite, start the selenium hub and node and execute the test in grid and generate allure report. 
