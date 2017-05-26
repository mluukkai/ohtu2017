Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation successful with correct username and password
    Given new user is selected
    When valid username "devinfinite" and password "possujuna1" and confirmation "possujuna1" are given
    Then new user account is created

  Scenario: creation fails with too short username and valid password
    Given new user is selected
    When too short username "a" and valid password "kokkuta1l1" and confirmation "kokkuta1l1" are given
    Then user is not created and error "username should have at least 3 characters" is reported

  Scenario: creation fails with correct username and too short password
    Given new user is selected
    When valid username "tellus" and too short password "pall0" and confirmation "pall0" are given
    Then user is not created and error "password should have at least 8 characters" is reported

    Scenario: creation fails with correct username and password consisting of letters
        Given new user is selected
        When  valid username "tonttu" and letter only password "puupainen" and confirmation "puupainen" are given
        Then user is not created and error "password can not contain only letters" is reported 
