Feature: Title Component

  Background:
    Given I am logged in
    And I create a "Title Component" page using the "Blog Demo Site Content Page" template

  Scenario: Validate title value
    When I open the dialog for the "Title" component
    And I set text field with name "Title" to "Title Component Page Title"
    And I save the dialog
    And I switch to "Preview" mode
    Then I should see the Header Text is "Title Component Page Title"