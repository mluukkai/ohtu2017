# Selenium troubleshooting

Osalla on ollut ongelmia Seleniumin toiminnan kanssa. Alla muutamia tapoja, miten ongelmat on saatu ratkaisuta. Jos törmäät ongelmaan ja saat sen ratkaistua jollain alla mainitsemattomalla tavalla, lisää ohje dokumenttiin.

## tapa 1: chromedriverin downloadaus

Lataa [täältä](https://sites.google.com/a/chromium.org/chromedriver/downloads) ChromeDriver.

Tee seuraava määrittely seleniumia käyttävässä tiedostossa:

```java
// windowsissa
System.setProperty("webdriver.chrome.driver", "oma_polku/chromedriver.exe"); 

// macissa ja linuxeissa
System.setProperty("webdriver.chrome.driver", "oma_polku/chromedriver.exe"); 
```

Testejä varten kannattaa määrittely sijoittaa luokan <code>ServerRule</code> metodiin <code>before</code>.

## tapa 2: firefox-driver

```

Ota käyttöön _FireforDriver_

```java
import org.openqa.selenium.firefox.FirefoxDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        // ...
    }
}
```

Firefox-driver ja seleniumin versio _2.41_ Firefox-selaimen uusimmat versiot eivät valitettavasti ole yhteensopivia. Joudut käyttämään Firefoxin versiota 28. Googlaa miten saat downgreidattua firefoxin version.

**HUOM:** erään kirjastoyhteensopivuusongelman kanssa Sparkin kanssa ei tällä hetkellä ole mahdollista käyttää Seleniumista uudempaa versiota kuin _2.41_.
 
## tapa 3: html-driver

Lisää projektille riippuvuudeksi _HtmlUnitDriver_ :

```groovy
dependencies {
    // ...
    compile group: 'org.seleniumhq.selenium', name: 'selenium-htmlunit-driver',version: seleniumVersion  
}
```

[HtmlUnitDriver](https://github.com/SeleniumHQ/selenium/wiki/HtmlUnitDriver) on ns. [headles](https://en.wikipedia.org/wiki/Headless_browser)-selain, eli sillä ei ole graafista käyttöliittymää. Jos haluat tietää millä sivulla selain on menossa, joudut esim. tulostamaan sivun lähdekoodin konsoliin.

Ota HtmlUnitDriver käyttöön seuraavasti:

```java
...
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
// ...

        driver.quit();
    }
    
}
```

HtmlUnitDriver:in hyvä puoli on nopeus. Voit käyttää sitä myös testeissä. Testien debuggaaminen muuttuu hankalammaksi, mutta testit toimivat nopeasti.



