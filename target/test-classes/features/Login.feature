Feature: Login related scenarios
  Background:
    Given user is navigated to HRMS application
  @smoke @sprint1 @login1
  Scenario: Valid admin login
   #Given user is navigated to HRMS application
    When user enters valid admin username and password
    And user clicks on login button
    Then user is successfully logged in the application
    @employee @sprint1
    Scenario: Valid ess login
     #Given user is navigated to HRMS application
      When user enters valid ess username and password
      And user clicks on login button
      Then user is successfully logged in the application
      @invalid @sprint1
      Scenario: invalid admin login
       Given user is navigated to HRMS application
       When user enters invalid username and password
       And user clicks on login button
       Then error message is displayed
@negative
        Scenario Outline: negative login test
          When user enters "<username>" and "<password>" and verifying the "<error>" for the combination
          Examples:
            | username | password | error |
            |admin     |hggghhj   |Invalid credentials|
            |admin1     |Hum@nhrm123 |Invalid credentials|
            |           |Hum@nhrm123 |Username cannot be empty|
            |  admin    |             |Password cannot be empty|
