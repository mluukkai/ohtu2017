package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;
import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Misc\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        //loginSuccess(driver);
        //failLoginWrongPassword(driver);
        //failLoginMissingUser(driver);
        //createUser(driver);
        createUserAndLogOut(driver);

        driver.quit();
    }

    private static void loginSuccess(WebDriver driver) {
        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);
    }

    private static void failLoginWrongPassword(WebDriver driver) {
        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("haloo");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);
    }

    private static void failLoginMissingUser(WebDriver driver) {
        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("timo");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);
    }

    private static void createUser(WebDriver driver) {
        driver.get("http://localhost:4567");

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("olavi");
        element = driver.findElement(By.name("password"));
        element.sendKeys("1234");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("1234");

        sleep(2);

        element = driver.findElement(By.name("signup"));
        element.submit();

        sleep(1);
    }

    private static void createUserAndLogOut(WebDriver driver) {
        createUser(driver);

        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        sleep(1);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(2);
    }

    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
