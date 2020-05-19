Feature: YUNAR by Ambidexter Automation challenge

  Scenario Outline: You will need to create a test script that can select any date from a date picker. Since the date picker is implemented differently on Android and iOS (and even across Android SDK versions)
    Given The user checks that the app is open on iOS
    When The user selects a formatted date "<DoW>", "<hours>" "<minutes>" "<time>" on the iOS device
    Then The date "<DoW>", "<hours>" "<minutes>" "<time>" is selected on the iOS device
    Examples:
      | DoW         | hours | minutes | time |
      | Fri, May 15 | 9     | 59      | AM   |
      | Thu, May 21 | 11    | 02      | PM   |
      | Wed, May 20 | 1     | 15      | AM   |
      | Tue, May 19 | 5     | 09      | AM   |