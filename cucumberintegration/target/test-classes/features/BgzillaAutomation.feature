Feature: To test the login scenario of BugZilla

  Scenario: To launch the BugZilla and verify the page title
    Given I open the BugZilla webpage at "http://localhost:90/bugzilla/"
    Then I clicked on the login button
    And I provided the username as "admin" and password as "password"
    When I click the login button again
    Then Page should be open wth title "Bugzilla – Main Page"