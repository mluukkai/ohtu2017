package ohtu;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import ohtu.io.*;
import ohtu.data_access.*;
import ohtu.domain.User;
import ohtu.services.*;

public class Stepdefs {
    App app;
    StubIO io;
    UserDao userDao = new InMemoryUserDao();
    AuthenticationService auth = new AuthenticationService(userDao);
    List<String> inputLines = new ArrayList<>();
    
    @Given("^command login is selected$")
    public void command_login_selected() throws Throwable {
        inputLines.add("login");
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are entered$")
    public void a_username_and_password_are_entered(String username, String password) throws Throwable {
       inputLines.add(username);
       inputLines.add(password);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }

    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String expectedOutput) throws Throwable {
        assertTrue(io.getPrints().contains(expectedOutput));
    }
    
    @Given("^command new user is selected$")
    public void command_new_user_selected() throws Throwable {
        inputLines.add("new");
    }
    
//   @Given("^user \"([^\"]*)\" with password \"([^\"]*)\" is created$")
    @Given("^user \"([^\"]*)\" with password \"([^\"]*)\" is created$")
    public void user_with_password_is_created(String username, String password) throws Throwable {
        auth.createUser(username, password);
    }
    
 
}
