@end2end @smoke
Feature: End to End Tests

  @get_visibility_score_of_app
  Scenario Outline: Users should have login with credential on mobileaction.com public site
    Given the user navigate to mobileaction.co
    When the user clicks on login on the main page
    Then the "<user>" login with public site
    Then mobileaction dashboard page is shown
    When the user search an "<app>" by id or name
    Then visibility score is shown on the app profile page
    Examples:
      | user                       | app                          |
      | umit.sahin@mobileaction.co | 418870703                    |
      | umit.sahin@mobileaction.co | Florist Now- Flowers & Gifts |