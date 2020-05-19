Feature: As an automation engineer I want to automate scenarios when updating the Free2Move App to a newer version so
  information related to connected providers or login status remain the same.

  @Working
  Scenario Outline: Check when a user that is logged in F2M app with email credentials and doesn't have connected providers, after updating the app, remains without connected providers.
    Given The user checks that the app is open on iOS
    When The user selects a formatted date "<DoW>", "<hours>" "<minutes>" "<time>" on the iOS device
    Then The date "<DoW>", "<hours>" "<minutes>" "<time>" is selected on the iOS device
    Examples:
      | DoW         | hours | minutes | time |
      | Fri, May 15 | 9     | 59      | AM   |
      | Thu, May 21 | 11    | 02      | PM   |
      | Wed, May 20 | 1     | 15      | AM   |
      | Tue, May 19 | 5     | 09      | AM   |