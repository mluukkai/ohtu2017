Feature: As a user I want to be able to set the counter to value zero

  Scenario: Reseting after one increment
    Given Counter is initialized
    When it is incremented
    And it is reseted
    Then the value should be 0

  Scenario: Reseting after incrementing with several values
    Given Counter is initialized
    When it is incremented by 5
    And it is reseted
    Then the value should be 0
