Feature: As an automation engineer I want to automate scenarios when updating the Free2Move App to a newer version so
  information related to connected providers or login status remain the same.

  @Working
  Scenario Outline: Check when a user that is logged in F2M app with email credentials and doesn't have connected providers, after updating the app, remains without connected providers.
    Given The user checks that the app is open on iOS
    When The user selects a date "<date>" on the iOS device
    Then The date is selected on the iOS device
    Examples:
      | date             |
      | 12 June 2023     |
      | 10 January 1995  |
      | 31 December 2020 |
      | 3 February 2020  |