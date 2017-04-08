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
    
    @Given("^register new user is selected$")
    public void register_new_user_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();          
    }
    
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" and confirmation \"([^\"]*)\" is successfully created$")
    public void user_succesfully_created(String username,String password,String confirmation) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
        signInWithAndLogOut(username, password, confirmation);
    }
    
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" and confirmation \"([^\"]*)\" is unsuccessfully created$")
    public void user_unsuccesfully_created(String username,String password,String confirmation) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
        signInWith(username, password, confirmation);
        element = driver.findElement(By.linkText("back to home"));       
        element.click();        
    } 
    
    @When("^valid username \"([^\"]*)\" and valid password \"([^\"]*)\" and matching confirmation \"([^\"]*)\" are given$")
    public void valid_username_and_valid_password_and_matching_confirmation_are_given(String username, String password, String confirmation) throws Throwable {
        signInWith(username, password, confirmation);
    }
    
    @When("^too short username \"([^\"]*)\" and valid password \"([^\"]*)\" and matching confirmation \"([^\"]*)\" are given$")
    public void too_short_username_and_valid_password_and_matching_confirmation_are_given(String username, String password, String confirmation) throws Throwable {
        signInWith(username, password, confirmation);
    }
    
    @When("^valid username \"([^\"]*)\" and too short password \"([^\"]*)\" and matching confirmation \"([^\"]*)\" are given$")
    public void correct_username_and_too_short_password_and_matching_confirmation_are_given(String username, String password, String confirmation) throws Throwable {
        signInWith(username, password, confirmation);
    }
    
    @When("^valid username \"([^\"]*)\" and only letters password \"([^\"]*)\" and matching confirmation \"([^\"]*)\" are given$")
    public void correct_username_and_only_letters_password_and_matching_confirmation_are_given(String username, String password, String confirmation) throws Throwable {
        signInWith(username, password, confirmation);
    }
    
    @When("^already taken username \"([^\"]*)\" and valid password \"([^\"]*)\" and matching confirmation \"([^\"]*)\" are given$")
    public void already_taken_username_and_valid_password_and_matching_confirmation_are_given(String username, String password, String confirmation) throws Throwable {
        signInWith(username, password, confirmation);
    }
    
    @When("^valid username \"([^\"]*)\" and valid password \"([^\"]*)\" and non matching confirmation \"([^\"]*)\" are given$")
    public void valid_username_and_valid_password_and_non_matching_confirmation_are_given(String username, String password, String confirmation) throws Throwable {
        signInWith(username, password, confirmation);
    }
  
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("^nonexistent username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void nonexistent_username_and_irrelevant_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("^new user is registered$")
    public void new_user_is_registered() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @Then("^new user is not created and error \"([^\"]*)\" is reported$")
    public void new_user_is_not_registered_and_error_is_reported(String error) throws Throwable {
        pageHasContent(error);
        pageHasContent("Create username and give password");
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
    
    @After
    public void tearDown(){
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
    
    private void signInWith(String username,String password,String confirmation){
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
        
    }
    
    private void signInWithAndLogOut(String username,String password,String confirmation){
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }
}
