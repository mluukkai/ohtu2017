package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        testi1();
        testi2();
        testi3();
        testi4();
        testi5();
    }

    public static void testi1() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");
        sleep(1);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        sleep(1);
        element.submit();
        sleep(1);
        driver.quit();
    }

    public static void testi2() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");
        sleep(1);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("haloo");
        element = driver.findElement(By.name("login"));
        sleep(1);
        element.submit();
        sleep(1);
        driver.quit();
    }

    public static void testi3() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");
        sleep(1);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("haloo");
        element = driver.findElement(By.name("password"));
        element.sendKeys("haloo");
        element = driver.findElement(By.name("login"));
        sleep(1);
        element.submit();
        sleep(1);
        driver.quit();
    }

    public static void testi4() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");
        sleep(1);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("isäs1");
        element = driver.findElement(By.name("password"));
        element.sendKeys("emäs");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("emäs");
        sleep(1);
        element = driver.findElement(By.name("signup"));
        sleep(1);
        element.submit();
        sleep(1);
        driver.quit();
    }

    public static void testi5() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");
        sleep(1);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("isäs2");
        element = driver.findElement(By.name("password"));
        element.sendKeys("emäs");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("emäs");
        sleep(1);
        element = driver.findElement(By.name("signup"));
        element.submit();
        sleep(1);

        WebElement e = driver.findElement(By.linkText("continue to application mainpage"));
        sleep(1);
        e.submit();

        sleep(1);
        e = driver.findElement(By.linkText("logout"));
        e.submit();
        sleep(1);
        driver.quit();
    }

    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
