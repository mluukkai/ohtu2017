package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {

    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^nonexisting username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void nonexisting_username_and_password_are_given(String arg1, String arg2) throws Throwable {
        logInWith(arg1, arg2);
    }

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @Given("^new user is selected$")
    public void new_user_is_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }


    @When("^valid username \"([^\"]*)\" and password \"([^\"]*)\" and confirmation \"([^\"]*)\" are given$")
    public void valid_username_and_password_and_confirmation_are_given(String arg1, String arg2, String arg3) throws Throwable {
        createUserWith(arg1, arg2, arg3);
    }

    @Then("^new user account is created$")
    public void new_user_account_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }

        @When("^too short username \"([^\"]*)\" and valid password \"([^\"]*)\" and confirmation \"([^\"]*)\" are given$")
    public void too_short_username_and_valid_password_and_confirmation_are_given(String arg1, String arg2, String arg3) throws Throwable {
        createUserWith(arg1, arg2, arg3);
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String arg1) throws Throwable {
        pageHasContent(arg1);
    }
    
    @When("^valid username \"([^\"]*)\" and too short password \"([^\"]*)\" and confirmation \"([^\"]*)\" are given$")
    public void valid_username_and_too_short_password_and_confirmation_are_given(String arg1, String arg2, String arg3) throws Throwable {
        createUserWith(arg1, arg2, arg3);
    }

    @When("^valid username \"([^\"]*)\" and letter only password \"([^\"]*)\" and confirmation \"([^\"]*)\" are given$")
    public void valid_username_and_letter_only_password_and_confirmation_are_given(String arg1, String arg2, String arg3) throws Throwable {
        createUserWith(arg1, arg2, arg3);
    }




    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createUserWith(String username, String password, String cPassword) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(cPassword);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

}
