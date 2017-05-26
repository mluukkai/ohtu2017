package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", 
                "/home/jarkko/Programming/ohtu/chromedriver"); 
        
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element;
        
        
        element = driver.findElement(By.linkText("login"));
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
        driver.get("http://localhost:4567"); 
        element = driver.findElement(By.linkText("login"));
        element.click();
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("vaara");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
        
        driver.get("http://localhost:4567");
        element = driver.findElement(By.linkText("login"));
        element.click();
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("olematon");
        element = driver.findElement(By.name("password"));
        element.sendKeys("asdf");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
        
        driver.get("http://localhost:4567/user");
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("uusi");
        element = driver.findElement(By.name("password"));
        element.sendKeys("asdf");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("asdf");
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();
        
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        sleep(2);
        driver.quit();
    }
   
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
