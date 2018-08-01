**How to run the tests (from terminal)**

- clone this repository
- cd into it
- execute *mvn test* to run default test suite

**To see test results run the command**

*brew install allure* (if you don't have allure in your System)

*allure serve allure-results* after test run or *allure serve target/surfire-reports*

**How to write the tests**

- Select test case from TestRail which is not covered by automated test yet
- Checkout from the branch to create branch for specified test-case, e.g: login_to_system_tc'
- After test completion, push that branch to the git
- Make pull request from pushed branch to the main branch and set reviewers, at least one
- After last review, please leave a comment and merge the changes
- Update in TestRail automation suite test-case, adding in test-case Expected Result checks which were implemented during writing test-case, e.g:
- If you had AssertTrue(homePage.isPageDisplayed()) then write in Expected result point: 'User must see homePage displayed' (These checks can be prepared in advance for those who will write the tests)
