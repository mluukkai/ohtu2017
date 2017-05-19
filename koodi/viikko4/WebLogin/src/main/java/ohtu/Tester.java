package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/jttakkin/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        WebElement element;

//        driver.get("http://localhost:4567");
//        sleep(2);
//        element = driver.findElement(By.linkText("login"));
//        element.click();
//        sleep(2);
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("akkep");
//        element = driver.findElement(By.name("login"));
//        sleep(2);
//        element.submit();
//        sleep(3);
        driver.get("http://localhost:4567");
        sleep(1);
        element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(1);
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("aaaaaaaaaaaaaaaaaa");
        element = driver.findElement(By.name("login"));
        sleep(1);
        element.submit();
        sleep(2);

        driver.get("http://localhost:4567");
        sleep(1);
        element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(1);
        element = driver.findElement(By.name("username"));
        element.sendKeys("aaaaaaaaaaaaaaaaaaa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("aaaaaaaaaaaaaaaaaa");
        element = driver.findElement(By.name("login"));
        sleep(1);
        element.submit();
        sleep(2);

        driver.get("http://localhost:4567");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(1);
        element = driver.findElement(By.name("username"));
        element.sendKeys("uusikayttaja");
        element = driver.findElement(By.name("password"));
        element.sendKeys("kayttaja");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("kayttaja");
        element = driver.findElement(By.name("signup"));
        sleep(1);
        element.click();

        element = driver.findElement(By.linkText("continue to application mainpage"));
        sleep(1);
        element.click();
        element = driver.findElement(By.linkText("logout"));
        sleep(1);
        element.click();

        driver.quit();

    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
