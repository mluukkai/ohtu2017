# Selenium troubleshooting

Osalla on ollut ongelmia Seleniumin toiminnan kanssa. Alla muutamia tapoja, miten ongelmat on saatu ratkaisuta. Jos törmäät ongelmaan ja saat sen ratkaistua jollain alla mainitsemattomalla tavalla, lisää ohje dokumenttiin.

## tapa 1

Lataa [täältä](https://sites.google.com/a/chromium.org/chromedriver/downloads) ChromeDriver.

Tee seuraava määrittely seleniumia käyttävässä tiedostossa:

```java
// windowsissa
System.setProperty("webdriver.chrome.driver", "oma_polku/chromedriver.exe"); 

// macissa ja linuxeissa
System.setProperty("webdriver.chrome.driver", "oma_polku/chromedriver.exe"); 
```



## tapa 2

Kokeile käyttää FirefoxDriveria Chromen sijaan. (Testattu Linuxilla)
Selenium v2.41.0 tukee ainoastaan Firefoxin versiota 28. Se löytyy [täältä](https://ftp.mozilla.org/pub/firefox/releases/28.0/) kun klikkaat omaa arkkitehtuuriasi. Pura paketti ja ota polku talteen.

Määrittele seuraavasti:
```java
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

File pathBinary = new File("polku/jonne/purit/firefoxin/firefox.exe");
FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
FirefoxProfile firefoxProfile = new FirefoxProfile();
WebDriver driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
```

## tapa 3
