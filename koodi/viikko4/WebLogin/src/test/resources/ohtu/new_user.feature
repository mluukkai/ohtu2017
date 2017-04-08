Feature: A new user account can be created if a proper unused username and password are given

Scenario: creation successful with correct username and password
        Given register new user is selected
        When  valid username "otto" and valid password "ottootto5%" and matching confirmation "ottootto5%" are given
        Then  new user is registered

Scenario: creation fails with too short username and valid password
    Given register new user is selected
    When too short username "ot" and valid password "ottootto5%" and matching confirmation "ottootto5%" are given
    Then new user is not created and error "username should have at least 3 characters" is reported

Scenario: creation fails with correct username and too short password
    Given register new user is selected
    When valid username "otto" and too short password "otto%" and matching confirmation "otto%" are given
    Then new user is not created and error "password should have at least 8 characters" is reported   

Scenario: creation fails with correct username and password consisting of letters
    Given register new user is selected
    When valid username "otto" and only letters password "ottootto" and matching confirmation "ottootto" are given
    Then new user is not created and error "password can not contain only letters" is reported 

Scenario: creation fails with already taken username and valid pasword
    Given register new user is selected
    When already taken username "jukka" and valid password "ottootto%" and matching confirmation "ottootto%" are given
    Then new user is not created and error "username is already taken" is reported 

Scenario: creation fails when password and password confirmation do not match
    Given register new user is selected
    When valid username "otto" and valid password "ottootto%" and non matching confirmation "ottoott%" are given
    Then new user is not created and error "password and password confirmation do not match" is reported   

Scenario: user can login with successfully generated account
    Given user with username "liisa" and password "salainen1" and confirmation "salainen1" is successfully created
    And   login is selected
    When  correct username "liisa" and password "salainen1" are given
    Then  user is logged in  

    Scenario: user can not login with account that is not successfully created
    Given user with username "aa" and password "bad" and confirmation "bad" is unsuccessfully created
    And   login is selected
    When  nonexistent username "aa" and password "bad" are given
    Then  user is not logged in and error message is given  
