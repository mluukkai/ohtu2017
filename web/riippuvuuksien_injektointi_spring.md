
## Dependency injection Spring-sovelluskehyksessä

Jatketaan [laskimen](rhttps://github.com/mluukkai/ohtu2017/blob/master/web/riippuvuuksien_injektointi.md) tarkastelua.

Koodiesimerkit löytyvät maven-muotoisina projekteina kurssirepositoriosta (hakemistossa viikko2)

Ennen käynnistämistä laskin pitää konfiguroida injektoimalla sille sopivat riippuvuudet:

``` java
// konfigurointivaihe
Laskin laskin = new Laskin( new KonsoliIO() );

// ja laskin valmiina käyttöön
laskin.suorita();
```

Esimerkkimme tapauksessa konfigurointi on helppoa. Isommissa ohjelmissa konfiguroitavalla oliolla voi olla suuri määrä riippuvuuksia ja konfigurointi voi olla monimutkaista.

[Spring-sovelluskehys](http://www.springsource.org/) tarjoaa mahdollisuuden olioiden konfigurointiin erillisten konfiguraatiotiedostojen avulla.

Spring saadaan käyttöön lisäämällä sopivat riippuvuudet maven-projektin määrittelemään pom.xml-tiedostoon, katso tarkemmin projektista [https://github.com/mluukkai/ohtu2017/tree/master/viikko2/RiippuvuuksienInjektointi2](https://github.com/mluukkai/ohtu2017/tree/master/viikko2/RiippuvuuksienInjektointi2)

Springissä konfigurointi tehdään xml-tiedostoon:

``` java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

    <bean id="konsoliIo" class="ohtu.laskin.KonsoliIO">
    </bean>

    <bean id="laskin" class="ohtu.laskin.Laskin">
        <constructor-arg ref="konsoliIo" />
    </bean>

</beans>
```

Oleellinen osa tässä ovat <code>bean</code>-tägien sisään tehtyt määritykset. Ensin määritellään olio, jonka tyyppi on KonsoliIO ja jota Springissä kutsutaan nimellä "konsoliIo". Nimi on vapaavalintainen merkkijono.

Toiseksi määritellään Laskin-tyyppinen olio, jolle annetaan nimi "laskin" ja joka saa konstruktoriparametrikseen viitteen konsoliIo-nimiseen olioon.

Spring luo automaattisesti instanssit näin konfiguroiduista olioista ja antaa ne sovelluksen käyttöön pyydettäessä. Springistä ja vastaavista oliota hallinnoivista sovelluskehyksistä käytetään termiä *oliosäiliö* englanniksi *container*.

Konfiguraatiotiedosto on talletettu nimellä _src/main/resources/spring-context.xml_. Jos NetBeans ei osaa näyttää tiedostoa Projects-välilehdellä, pääset siihen käsiksi välilehden Files kautta:

![](https://github.com/mluukkai/ohtu2017/raw/master/images/lh2-1.png)

Pääohjelma on nyt seuraava:

``` java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

        Laskin laskin = (Laskin) ctx.getBean("laskin");
        laskin.suorita();
    }
}
```

Pääohjelma tarvitsee *sovelluskontekstin*, jonka kautta se pääsee käsiksi Springin oliosäiliössä oleviin olioihin. Kontekstilta olioa voi pyytää käyttämällä oliolle määriteltyä nimeä.

Jos tietyn tyypin olioita ei ole määritelty Springin oliosäiliön hallinnoitavaksi kuin yksi, voi olion pyytää Springiltä myös seuraavasti:

``` java
Laskin laskin = ctx.getBean(Laskin.class);
laskin.suorita();
```
Oletusarvoisesti Spring luo vain yhden olion kustakin oliosäiliöön määritellystä oliosta. Esim seuraavassa syntyy ainoastaan yksi Laskin-olio:

``` java
Laskin laskin1 = ctx.getBean(Laskin.class);
laskin1.suorita();

Laskin laskin2 = ctx.getBean(Laskin.class);
laskin2.suorita();
```

Jos haluttaisiin, että jokaisella <code>getBean</code>-pyynnöllä syntyy uusi olio, tulisi konfiguraatiotiedosto olla muodossa:


``` java
<bean id="laskin" class="ohtu.laskin.Laskin" scope="prototype">
    <constructor-arg ref="konsoliIo" />
</bean>
```

### Springin konfigurointi annotaatioilla

Esimerkkiprojekti [https://github.com/mluukkai/ohtu2017/tree/master/viikko2/RiippuvuuksienInjektointi3](https://github.com/mluukkai/ohtu2017/tree/master/viikko2/RiippuvuuksienInjektointi3) esittelee vaihtoehtoisen tavan konfiguroida Springin hallinnoimia oliota. Konfiguraatiotiedosto on nyt muotoa

``` java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
                   http://www.springframework.org/schema/context
                   http://www.springframework.org/schema/context/spring-context-3.0.xsd
                   http://www.springframework.org/schema/context
                   http://www.springframework.org/schema/context/spring-context-3.1.xsd">

    <context:annotation-config />
    <context:component-scan base-package="ohtu.laskin" />

</beans>
```
Ensin todetaan että käytetään annotaatioita konfigurointiin ja sen jälkeen määritellään, että annotaatioilla konfiguroidut luokat löytyvät pakkauksesta <code>ohtu.laskin</code> tai sen alipakkauksista.

Luokan <code>KonsoliIO</code> alkuun on nyt lisätty annotaatio <code>@Component</code>:

``` java
import org.springframework.stereotype.Component;

@Component
public class KonsoliIO implements IO {
    private Scanner lukija;

    public KonsoliIO() {
        lukija = new Scanner(System.in);
    }

    public int nextInt() {
        return lukija.nextInt();
    }

    public void print(String m) {
        System.out.print(m);
    }

}
```

Tämä saa aikaan sen, että Spring luo oliosäiliöön <code>KonsoliIO</code>-olion. Olion nimeksi tulee oletusarvoinen konsoliIO.

Myös Laskin on merkitty samalla annotaatiolla. Laskimesta löytyy toinenkin mielenkiintoinen annotaatio, <code>@Autowired</code>:

``` java
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Laskin {
    private IO io;

    @Autowired
    public Laskin(IO io) {
        this.io = io;
    }

    public void suorita(){
        // ...
    }

    private int laskeSumma(int luku1, int luku2) {
        return luku1+luku2;
    }

}
```

<code>@Autowired</code> kertoo Springille, että sen täytyy etsiä konstruktoriparametriksi sopiva olio oliosäiliöstään ja antaa se laskimelle parametriksi luomishetkellä.

Laskin saa oletusarvoisen nimen "laskin", eli sama kuin luokan nimi mutta ensimmäinen kirjain pienellä. Nyt Laskin on pääohjelman käytettävissä tutulla tavalla:

``` java
public static void main(String[] args) {
    ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

    Laskin laskin = (Laskin) ctx.getBean("laskin");
    laskin.suorita();
}
```

### Lisätietoa

* koodiesimerkkejä moniin tilanteisiin [http://www.roseindia.net/tutorial/spring/spring3/ioc/index.html](http://www.roseindia.net/tutorial/spring/spring3/ioc/index.html), ApplicationContext-olion sijasta esimerkeissä käytetään BeanFactoryä jonka avulla saadaan oliot Springiltä
* kaikki oleellinen löytyy Springin [manuaalista](http://static.springsource.org/spring/docs/3.2.1.RELEASE/spring-framework-reference/)
