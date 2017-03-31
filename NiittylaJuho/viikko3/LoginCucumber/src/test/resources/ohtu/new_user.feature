Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation succesful with correct username and password
        Given command new user is selected
        When  username "devnull" and password "devnull0" is entered
        Then  system will respond with "new user registered"

    Scenario: creation fails with correct username and too short password
        Given command new user is selected
        When  username "devone" and password "null" is entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with correct username and password consisting of letters
        Given command new user is selected
        When  username "devtwo" and password "devausonkivaa" is entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with too short username and valid password
        Given command new user is selected
        When  username "d" and password "devaus000" is entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with already taken username and valid password
        Given command new user is selected
        When  username "devnull" and password "devnull0" and command "new" is entered and username "devnull" is entered and password "devnull11" is entered
        Then  system will respond with "new user not registered"

    Scenario: can login with succesfully generated account
        Given user "eero" with password "salainen1" is created
        And   command login is selected
        When  username "eero" and password "salainen1" is entered
        Then  system will respond with "logged in"

    Scenario: can not login with account that is not succesfully created
        Given user "aa" with password "aa" is created
        And   command login is selected
        When  username "aa" and password "aa" is entered
        Then  system will respond with "wrong username or password"