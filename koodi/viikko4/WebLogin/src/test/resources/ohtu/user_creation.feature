
Feature: A new user account can be created if a proper unused username and password and matching password confirmation are given

    Scenario: creation successful with correct username and password
        Given new user is selected
        When  valid username "newuser" and valid password "password1" and matching password confirmation are given
        Then  user is created

    Scenario: creation fails with too short username and valid password
        Given new user is selected
        When short username "nw" and valid password "password1" and matching password confirmation are given
        Then user is not created and error "username should have at least 3 characters" is reported   

    Scenario: creation fails with correct username and too short password
        Given new user is selected
        When  valid username "newuser" and short password "pass1" and matching password confirmation are given
        Then user is not created and error "password should have at least 8 characters" is reported   

    Scenario: creation fails with correct username and password consisting of letters
        Given new user is selected
        When  valid username "newuser" and password containing only letters "letterpassword" and matching password confirmation are given
        Then user is not created and error "password can not contain only letters" is reported 

    Scenario: creation fails with already taken username and valid pasword
        Given new user is selected
        When already taken username "jukka" and valid password "password1" and matching password confirmation are given
        Then user is not created and error "username is already taken" is reported 

    Scenario: creation fails when password and password confirmation do not match
        Given new user is selected
        When valid username "newuser" and valid password "password1" and not matching password "password11" confirmation are given 
        Then user is not created and error "password and password confirmation do not match" is reported   