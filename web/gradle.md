Tehdään nyt gradle-projekti alusta asti itse. Tee tehtävienpalautusrepositoriosi sisälle hakemisto ja mene hakemistoon. Kokeile toimiiko koneessasi komento `gradle`. Jos ei, kopioi hakemistoon jostain aiemmasta gradle-projektistasi tiedosto _gradlew_ jos käytät Linux tai OSX) tai _gradlew.bat_ jos käytät Windowsia ja käytä jatkossa komentoa _./gradlew_ tai _gradlew_. Mikäli edelleen ei toimi, kopioi gradlew tiedoston lisäksi hakemisto gradle ja kokeile komentoja uudelleen.

Aloita antamalla komento _gradle_:

<pre>
Welcome to Gradle 3.4.1.

To run a build, run gradle <task> ...

To see a list of available tasks, run gradle tasks
...
</pre>

Ohje neuvoo meitä seuraavasti "To see a list of available tasks, run gradle tasks", eli kokeillaan komentoa _gradle tasks_:

<pre>
------------------------------------------------------------
All tasks runnable from root project
------------------------------------------------------------

Build Setup tasks
-----------------
init - Initializes a new Gradle build. [incubating]
wrapper - Generates Gradle wrapper files. [incubating]

Help tasks
----------
buildEnvironment - Displays all buildscript dependencies declared in root project 'HelloGradle'.
components - Displays the components produced by root project 'HelloGradle'. [incubating]
dependencies - Displays all dependencies declared in root project 'HelloGradle'.
dependencyInsight - Displays the insight into a specific dependency in root project 'HelloGradle'.
dependentComponents - Displays the dependent components of components in root project 'HelloGradle'. [incubating]
help - Displays a help message.
model - Displays the configuration model of root project 'HelloGradle'. [incubating]
projects - Displays the sub-projects of root project 'HelloGradle'.
properties - Displays the properties of root project 'HelloGradle'.
tasks - Displays the tasks runnable from root project 'HelloGradle'.

To see all tasks and more detail, run gradle tasks --all

To see more detail about a task, run gradle help --task <task>
</pre>

