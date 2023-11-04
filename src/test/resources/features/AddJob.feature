Feature: adding new jobs in HRMS
  Background:
    When user enters valid admin username and password
    And user clicks on login button
    Then user is successfully logged in the application

  @addJob
    Scenario: user adds a new job
      * user clicks on the admin button
      * user clicks on the job
      * user clicks on Job Title
      * user clicks on the add button
      * user enters the job "Java Instructor" title
      * user enters job description "Teaches java"
    * user enters job note "Java programming note"
      * user clicks on the save button
      * verify data is properly stored in database