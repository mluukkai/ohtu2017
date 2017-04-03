# Riippuvuuksien injektointi

Lue ensin [http://jamesshore.com/Blog/Dependency-Injection-Demystified.html](http://jamesshore.com/Blog/Dependency-Injection-Demystified.html)

Allaolevat koodiesimerkit löytyvät maven-muotoisina projekteina [kurssirepositoriosta](https://github.com/mluukkai/ohtu2017) (hakemistossa viikko2)

Seuraavassa yksinkertainen laskin:

``` java
public class Main {
    public static void main(String[] args) {
        Laskin laskin = new Laskin();
        laskin.suorita();
    }
}

public class Laskin {

    private Scanner lukija;

    public Laskin() {
        lukija = new Scanner(System.in);
    }

    public void suorita(){
        while( true ) {
            System.out.println("luku 1: ");
            int luku1 = lukija.nextInt();
            if ( luku1==-9999  ) return;

            System.out.println("luku 2: ");
            int luku2 = lukija.nextInt();
            if ( luku2==-9999  ) return;

            int vastaus = laskeSumma(luku1, luku2);
            System.out.println("summa: "+ vastaus);
        }
    }

    private int laskeSumma(int luku1, int luku2) {
        return luku1+luku2;
    }

}
```

Ohjelman ikävä puoli on se, että <code>Laskin</code>-luokalla on konkreettinen riippuvuus <code>Scanner</code>-olioon ja ruudulle tulostamisen hoitavaan <code>System.out</code>-olioon.

Konkreettiset riippuvuudet vaikeuttavat testaamista ja tekevät ohjelman laajentamisen vaikeaksi.

### Riippuvuus rajapintaan

Määritellään rajapinta, jonka taakse konkreettiset riippuvuudet voidaan piilottaa:

``` java
public interface IO {
    int nextInt();
    void print(String m);
}
```

Tehdään rajapinnalle toteutus:

``` java
public class KonsoliIO implements IO {
    private Scanner lukija;

    public KonsoliIO() {
        lukija = new Scanner(System.in);
    }

    public int nextInt() {
        return lukija.nextInt();
    }

    public void print(String m) {
        System.out.println(m);
    }

}
```

Muokatussa <code>Laskin</code>-luokan versiossa määritellään <code>IO</code>-rajapinnan toteuttava oliomuuttuja joka annetaan laskin-oliolle konstruktorin parametrina:

``` java
public class Laskin {
    private IO io;

    public Laskin(IO io) {
        this.io = io;
    }

    public void suorita(){
        while( true ) {
            io.print("luku 1: ");
            int luku1 = io.nextInt();
            if ( luku1==-9999  ) return;

            io.print("luku 2: ");
            int luku2 = io.nextInt();
            if ( luku2==-9999 ) return;

            int vastaus = laskeSumma(luku1, luku2);
            io.print("summa: "+vastaus+"\n");
        }
    }

    private int laskeSumma(int luku1, int luku2) {
        return luku1+luku2;
    }

}
```

Ja laskimelle voidaan antaa IO-luokasta sopiva toteutus _injektoimalla_ eli antamalla se konstruktorin parametrina:

``` java
public class Main {
    public static void main(String[] args) {
        Laskin laskin = new Laskin( new KonsoliIO() );
        laskin.suorita();
    }
}
```

### Testaus

Ohjelmalle on nyt helppo tehdä yksikkötestit. Testejä varten toteutetaan IO-rajapinnan toteuttava "stubi":

``` java
class IOStub implements IO {

    int[] inputs;
    int mones;
    ArrayList<String> outputs;

    public IOStub(int... inputs) {
        this.inputs = inputs;
        this.outputs = new ArrayList<String>();
    }

    public int nextInt() {
        return inputs[mones++];
    }

    public void print(String m) {
        outputs.add(m);
    }
}
```

Stubille voidaan siis antaa "käyttäjän syötteet" konstruktorin parametrina. Ohjelman tulosteet saadaan suorituksen jälkeen kysyttyä stubilta.

Testi seuraavassa:

``` java
public class LaskinTest {

    @Test
    public void yksiSummaOikein() {
        IOStub io = new IOStub(1, 3, -9999);
        new Laskin(io).suorita();

        assertEquals("summa: 4\n", io.outputs.get(2));
    }
}
```

Laskimen tarjastelu jatkuu osoitteessa
<https://github.com/mluukkai/ohtu2017/blob/master/web/riippuvuuksien_injektointi_spring.md>
ensin kannattaa kuitenkin tehdä tehtävät 1-9.
