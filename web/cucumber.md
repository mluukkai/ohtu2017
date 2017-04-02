[Cucumber](https://cucumber.io) on kirjasto User Storyjen hyväksymätestauksen automatisointiin. Ideana on kirjoittaa storyjen testit asiakkaan ymmärtämässä muodossa, normaalilla kielellä, mutta tehdä niistä kuitenkin automaattisesti suoritettavia. 

Cucumberilla tehtävät testit lasketaan kuuluvaksi [Behavior-driven developmentin eli BDD:n](https://en.wikipedia.org/wiki/Behavior-driven_development) alalle. BDD:ssä pyritään välttämään termin _testi_ käyttöä ja sen sijaan puhutaan _spesifikaatioista_.

Vaikka Cucumber on varsinaisesti tarkoitettu koko ohjelmiston "end-to-end"-testaukseen, tarkastellaan ensin Cucumberin käyttöä yksittäisen luokan testaamiseen.

Hae kurssirepositorion hakemistossa [koodi/viikko3/HelloCucumber](https://github.com/mluukkai/ohtu2017/tree/master/koodi/viikko3/HelloCucumber) oleva NetBeans-projekti. 

NetBeansiin kannattaa asentaa [täältä](http://plugins.netbeans.org/plugin/43852/cucumber-features) löytyvä Cucumber-plugin. Pluginia ei ole päivitetty aikoihin, mutta se näyttää toimivan vähintään siedettävästi ainakin NetBeans 8.1:llä. Pluginin asennus tapahtuu klikkaamalla [pluginin sivulta](http://plugins.netbeans.org/plugin/43852/cucumber-features) nappia download, ja valitsemalla NetBeansin valikosta _tools_, _plugins_ ja avautuvasta dialogista _downloaded_, _add plugin_ ja etsimällä ladattu tiedosto:

![](https://github.com/mluukkai/ohtu2017/raw/master/images/lh3-1.png)

Uudelleenkäynnistä NetBeans ja avaa nyt projekti.

Testattavana on yksinkertinen laskuri:

```java
public class Counter {
   int val = 0;

   public void increase(){
        val++;
   } 
   
   public void reset(){
        val = 0;
   }    
   
   public void increment(int a){
        val += a;
   } 
   
   public int value(){
       return val;
   }
}
```

## vaatimuksien ilmaiseminen

Laskurin haluttua toimintaa kuvaavat seuraavat user storyt
* As a user I want to be able to increase the counter value
* As a user I want to be able to set the counter to value zero  

Cucumberissa (ja muutamassa muussakin BDD-työkaluissa) vaatimukset ilmaistaan [Gherkin](https://cucumber.io/docs/reference#gherkin)-formaatissa. User storya vastaava asia on _Feature_. Laskimen Storyt voidaan ilmaista seuraavasti:

<pre>
Feature: As a user I want to be able to increase the counter value
</pre>


<pre>
Feature: As a user I want to be able to set the counter to value zero
</pre>  

Jokainen feature talletetaan omaan _.feature-päätteiseen_ tiedostoonsa. Featuret sijoitetaan gradle-projekteissa hakemiston _src/test/resources/_ alle. Esimerkkiprojektissamme featuret ovat tiedostoissa _src/test/resources/ohtu/increasingCounter.feature_ ja _src/test/resources/ohtu/resetingCounter.feature_

Featureen liittyy joukko _skenaarioita_ jotka vastaavat käytännössä storyn hyväksymätestejä:

<pre>
Feature: As a user I want to be able to increase the counter value

  Scenario: Increment once
    Given Counter is initialized
    When it is incremented
    Then the value should be 1

  Scenario: Increment by many
    Given Counter is initialized
    When it is incremented by 5
    Then the value should be 5

  Scenario: Increment many times
    Given Counter is initialized
    When it is incremented
    And it is incremented
    And it is incremented
    Then the value should be 3
</pre>

Skenaariot taas kirjoitetaan _Given_, _When_, _Then_ -formaatissa. Jokaista skenaarion riviä kutsutaan askeleeksi eli _stepiksi_. 
* _Given_ kertoo skenaarion (eli testin) lähtötilanteen
* _When_ kuvaa operaation mitä skenaariossa testataan
* _Then_ ilmaisee mitä skenaariossa pitäisi tapahtua

Avainsanan _And_ avulla jokaiseen skenaarion askeleista voidaan liittää useita steppejä. Näin tehdään esimerkin kolmannessa skenaariossa. 

## testien suorituskelpoiseksi tekeminen

Jotta testeistä saadaan suorituskelpoisia tulee projektiin kirjoittaa skenaarion steppejä vastaava koodi. Jokainen steppi määritellään omana metodinaan luokassa _Stepdefs_. Esimerkin steppien määrittely tapahtuu seuraavasti: 

```java
public class Stepdefs {
    Counter counter;
    
    @Given("^Counter is initialized$")
    public void counter_is_initialized() throws Throwable {
        counter = new Counter();
    }

    @When("^it is incremented$")
    public void it_is_incremented() throws Throwable {
        counter.increase();
    }

    @When("^it is incremented by (\\d+)$")
    public void it_is_incremented_by(int val) throws Throwable {
        counter.increment(val);
    }        
    
    @Then("^the value should be (\\d+)$")
    public void the_value_should_be(int val) throws Throwable {
        assertEquals(val, counter.value());
    }
      
}
```

Jokaista metodia edeltää annotaatio, joka määrittelee mitä steppiä vastaavasta metodista on kyse. Kaikkien skenaarioiden _Given_-step on sama, se määrittelee että skenaariot alkavat laskurin luomisella

```java
    public void counter_is_initialized() throws Throwable {
        counter = new Counter();
    }
```

Stepeissä voi olla "parametreja", eli skenaariossa 

<pre>
  Scenario: Increment by many
    Given Counter is initialized
    When it is incremented by 5
    Then the value should be 5
</pre>

määritellyt luvut välitetään niitä vastaaville metodeille 

```java
    public void it_is_incremented_by(int val) throws Throwable {
        counter.increment(val);
    } 

    public void the_value_should_be(int val) throws Throwable {
        assertEquals(val, counter.value());
    }    
```

metodien parametrina. Onnistumisen varmistava "Then"-step suorittaa tarkastuksen JUnitin assertEquals-metodia käyttäen.

Cucumber edellyttää vielä pienen määrän konfiguraatiota, joka on tehty tiedostossa _src/.../RunCukesTest.java_. Konfiguraatio on yksinkertainen, se määrittelee testit suoritettavaksi [JUnit-testien suorituksen yhteydessä](https://cucumber.io/docs/reference/jvm#junit-runner) ja että testien tulos raportoidaan [komentorivillä](https://cucumber.io/docs/reference#pretty):

```java
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class RunCounterTest {
}
```

Testit suoritetaan komennolla _gradle test_. Huomaa, että testien suorittaminen ei todennäköisesti toimi NetBeansin testinapilla.

## nollaamisen skenaariot

Laskimen nollaamiseen liittyvä story on tiedostossa _src/test/resources/ohtu/resetingCounter.feature_

Lisää storyyn seuraavat skenaariot:

<pre>
Feature: As a user I want to be able to set the counter to value zero

  Scenario: Reseting after one increment
    Given Counter is initialized
    When it is incremented
    And it is reseted 
    Then the value should be 0

  Scenario: Reseting after incrementing with several values
    Given Counter is initialized
    When it is incremented by 5
    And it is reseted 
    Then the value should be 0
</pre>

Kun nyt suoritat testit, näyttää tulos seuraavalta:

![](https://github.com/mluukkai/ohtu2017/raw/master/images/lh3-2.png)

Eli cucumber ilmoittaa osan stepeistä olevan _undefined_. Cucumber tulostaa myös valmiin koodirungon, jonka avulla stepin voi toteuttaa.

Kopioi stepin koorirunko toteuttavaan luokkaan ja täydennä se järkevällä tavalla. 

Varmista että testit toimivat.
