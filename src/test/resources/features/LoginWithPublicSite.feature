@login
Feature: Users should be able to login

  @login_with_credential_on_public_site
  Scenario Outline: Users should have login with credential on mobileaction.com public site
    Given the user navigate to mobileaction.co
    When the user clicks on login on the main page
    Then the "<user>" login with public site
    Then mobileaction dashboard page is shown
    Examples:
      | user                                 |
      | umit.sahin+testuser1@mobileaction.co |