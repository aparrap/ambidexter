Feature: As an automation engineer I want to automate scenarios when updating the Free2Move App to a newer version so
  information related to connected providers or login status remain the same.

  Scenario Outline: You will need to create a test script that can select any date from a date picker. Since the date picker is implemented differently on Android and iOS (and even across Android SDK versions)
    Given The user checks that the app is open on Android
    When The user selects a date "<date>" on the Android device
    Then The date "<date>" is selected on the Android device
    Examples:
      | date             |
      | 12 June 2029     |
      | 10 January 1995  |
      | 31 December 2020 |
      | 3 February 2020  |