Komento listaa käytettävissä olevat _taskit_. Gradlen [dokumentaatio](https://docs.gradle.org/current/userguide/tutorial_using_tasks.html) kuvaa taskeja seuraavasti:

> Each gradle project is made up of one or more tasks. A task represents some atomic piece of work which a build performs. This might be compiling some classes, creating a JAR, generating Javadoc, or publishing some archives to a repository. 

Eli taskit ovat siis "komentoja" joita voimme suorittaa gradle-projekteille.

Gradle-projekti määritellään projektihakemiston juureen sijoitettavan tiedoston _build.gradle_ avulla.  Saat luotua tiedoston suorittamalla taskin _init_ (eli antamalla komennon _gradle init_). 

Huomaat että alustuksen jälkeen hakemistoon on tullut tiedoston _build.gradle_ lisäksi muutakin:

<pre>
$ ls -la
drwxr-xr-x   8 mluukkai  ATKK\hyad-all   272 Mar 21 19:47 .
drwxr-xr-x  27 mluukkai  ATKK\hyad-all   918 Mar 21 19:27 ..
drwxr-xr-x   4 mluukkai  ATKK\hyad-all   136 Mar 21 19:44 .gradle
-rw-r--r--   1 mluukkai  ATKK\hyad-all  1172 Mar 21 19:47 build.gradle
drwxr-xr-x   3 mluukkai  ATKK\hyad-all   102 Mar 21 19:47 gradle
-rwxr-xr-x   1 mluukkai  ATKK\hyad-all  5299 Mar 21 19:47 gradlew
-rw-r--r--   1 mluukkai  ATKK\hyad-all  2260 Mar 21 19:47 gradlew.bat
-rw-r--r--   1 mluukkai  ATKK\hyad-all   586 Mar 21 19:47 settings.gradle 
</pre>

Näistä hakemisto _.gradle_ kannattaa gitignoroida. Gradle-projekteissa tulee gitignoroida aina myös hakemisto _build_ mihin kaikki gradle taskien generoimat tiedostot sijoitetaan.

Tavoitteenamme on lisätä projektiin Java-koodia ja JUnit-testejä. Oletusarvoisesti gradle ei ymmärrä Javasta mitään, mutta ottamalla käyttöön _java-pluginin_, se lisää projektille uusia, Javan kääntämiseen liittyviä taskeja.

Otetaan nyt käyttöön java-plugin lisäämällä tiedostoon _build.gradle_ rivi:

<pre>
apply plugin: 'java'
</pre>

**HUOM** Tiedostossa on jo valmiina _kommenteissa_ kaikenlaista hyödyllistä esim. java-pluginin määrittely. 

Lisäillään kuitenkin nyt asioita yksi kerrallaan samalla tarkastellen mistä on kysymys.

Kun nyt suoritetaan komento _gradle tasks_ huomataan että listalla on uusia, java-pluginin lisäämiä taskeja:

<pre>
Build tasks
-----------
assemble - Assembles the outputs of this project.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles main classes.
clean - Deletes the build directory.
jar - Assembles a jar archive containing the main classes.
testClasses - Assembles test classes.


Documentation tasks
-------------------
javadoc - Generates Javadoc API documentation for the main source code.


Verification tasks
------------------
check - Runs all checks.
test - Runs the unit tests.
</pre>

Voimme nyt siis suorittaa projektille esim. viime viikoilta tutut komennot _gradle build_ ja _gradle test_.

Jos suoritamme esim. taskin _build_ eli komennon _gradle build_ on tulostus seuraava

<pre>
:compileJava NO-SOURCE
:processResources NO-SOURCE
:classes UP-TO-DATE
:jar
:assemble
:compileTestJava NO-SOURCE
:processTestResources NO-SOURCE
:testClasses UP-TO-DATE
:test NO-SOURCE
:check UP-TO-DATE
:build
</pre>

tulostus kertoo, että taskin _build_ suoritus aiheutti myös taskien _compileJava_, _processResources_ jne suorituksen.

Komento help vahvistaa asian:

$ gradle help --task build
<pre>
Detailed task information for build

Path
     :build

Type
     Task (org.gradle.api.Task)

Description
     Assembles and tests this project.
</pre>

Eli _build_ suorittaa koodin käännöksen, paketoinnin jar-tiedostoksi sekä suorittaa projektiin liittyvät testit. 

Jos haluamme ainoastaan kääntää koodin, riittää taskin _compileJava_ suorittaminen.

Ennen kun siirryt eteenpäin suorita _gradle clean_ joka poistaa kaikki edellisen komennon luomat tiedostot.

## järkevä editori

Älä käytä tällä kertaa NetBeansia tai muutakaan IDE:ä vaan tee kaikki koodi tekstieditorilla. 

Älä käytä nanoa, notepadia tai geditiä vaan ota käyttöön jokin ohjelmointiin suunniteltu editori. Hyviä vaihtoehtoja ovat esim:
* https://code.visualstudio.com
* https://atom.io
* https://www.sublimetext.com

Laitoksen koneilla ei valitettavasti ole mitään järkevää editoria. Voit asentaa Atomin laitoksen koneelle seuraavasti
* mene esim. kotihakemistoosi (komennolla cd)
* lataa atom komennolla _wget https://github.com/atom/atom/releases/download/v1.15.0/atom-amd64.tar.gz_
* pura paketti komennolla _tar -xf atom-amd64.tar.gz_
* editorin voi nyt käynnistää komennolla _~/atom-1.15.0-amd64/atom_
* jos lisäät kotihakemistossasi olevaan tiedostoon _.bashrc_ seuraavan rivin
```bash
alias atom='~/atom-1.15.0-amd64/atom'
```
ja uudelleenkäynnistät terminaalin, voit käynnistää atomin missä vaan komennolla _atom_

Gradle olettaa, että ohjelman koodi sijaitsee projektin juuren alla hakemistossa _src/main/java_. Luo hakemisto(t) ja tiedosto _src/main/java/Main.java_ ja sille esim. seuraava sisältö:

<pre>
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello gradle!");
    }
}
</pre>

Suorita sitten taski  _compileJava_ ja tarkastele projektihakemiston sisältöä komennolla _tree_:

<pre>
$ tree
.
├── build
│   ├── classes
│   │   └── main
│   │       └── Main.class
│   └── tmp
│       └── compileJava
...
</pre>

Taski _compileJava_ on siis luonut hakemiston _build_ ja sen sisälle käännöksen tuloksena olevan _class_-tiedoston.

