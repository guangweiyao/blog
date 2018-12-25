Feature: Login
  In order to edit site
  As a site administrator
  I want to log in

Scenario Outline: Login with valid credentials
  Given I have opened login page
  And I am not logged in
  When I enter following credentials defined by properties "<username>", "<password>"
  And I press login button
  Then I should see welcome page

  Examples:
    | username       | password          |
    | author.login   | author.password   |

Scenario: Fail to login with invalid credentials
  Given I have opened login page
  And I am not logged in
  When I enter following credentials "invalid", "user"
  And I press login button
  Then Authorization error message should appear