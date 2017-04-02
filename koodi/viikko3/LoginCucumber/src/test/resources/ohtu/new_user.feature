Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation successful with correct username and password
    Given command new user is selected
    When  username "lol" and password "apua6666" are entered
    Then  system will respond with "new user registered"

  Scenario: creation fails with correct username and too short password
    Given command new user is selected
    When  username "lol" and password "6" are entered
    Then  system will respond with "new user not registered"

  Scenario: creation fails with correct username and password consisting of letters
    Given command new user is selected
    When  username "lol" and password "aaaaaa" are entered
    Then  system will respond with "new user not registered"

  Scenario: creation fails with too short username and valid password
    Given command new user is selected
    When  username "a" and password "haloo666" are entered
    Then  system will respond with "new user not registered"

  Scenario: creation fails with already taken username and valid password
    Given user "eero" with password "salainen1" is created
    And   command new user is selected
    When  username "eero" and password "haloo124" are entered
    Then  system will respond with "new user not registered"

  Scenario: can login with successfully generated account
    Given user "eero" with password "salainen1" is created
    And   command login is selected
    When  username "eero" and password "salainen1" are entered
    Then  system will respond with "logged in"

  Scenario: can not login with account that is not successfully created
    Given user "aa" with password "aa" is created
    And   command login is selected
    When  username "aa" and password "aa" are entered
    Then  system will respond with "wrong username or password"