Suorita käännetty koodi menemällä hakemistoon ja antamalla komento _java_:

<pre>
$ cd build/classes/main/
$ java Main
Hello gradle!
</pre>

Yleensä java-koodia ei suoriteta käyttämällä suoraan _class_-tiedostoja. Parempi tapa on pakata koodi  _jar_-tiedostoksi viikon 1 [tehtävän 6](https://github.com/mluukkai/ohtu2017/blob/master/laskarit/1.md#6-gradle) tapaan.

Jar-tiedosto muodostetaan gradlen taskilla jar. Help kertoo seuraavaa:

<pre>
$ gradle help --task jar
Detailed task information for jar

Path
     :jar

Type
     Jar (org.gradle.api.tasks.bundling.Jar)

Description
     Assembles a jar archive containing the main classes.
</pre>

Määritellään taskia varten pääohjelman sijainti lisäämällä seuraava tiedostoon _build.gradle_:

<pre>
jar {
    manifest {
        attributes 'Main-Class': 'Main'
    }
}
</pre>

Palaa nyt projektihakemistoon ja suorita jar-tiedoston generoiva taski _jar_ (eli komento _gradle _jar). 

Voit suorittaa syntyneen jar-tiedoston seuraavasti:

<pre>
$ java -jar build/libs/HelloGradle.jar
Hello gradle!
</pre>

Aiemmissa tehtävissä olemme pystyneet suorittamaan koodin myös komennolla _gradle run_. Komento aiheuttaa kuitenkin nyt virheilmoituksen _Task 'run' not found in root project_.

Syynä tälle on se, että taski _run_ ei ole java-pluginin vaan [application-pluginin](https://docs.gradle.org/current/userguide/application_plugin.html) määrittelemä. Otetaan käyttöön myös application plugin lisäämällä tiedostoon _build.gradle_ rivi 

<pre>
apply plugin: 'application'
</pre>

Komento aiheuttaa virheen _No main class specified_.

Pluginin [dokumentaatio](https://docs.gradle.org/current/userguide/application_plugin.html) kertoo, että _main class_ tulee määritellä lisäämällä tiedostoon _build.gradle_ seuraava rivi:

<pre>
mainClassName = 'Main'
</pre>

Nyt ohjelman suorittaminen taskin avulla toimii:

<pre>
$ gradle run
:compileJava UP-TO-DATE
:processResources NO-SOURCE
:classes UP-TO-DATE
:run
Hello gradle!

BUILD SUCCESSFUL

Total time: 0.977 secs
</pre>

Suorittaminen kannattanee tehdä optiota _-q_ käyttäen, jolloin gradle jättää omat tulosteensa tekemättä:

<pre>
$ gradle run -q
Hello gradle!
</pre>

Laajennetaan ohjelmaa siten, että se kysyy Scanner-olion avulla käyttäjän nimeä:

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("kuka olet: ");
        String nimi = scanner.nextLine();

        System.out.println("Hello "+nimi);
    }
}
```

Jos ohjelmasta tehdään _jar-tiedosto_, toimii se odotetulla tavalla:

<pre>
$ java -jar build/libs/HelloGradle.jar
kuka olet:
pekka
Hello pekka
</pre>

Jos ohjelma suoritetaan gradlen _run_-taskin seurauksena on kuitenkin virhe:

<pre>
$ gradle run
:compileJava
:processResources NO-SOURCE
:classes
:run
kuka olet:
Exception in thread "main" java.util.NoSuchElementException: No line found
        at java.util.Scanner.nextLine(Scanner.java:1540)
        at Main.main(Main.java:7)
:run FAILED

FAILURE: Build failed with an exception.
</pre>

Syynä tälle on se, että oletusarvoisesti gradlen _run_-task ei liitä terminaalia systeein "inputvirtaan". Asia saadaan korjautumaan lisäämällä tiedostoon _build.gradle_ seuraava: 

<pre>
run {
    standardInput = System.in
}
</pre>

Nyt komento _gradle run_ toimii. 

Lisätään vielä ohjelmalle luokka, jonka avulla on mahdollista laskea kertolaskuja. Sijoitetaan luokka pakkaukseen _ohtu_ eli tiedostoon _src/main/java/ohtu/Multiplier.java_

```java
package ohtu;

public class Multiplier {
    private int value;
    public Multiplier(int value) {
        this.value = value;
    }

    public int multipliedBy(int other) {
        return value * other;
    }

}
```

Käytetään luokkaa pääohjelmasta. Huomaa, että koska luokka on eri pakkauksessa kuin pääohjelma, tulee pakkaus importata:

```java
import java.util.*;
import ohtu.Multiplier;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Multiplier kolme = new Multiplier(3);
        System.out.println("anna luku ");
        int luku = scanner.nextInt();

        System.out.println("luku kertaa kolme on "+kolme.multipliedBy(luku) );
    }
}
```

Tehdään nyt luokalle JUnit-testi. Gradle olettaa, että JUnit-testit sijoitetaan hakemiston _src/test/java_ alle. Sijoitamme testin samaan pakkaukseen kuin testattavan luokan, eli tiedostoon _src/test/java/ohtu/MultiplierTest.java_

```java
package ohtu;

import static org.junit.Assert.*;
import org.junit.Test;

public class MultiplierTest {

    @Test
    public void kertominenToimii() {
        Multiplier viisi = new Multiplier(5);

        assertEquals(5, viisi.multipliedBy(1));
        assertEquals(35, viisi.multipliedBy(7));
    }

}
```

Yritetään suorittaa testit komennolla _gradle test_. Seurauksena on suuri määrä virheilmoituksia. Virheet tapahtuvat taskin _compileTestJava_ eli testien kääntämisen aikana:

<pre>
$ gradle test
:compileJava UP-TO-DATE
:processResources NO-SOURCE
:classes UP-TO-DATE
:compileTestJava
/Users/mluukkai/opetus/koodit/HelloGradle/src/test/java/ohtu/MultiplierTest.java:3: error: package org.junit does not exist
import static org.junit.Assert.*;
                       ^
/Users/mluukkai/opetus/koodit/HelloGradle/src/test/java/ohtu/MultiplierTest.java:4: error: package org.junit does not exist
import org.junit.Test;
                ^
/Users/mluukkai/opetus/koodit/HelloGradle/src/test/java/ohtu/MultiplierTest.java:9: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class MultiplierTest
/Users/mluukkai/opetus/koodit/HelloGradle/src/test/java/ohtu/MultiplierTest.java:11: error: cannot find symbol
</pre>

Syynä virheille on se, että projektimme ei tunne testien importtaamaa koodia:

<pre>
import static org.junit.Assert.*;
import org.junit.Test;
</pre>

JUnit-kirjasto on siis ohjelmamme testien käännösaikainen _riippuvuus_. 

## riippuvuudet

Käytännössä riippuvuudet ovat jar-tiedostoja, jotka sisältävät riippuvuuksien, eli tässä tapauksessa JUnitin koodin. Gradlen samoin kuin Mavenin hyvä puoli on se, että ohjelmoijan ei tarvitse itse latailla riippuvuuksia vaan riittää kun projektin riippuvuudet määritellään tiedostossa _build.gradle_ ja gradle hoitaa sitten automaattisesti riippuvuuksien lataamisen jos niitä ei jo löydy koneelta.

Tarvittava määrittely on seuraava:

<pre>
repositories {
    jcenter()
}

dependencies {
    testCompile 'junit:junit:4.12'
}
</pre>

Ensimmäinen osa _repositories_ kertoo gradlelle mistä sen tulee etsiä riippuvuuksia. [jcenter](https://bintray.com/bintray/jcenter) on eräs niistä paikoista johon on talletettu suuri määrä gradlen ja mavenin käyttämiä kirjastoja. Toinen vaihtoehtoinen paikka riippuvuuksien etsimiseen on [mavenCentral](https://search.maven.org). _repositories_-osassa voidaan määritellä myös useita paikkoja joista gradle käy etsimässä projektiin määriteltyjä riippuvuuksia.

Toinen osa määrittelee, että _testCompile_-vaiheeseen otetaan käyttöön JUnit-kirjaston versio 4.12. Käytännössä tämä tarkoittaa että kääntäessään testien koodia gradle liittää JUnitin _classpathiin_.

Kun suoritamme uudelleen komennon _gradle test_ kaikki toimii. 

Rikotaan vielä testi ja varmistetaan että junit huomaa virheen.

