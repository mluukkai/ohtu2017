## koheesio metoditasolla

Esimerkki artikkelista [http://www.ibm.com/developerworks/java/library/j-eaed4/index.html](http://www.ibm.com/developerworks/java/library/j-eaed4/index.html)

``` java
public void populate() throws Exception  {
    Connection c = null;
    try {
        c = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(SQL_SELECT_PARTS);
        while (rs.next()) {
            Part p = new Part();
            p.setName(rs.getString("name"));
            p.setBrand(rs.getString("brand"));
            p.setRetailPrice(rs.getDouble("retail_price"));
            partList.add(p);
        }
    } finally {
        c.close();
    }
}
```

Metodissa tehdään montaa asiaa:

* luodaan yhteys tietokantaan
* tehdään tietokantakysely
* käydään kyselyn tulosrivit läpi ja luodaan jokaista tulosriviä kohti Part-olio
* suljetaan yhteys

Ikävänä seurauksena tästä on myös se, että metodi toimii monella abstraktiotasolla. Toisaalta käsitellään teknisiä tietokantatason asioita kuten tietokantayhteyden avaamista ja kyselyn tekemistä, toisaalta "bisnestason" olioita.

Metodi on helppo __refaktoroida__ pilkkomalla se pienempiin osiin joiden kutsumista alkuperäinen metodi koordinoi.

``` java
public void populate() throws Exception {
    Connection c = null;
    try {
        c = getDatabaseConnection();
        ResultSet rs = createResultSet(c);
        while (rs.next()){
            addPartToListFromResultSet(rs);
        }
    } finally {
        c.close();
    }
}

private ResultSet createResultSet(Connection c)throws SQLException {
    return c.createStatement().
            executeQuery(SQL_SELECT_PARTS);
}

private Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
    return DriverManager.getConnection(DB_URL,"webuser", "webpass");
}

private void addPartToListFromResultSet(ResultSet rs) throws SQLException {
    Part p = new Part();
    p.setName(rs.getString("name"));
    p.setBrand(rs.getString("brand"));
    p.setRetailPrice(rs.getDouble("retail_price"));
    partList.add(p);
}
```

Yksittäiset metodit ovat nyt kaikki samalla abstraktiotasolla toimivia ja hyvin nimettyjä.

Nyt aikaansaatu lopputulos ei ole vielä välttämättä ideaali koko ohjelman kontekstissa. [Artikkelissa](http://www.ibm.com/developerworks/java/library/j-eaed4/index.html) esimerkkiä jatketaankin eristäen tietokantaoperaatiot (joita myös muut ohjelman osat tarvitsevat) omaan luokkaansa.

## Single responsibility -periaate eli koheesio luokkatasolla

Kurssin alussa tarkastelimme yksinkertaista laskinta:

``` java
public class Laskin {

    private Scanner lukija;

    public Laskin() {
        lukija = new Scanner(System.in);
    }

    public void suorita(){
        while( true ) {
            System.out.print("luku 1: ");
            int luku1 = lukija.nextInt();
            if ( luku1==-9999  ) return;

            System.out.print("luku 2: ");
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

Luokka rikkoo Single responsibility -periaatteen? Miksi? Periaate sanoo, että luokalla saa olla vain yksi vastuu eli syy muuttuua. Nyt luokalla on kuitenkin useita syitä muuttua:

* luokalle halutaan toteuttaa uusia laskutoimituksia
* kommunikointi käyttäjän kanssa halutaan hoitaa jotenkin muuten kuin konsolin välityksellä

Eriyttämällä käyttäjän kanssa kommunikointi omaan luokkaan ja eristämällä se rajapinnan taakse (eli kapseloimalla kommunikoinnin toteutustapa) saadaan luokan Laskin vastuita vähennettyä:

``` java
public interface IO {
    int nextInt();
    void print(String m);
}

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

Nyt kommunikointitavan muutos ei edellytä luokkaan mitään muutoksia edellyttäen että uusikin kommunikoinitapa toteuttaa rajapinnan jonka kautta Laskin hoitaa kommunikoinnin.

Vaikka luokka Laskin siis toteuttaakin edelleen käyttäjänsä näkökulmasta samat asiat kuin aiemmin, ei se hoida kaikkea itse vaan delegoi osan vastuistaan muualle.

Luokka ei ole vielä kaikin osin laajennettavuuden kannalta optimaalinen. Palaamme asiaan hetken kuluttua.

## Favour composition over inheritance eli milloin ei kannata periä

Meillä on käytössämme luokka joka mallintaa pankkitiliä:

``` java
public class Tili {
    private String tiliNumero;
    private String omistaja;
    private double saldo;
    private double korkoProsentti;

    public Tili(String tiliNumero, String omistaja, double korkoProsentti) {
        this.tiliNumero = tiliNumero;
        this.omistaja = omistaja;
        this.korkoProsentti = korkoProsentti;
    }

    public boolean siirraRahaaTililta(Tili tilille, double summa){
        if ( this.saldo<summa ) return false;

        this.saldo -= summa;
        tilille.saldo += summa;

        return true;
    }

    public void maksaKorko(){
        saldo += saldo*korkoProsentti*100;
    }
}
```

Huomaamme, että tulee tarve toisentyyppiselle tilille joko 1, 3, 6 tai 12 kuukaiden Euribor-korkoon perustuvalle tilille. päätämme tehdä uuden luokan EuriborTili perimällä luokan Tili ja ylikirjoittamalla metodin maksaKorko siten että Euribor-koron senhetkinen arvo haetaan verkosta:

``` java
public class EuriborTili extends Tili {
    private int kuukauden;

    public EuriborTili(String tiliNumero, String omistaja, int kuukauden) {
        super(tiliNumero, omistaja, 0);
        this.kuukauden = kuukauden;
    }

    @Override
    public void maksaKorko() {
        saldo += saldo*korko()*100;
    }

    private double korko() {
        try {
            Scanner lukija = new Scanner(new URL("http://www.euribor-rates.eu/current-euribor-rates.asp").openStream());
            int count = 0;
            while (lukija.hasNextLine()) {
                String sisalto = lukija.nextLine();
                if (sisalto.contains("Euribor - "+kuukauden+" month") && count==0){
                    count = 1;
                } else if (sisalto.contains("Euribor - "+kuukauden+" month") && count==1){
                    lukija.nextLine();
                    lukija.nextLine();
                    sisalto = lukija.nextLine();
                    return Double.parseDouble(sisalto.substring(0, sisalto.length()-1))/100;
                }
            }      
            
        } catch (Exception e) {}
        return 0;
    }
}
```

Huomaamme, että EuriborTili rikkoo Single Responsibility -periaatetta, sillä luokka sisältää normaalin tiliin liittyvän toiminnan lisäksi koodia joka hakee tavaraa internetistä. Vastuut kannattaa selkeyttää ja korkoprosentin haku eriyttää omaan rajapinnan takana olevaan luokkaan:

``` java
public interface EuriborLukija {
    double korko();
}

public class EuriborTili extends Tili {
    private EuriborLukija euribor;

    public EuriborTili(String tiliNumero, String omistaja, int kuukauden) {
        super(tiliNumero, omistaja, 0);
        euribor = new EuriborlukijaImpl(kuukauden);
    }

    @Override
    public void maksaKorko() {
        saldo += saldo*euribor.korko()*100;
    }

}

public class EuriborlukijaImpl implements EuriborLukija {
    private int kuukauden;

    public EuriborlukijaImpl(int kuukauden) {
        this.kuukauden = kuukauden;
    }

    @Override
    public double korko() {
        try {
            Scanner lukija = new Scanner(new URL("http://www.euribor-rates.eu/current-euribor-rates.asp").openStream());
            int count = 0;
            while (lukija.hasNextLine()) {
                String sisalto = lukija.nextLine();
                if (sisalto.contains("Euribor - "+kuukauden+" month") && count==0){
                    count = 1;
                } else if (sisalto.contains("Euribor - "+kuukauden+" month") && count==1){
                    lukija.nextLine();
                    lukija.nextLine();
                    sisalto = lukija.nextLine();
                    return Double.parseDouble(sisalto.substring(0, sisalto.length()-1))/100;
                }
            }      
            
        } catch (Exception e) {}
        return 0;
    }
}
```

EuriborTili-luokka alkaa olla nyt melko siisti, EuriborLukijassa olisi paljon parantemisen varaa, mm. sen ainoan metodin koheesio on huono, metodi tekee aivan liian montaa asiaa. Palaamme siihen kuitenkin myöhemmin.

Seuraavaksi huomaamme että on tarvetta Määräaikaistilille joka on muuten samanlainen kuin Tili mutta määräaikaistililtä ei voi siirtää rahaa muualle ennen kuin se tehdään mahdolliseksi tietyn ajan kuluttua. Eli ei ongelmaa, perimme jälleen luokan Tili:

``` java
public class MääräaikaisTili extends Tili {
    private boolean nostokielto;

    public MääräaikaisTili(String tiliNumero, String omistaja, double korkoProsentti) {
        super(tiliNumero, omistaja, korkoProsentti);
        nostokielto = true;
    }

    public void salliNosto(){
        nostokielto = false;
    }

    @Override
    public boolean siirraRahaaTililta(Tili tilille, double summa) {
        if ( nostokielto )
            return false;

        return super.siirraRahaaTililta(tilille, summa);
    }

}
```

Luokka syntyi tuskattomasti.

Seuraavaksi tulee idea Euribor-korkoa käyttävistä määräaikaistileistä. Miten nyt kannattaisi tehdä? Osa toiminnallisuudesta on luokassa Määräaikaistili ja osa luokassa Euribor-tili...

Ehkä koronmaksun hoitaminen perinnän avulla ei ollutkaan paras ratkaisu, ja kannattaisi noudattaa "favor composition over inheritance"-periaatetta. Eli erotetaan koronmaksu omaksi luokakseen, tai rajapinnan toteuttaviksi luokiksi:

``` java
public interface Korko {
    double korko();
}

public class Tasakorko implements Korko {
    private double korko;

    public Tasakorko(double korko) {
        this.korko = korko;
    }

    public double korko() {
        return korko;
    }
}

public class EuriborKorko implements Korko {
    EuriborLukija lukija;

    public EuriborKorko(int kuukausi) {
        lukija = new EuriborlukijaImpl(kuukausi);
    }

    public double korko() {
        return korko();
    }
}
```

Nyt tarve erilliselle EuriborTili-luokalle katoaa, ja pelkkä Tili muutettuna riittää:

``` java
public class Tili {
    private String tiliNumero;
    private String omistaja;
    private double saldo;
    private Korko korko;

    public Tili(String tiliNumero, String omistaja, Korko korko) {
        this.tiliNumero = tiliNumero;
        this.omistaja = omistaja;
        this.korko = korko;
    }

    public boolean siirraRahaaTililta(Tili tilille, double summa){
        if ( this.saldo<summa ) return false;

        this.saldo -= summa;
        tilille.saldo += summa;

        return true;
    }

    public void maksaKorko(){
        saldo += saldo*korko.korko()*100;
    }
}
```

Erilaisia tilejä luodaan nyt seuraavasti:

``` java
Tili normaali = new Tili("1234-1234", "Kasper Hirvikoski", new Tasakorko(4));
Tili euribor12 = new Tili("4422-3355", "Tero Huomo", new EuriborKorko(12));
```

Muutetaan luokkaa vielä siten, että tilejä saadaan luotua ilman konstruktoria:

``` java
public class Tili {

    private String tiliNumero;
    private String omistaja;
    private double saldo;
    private Korko korko;

    public static Tili luoEuriborTili(String tiliNumero, String omistaja, int kuukausia) {
        return new Tili(tiliNumero, omistaja, new EuriborKorko(kuukausia));
    }

    public static Tili luoMääräaikaisTili(String tiliNumero, String omistaja, double korko) {
        return new MääräaikaisTili(tiliNumero, omistaja, new Tasakorko(korko));
    }

    public static Tili luoKäyttöTili(String tiliNumero, String omistaja, double korko) {
        return new Tili(tiliNumero, omistaja, new Tasakorko(korko));
    }

    protected Tili(String tiliNumero, String omistaja, Korko korko) {
        this.tiliNumero = tiliNumero;
        this.omistaja = omistaja;
        this.korko = korko;
    }

    // ...

    public void vaihdaKorkoa(Korko korko) {
        this.korko = korko;
    }
}
```

Lisäsimme luokalle 3 staattista apumetodia helpottamaan tilien luomista. Tilejä voidaan nyt luoda seuraavasti:

``` java
Tili määräaikais = Tili.luoMääräaikaisTili("1234-1234", "Kasper Hirvikoski", 2.5);
Tili euribor12 = Tili.luoEuriborTili("4422-3355", "Tero Huomo", 12 );
Tili fyrkka = Tili.luoEuriborTili("7895-4571", "Esko Ukkonen", 10.75 );
```

Käyttämämme periaate olioiden luomiseen staattisten metodien avulla on hyvin tunnettu suunnittelumalli *staattinen tehdas, engl. static factory*.

Huomaamme, että tehdasmetodien avulla voimme kapseloida luokan todellisen tyypin. Kasperin tilihän on määräaikaistili, se kuitenkin pyydetään Tili-luokassa sijaitsevalta factoryltä, olion oikea tyyppi on piilotettu tarkoituksella käyttäjältä. Määräaikaistilin käyttäjällä ei siis ole enää konkreettista riippuvuutta luokkaan Määräaikaistili.

Teimme myös metodin jonka avulla tilin korkoa voi muuttaa. Kasperin tasakorkoinen määräaikaistili on helppo muuttaa lennossa kolmen kuukauden Euribor-tiliksi:

``` java
määräaikais.vaihdaKorkoa(new EuriborKorko(3));
```

Eli luopumalla perinnästä selkeytyy oliorakenne huomattavasti ja saavutetaan ajonaikaista joustavuuttaa (koronlaskutapa) joka perintää käyttämällä ei onnistu.

Tekniikka jolla koronmaksu hoidetaan on myöskin suunnittelumalli nimeltään *strategia eli englanniksi strategy*.

## Tilin luominen

Loimme äsken luokalle _Tili_ staattiset apumetodit tilien luomista varten. Voisi kuitenkin olla järkevämpää siirtää vastuu tilien luomisesta erillisen luokan, _pankin_ vastuulle. Pankki voi helposti hallinnoida myös tilinumeroiden generointia:

``` java
public class Pankki {
    private int numero;
        
    private String generoiTilinro() {
        numero++;
        return "12345-"+numero;
    }
    
    public Tili kayttotili(String omistaja, double k){
        return new Tili(generoiTilinro(), omistaja, new Tasakorko(k));
    }
    
    public Tili maaraikaistili(String omistaja, double k){
        return new MaaraAikaisTili(generoiTilinro(), omistaja, new Tasakorko(k));
    }    
    
    public Tili euribortili(String tiliNumero, String omistaja, int kk){
        return new Tili(generoiTilinro(), omistaja, new EuriborKorko(kk));
    }        

    public Tili maaraaikaisEuribor(String tiliNumero, String omistaja, int kk){
        return new MaaraAikaisTili(tiliNumero, omistaja, new EuriborKorko(kk));
    } 
}
```

Tilejä luodaan pankin avulla seuraavasti:

``` java
Pankki spankki = new Pankki();

Tili euriborTili = spankki.euribortili("Kasper Hirvikoski", 6);
Tili maaraaikaistili = spankki.maaraikaistili("Arto Hellas", 0.15);
``` 

eli tlilin luojan ei enää tarvitse huolehtia tilinumeroiden generoinnista.

## Laskin ilman iffejä

Olemme laajentaneet Laskin-luokkaa osaamaan myös muita laskuoperaatioita:

``` java
public class Laskin {

    private IO io;

    public Laskin(IO io) {
        this.io = io;
    }

    public void suorita() {
        while (true) {
            io.print("komento: ");
            String komento = io.nextLine();
            if (komento.equals("lopetus")) {
                return;
            }

            io.print("luku 1: ");
            int luku1 = io.nextInt();

            io.print("luku 2: ");
            int luku2 = io.nextInt();

            int vastaus = 0;

            if ( komento.equals("summa") ){
                vastaus = laskeSumma(luku1, luku2);
            } else if ( komento.equals("tulo") ){
                vastaus = laskeTulo(luku1, luku2);
            } else if ( komento.equals("erotus") ){
                vastaus = laskeErotus(luku1, luku2);
            }

            io.print("summa: " + vastaus + "\n");
        }
    }

    private int laskeSumma(int luku1, int luku2) {
        return luku1 + luku2;
    }

    private int laskeTulo(int luku1, int luku2) {
        return luku1 * luku2;
    }

    private int laskeErotus(int luku1, int luku2) {
        return luku1-luku2;
    }
}
```

Ratkaisu ei ole kaikin puolin tyydyttävä. Entä jos haluamme muitakin operaatioita kuin summan, tulon ja erotuksen? if-hässäkkä tulee kasvamaan.

Päätämme siirtyä strategian käyttöön, eli hoidetaan laskuoperaatio omassa luokassaan. Rajapinnan sijasta käytämme tällä kertaa abstraktia luokkaa:

``` java
public abstract class Operaatio {

    protected int luku1;
    protected int luku2;

    public Operaatio(int luku1, int luku2) {
        this.luku1 = luku1;
        this.luku2 = luku2;
    }

    public static Operaatio luo(String operaatio, int luku1, int luku2) {
        if (operaatio.equals("summa")) {
            return new Summa(luku1, luku2);
        } else if (operaatio.equals("tulo")) {
            return new Tulo(luku1, luku2);
        }
        return new Erotus(luku1, luku2);
    }

    public abstract int laske();
}

public class Summa extends Operaatio {

    public Summa(int luku1, int luku2) {
        super(luku1, luku2);
    }

    @Override
    public int laske() {
        return luku1 + luku2;
    }
}

public class Tulo extends Operaatio {

    public Tulo(int luku1, int luku2) {
        super(luku1, luku2);
    }

    @Override
    public int laske() {
        return luku1 * luku2;
    }
}

public class Erotus extends Operaatio {

    public Erotus(int luku1, int luku2) {
        super(luku1, luku2);
    }

    @Override
    public int laske() {
        return luku1 - luku2;
    }
}
```

Laskin-luokka yksinkertaistuu huomattavasti:

``` java
public class Laskin {

    private IO io;

    public Laskin(IO io) {
        this.io = io;
    }

    public void suorita() {
        while (true) {
            io.print("komento: ");
            String komento = io.nextLine();
            if (komento.equals("lopetus")) {
                return;
            }

            io.print("luku 1: ");
            int luku1 = io.nextInt();

            io.print("luku 2: ");
            int luku2 = io.nextInt();

            Operaatio operaatio = Operaatio.luo(komento, luku1, luku2);

            io.print("summa: " + operaatio.laske() + "\n");
        }
    }
}
```

Hienona puolena laskimessa on nyt se, että voimme lisätä operaatioita ja Laskinta ei tarvitse muuttaa millään tavalla!

Entä jos haluamme laskimelle muunkinlaisia kuin 2 parametria ottavia operaatioita, esim. neliöjuuren?

Jatkamme muokkaamista seuraavassa luvussa

## laskin ja komento-olio

Muutamme Operaatio-luokan olemusta, päädymme jo oikeastaan Strategy-suunnittelumallin lähisukulaisen Command-suunnittelumallin puolelle ja annammekin sille nimen Komento ja teemmie siitä rajapinnan sillä siirrämme erillisten komento-olioiden luomisen Komentotehdas-luokalle:

``` java
public interface Komento {
    void suorita();
}
```

Komento-rajapinta on siis äärimmäisen yksinkertainen. Komennon voi ainoastaan suorittaa eikä se edes palauta mitään!

Komento-olioita luova komentotehdas on seuraavassa:

``` java
public class Komentotehdas {

    private IO io;

    public Komentotehdas(IO io) {
        this.io = io;
    }

    public Komento hae(String operaatio) {
        if (operaatio.equals("summa")) {
            return new Summa(io);
        } else if (operaatio.equals("tulo")) {
            return new Tulo(io);
        } else if (operaatio.equals("nelio")) {
            return new Nelio(io);
        } else if (operaatio.equals("lopeta")) {
            return new Lopeta();
        }
        return new Tuntematon(io);
    }
}
```
Komentotehdas siis palauttaa hae-metodin merkkijonoparametria vastaavan komennon. Koska vastuu käyttäjän kanssa kommunikoinnista on siirretty Komento-olioille, annetaan niille IO-olio konstruktorissa.

if-hässäkkä näyttää hieman ikävältä. Mutta hetkinen! Voisimme tallentaa erilliset komennon HashMap:iin:

``` java
public class Komentotehdas {
    private HashMap<String, Komento> komennot;

    public Komentotehdas(IO io) {
        komennot = new HashMap<String, Komento>();
        komennot.put("summa", new Summa(io));
        komennot.put("tulo", new Tulo(io));
        komennot.put("nelio", new Nelio(io));
        komennot.put("tuntematon", new Tuntematon(io));
    }

    public Komento hae(String operaatio) {
        Komento komento = komennot.get(operaatio);
        if (komento == null) {
            komento = komennot.get("tuntematon");
        }
        return komento;
    }
}
```

Pääsimme kokonaan eroon if-ketjusta, loistavaa!

Yksittäiset komennot ovat hyvin yksinkertaisia:

``` java
public class Nelio implements Komento {
    private IO io;

    public Nelio(IO io) {
        this.io = io;
    }

    @Override
    public void suorita() {
        io.print("luku 1: ");
        int luku = io.nextInt();

        io.print("vastaus: "+luku*luku);
    }
}

public class Tuntematon implements Komento {
    private IO io;

    public Tuntematon(IO io) {
        this.io = io;
    }

    @Override
    public void suorita() {
        io.print("sallitut komennot: summa, tulo, nelio, lopeta");
    }
}

public class Lopeta implements Komento {
    private IO io;

    public Lopeta(IO io) {
        this.io = io;
    }

    @Override
    public void suorita() {
        io.print("kiitos ja näkemiin");
        System.exit(0);
    }

}
```

Koska kaksi parametria käyttäjältä kysyvillä komennoilla on paljon yhteistä, luodaan niitä varten yliluokka:

``` java
public abstract class KaksiparametrinenLaskuoperaatio implements Komento {

    protected IO io;
    protected int luku1;
    protected int luku2;

    public KaksiparametrinenLaskuoperaatio(IO io) {
        this.io = io;
    }

    @Override
    public void suorita() {
        io.print("luku 1: ");
        int luku1 = io.nextInt();

        io.print("luku 2: ");
        int luku2 = io.nextInt();

        io.print("vastaus: "+laske());
    }

    protected abstract int laske();
}

public class Summa extends KaksiparametrinenLaskuoperaatio {

    public Summa(IO io) {
        super(io);
    }

    @Override
    protected int laske() {
        return luku1+luku2;
    }
}

public class Tulo extends KaksiparametrinenLaskuoperaatio {

    public Tulo(IO io) {
        super(io);
    }

    @Override
    public int laske() {
        return luku1*luku2;
    }
}
```

Ja lopulta luokka Laskin, jossa ei ole enää juuri mitään jäljellä:

``` java
public class Laskin {

    private IO io;
    private Komentotehdas komennot;

    public Laskin(IO io) {
        this.io = io;
        komennot = new Komentotehdas(io);
    }

    public void suorita() {
        while (true) {
            io.print("komento: ");
            String komento = io.nextLine();
            komennot.hae(komento).suorita();
        }
    }
}
```

Ohjelmasta on näinollen saatu laajennettavuudeltaan varsin joustava. Uusia operaatioita on helppo lisätä ja lisäys ei aiheuta muutoksia moneen kohtaan koodia. Laskin-luokallahan ei ole riippuvuuksia muualle kuin rajapintoihin IO ja Komento ja luokkaan Komentotehdas.

Hintana joustavuudelle on luokkien määrän kasvu. Nopealla vilkaisulla saattaakin olla vaikea havaita miten ohjelma toimii, varsinkaan jos ei ole vastaavaan tyyliin tottunut, mukaan on nimittäin piilotettu factory- ja command-suunnittelumallien lisäksi suunnittelumalli __template method__ (kaksiparametrisen komennon toteutukseen). Luokka- ja sekvenssikaavion piirtäminen lienee paikallaan.

Yksinkertaisessa ohjelmassa ei tietenkään ole järkeä tehdä ohjelman rakenteesta näin joustavaa.

## Koodissa olevan epätriviaalin copypasten poistaminen Strategy-patternin avulla, Java 8:a hyödyntävä versio

Tarkastellaan [Project Gutenbergistä](http://www.gutenberg.org/) löytyvien kirjojen sisällön analysointiin tarkoitettua luokkaa <code>GutenbergLukija</code>:

``` java
public class GutenbergLukija {

    private List<String> rivit;

    public GutenbergLukija(String osoite) throws IllegalArgumentException {
        rivit = new ArrayList<String>();
        try {
            URL url = new URL(osoite);
            Scanner lukija = new Scanner(url.openStream());
            while (lukija.hasNextLine()) {
                rivit.add(lukija.nextLine());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<String> rivit() {
        List<String> palautettavat = new ArrayList<>();

        for (String rivi : rivit) {
            palautettavat.add(rivi);
        }

        return palautettavat;
    }

    public List<String> rivitJotkaPaattyvatHuutomerkkiin() {
        List<String> ehdonTayttavat = new ArrayList<>();

        for (String rivi : rivit) {
            if (rivi.endsWith("!")) {
                ehdonTayttavat.add(rivi);
            }
        }

        return ehdonTayttavat;
    }

    public List<String> rivitJoillaSana(String sana) {
        List<String> ehdonTayttavat = new ArrayList<String>();

        for (String rivi : rivit) {
            if (rivi.contains(sana)) {
                ehdonTayttavat.add(rivi);
            }
        }

        return ehdonTayttavat;
    }
}
```

Luokalla on kolme metodia, kaikki kirjan rivit palauttava <code>rivit</code> sekä <code>rivitJotkaPaattyvatHuutomerkkiin</code> ja <code>rivitJoillaSana(String sana)</code> jotka toimivat kuten metodin nimi antaa ymmärtää.

Luokkaa käytetään seuraavasti:

``` java
    public static void main(String[] args) {
        String osoite = "https://www.gutenberg.org/files/2554/2554-0.txt";
        GutenbergLukija kirja = new GutenbergLukija(osoite);

        for( String rivi : kirja.rivitJoillaSana("beer") ) {
            System.out.println(rivi)
        }
    }
```

Tutustutaan tehtävässä hieman [Java 8:n](http://docs.oracle.com/javase/8/docs/api/) tarjoamiin uusiin ominaisuuksiin. Voimme korvata listalla olevien merkkijonojen tulostamisen kutsumalla listoilla (tarkemmin sanottuna rajapinnan [Interable](http://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html)-toteuttavilla) olevaa metodia <code>forEach</code> joka mahdollistaa listan alkioiden läpikäynnin "funktionaaliseen" tyyliin. Metodi saa parametrikseen "functional interfacen" (eli rajapinnan, joka määrittelee ainoastaan yhden toteutettavan metodin) toteuttavan olion. Tälläisiä ovat Java 8:ssa myös ns. lambda-lausekkeet (lambda expression), joka tarkoittaa käytännössä anonyymia mihinkään luokkaan liittymätöntä metodia.  Seuraavassa metodin palauttavien kirjan rivien tulostus forEachia ja lambdaa käyttäen:


``` java
    public static void main(String[] args) {
        String osoite = "https://www.gutenberg.org/files/2554/2554-0.txt";
        GutenbergLukija kirja = new GutenbergLukija(osoite);

        kirja.rivitJoillaSana("beer").forEach(s->System.out.println(s));
    }
```

Esimerkissä lambdan syntaksi oli seuraava:

``` java
    s->System.out.println(s)
```

parametri <code>s</code> saa arvokseen yksi kerrallaan kunkin läpikäytävän tekstirivin. Riveille suoritetaan "nuolen" oikealla puolella oleva tulostuskomento. Lisää lambdan syntaksista [täältä](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html). Huomionarvoista on se, että lambdan parametrin eli muuttujan <code>s</code> tyyppiä ei tarvitse määritellä, kääntäjä osaa päätellä sen iteroitavana olevan kokoelman perusteella.

Luokan <code>GutenbergLukija</code> tarjoamat 3 kirjan sisällön hakemiseen tarkoitettua metodia ovat selvästi rakenteeltaan hyvin samantapaisia. Kaikki käyvät jokaisen kirjan rivin läpi ja palauttavat niistä osan (tai kaikki) metodin kutsujalle. Metodit eroavat sen suhteen mitä kirjan riveistä ne palauttavat. Voidaankin ajatella, että jokaisessa metodissa on oma _strategiansa_ rivien palauttamiseen. Eriyttämällä rivien valintastrategia omaksi luokakseen, voitaisiin selvitä ainoastaan yhdellä rivien läpikäynnin hoitavalla metodilla.

Määritellään rivien valintaa varten rajapinta:

``` java
public interface Ehto {
    boolean test(String rivi);
}
```

Huom: metodin nimen valinta ei ollut täysin sattumanvarainen. Tulemme myöhemmin määrittelemään, että rajapinta <code>Ehto</code> laajentaa rajapinnan, joka vaatii että rajapinnalla on nimenomaan <code>test</code>-niminen metodi.

Ideana on luoda jokaista kirjojen erilaista _hakuehtoa_ kohti oma rajapinnan <code>Ehto</code> toteuttava luokka.

Seuraavassa ehto-luokka, joka tarkastaa sisältyykö tietty sana riville:

``` java
public class SisaltaaSanan implements Ehto {
    private String sana;

    public SisaltaaSanan(String sana) {
        this.sana = sana;
    }

    @Override
    public boolean test(String rivi) {
        return rivi.contains(sana);
    }
}
```

Jos luokasta luodaan ilmentymä

``` java
    Ehto ehto = new SisaltaaSanan("olut");
```

voidaan luokan avulla tarkastella sisältävätkö merkkijonot sanan _olut_:


``` java
    Ehto ehto = new SisaltaaSanan("olut");
    ehto.test("internetin paras suomenkielinen olutsivusto on olutopas.info");
    ehto.test("Java 8 ilmestyi 18.3.2014");
```

Ensimmäinen metodikutsuista palauttaisi _true_ ja jälkimäinen _false_.

Kirjasta voidaan nyt palauttaa oikean ehdon täyttävät sanat lisäämällä luokalle <code>GutenbergLukija</code> metodi:

``` java
    public List<String> rivitJotkaTayttavatEhdon(Ehto ehto) {
        List<String> palautettavatRivit = new ArrayList<>();

        for (String rivi : rivit) {
            if (ehto.test(rivi)) {
                palautettavatRivit.add(rivi);
            }
        }

        return palautettavatRivit;
    }
```

ja sanan _beer_ sisältävät rivit saadaan tulostettua seuraavasti:

``` java
    kirja.rivitJotkaTayttavatEhdon(new SisaltaaSanan("beer")).forEach(s->System.out.println(s));
```

Pääsemmekin sopivien ehto-luokkien määrittelyllä eroon alkuperäisistä rivien hakumetodeista. Sovellus tulee sikälikin huomattavasti joustavammaksi, että uusia hakuehtoja voidaan helposti lisätä määrittelemällä uusia rajapinnan <code>Ehto</code> määritteleviä luokkia.

Ehto-rajapinta on ns. _functional interface_ eli se määrittelee ainoastaan yhden toteutettavan metodin (huom: Java 8:ssa rajapinnat voivat määritellä myös [oletusarvoisen toteutuksen](http://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html) sisältämiä metodeja!). Java 8:n aikana voimme määritellä ehtoja myös lambda-lausekkeiden avulla. Eli ei ole välttämätöntä tarvetta määritellä eksplisiittisesti rajapinnan <code>Ehto</code> toteuttavia luokkia. Seuraavassa edellinen esimerkki käyttäen lambda-lauseketta ehdon määrittelemiseen:

``` java
kirja.rivitJotkaTayttavatEhdon(s->s.contains("beer")).forEach(s->System.out.println(s));
```

Käytännössä siis määrittelemme "lennossa" rajapinnan <code>Ehto</code> toteuttavan luokan, jonka ainoan metodin toiminnallisuuden määritelmä annetaan lambda-lausekkeen avulla.

Lambdojen avulla on helppoa määritellä mielivaltaisia ehtoja. Seuraavassa tulostetaan kaikki rivit, joilla esiintyy jompi kumpi sanoista _beer_ tai _vodka_. Ehdon ilmaiseva lambda-lauseke on nyt määritelty selvyyden vuoksi omalla rivillään:

``` java
    Ehto ehto = s -> s.contains("beer") || s.contains("vodka");

    kirja.rivitJotkaTayttavatEhdon(ehto).forEach(s->System.out.println(s));
```

Voimme hyödyntää Java 8:n uusia piirteitä myös luokan <code>GutenbergLukija</code> metodissa <code>rivitJotkaTayttavatEhdon</code>.

Metodi on tällä hetkellä seuraava:

``` java
    public List<String> rivitJotkaTayttavatEhdon(Ehto ehto) {
        List<String> palautettavatRivit = new ArrayList<>();

        for (String rivi : rivit) {
            if (ehto.test(rivi)) {
                palautettavatRivit.add(rivi);
            }
        }

        return palautettavatRivit;
    }
```

Java 8:ssa kaikki rajapinnan <code>Collection</code> toteuttavat luokat mahdollistavat alkioidensa käsittelyn <code>Stream</code>:ina eli "alkiovirtoina", ks. [API-kuvaus](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html). Kokoelmaluokasta saadaan sitä vastaava alkiovirta kutsumalla kokoelmalle metodia <code>stream</code>.

Alkiovirtoja on taas mahdollista käsitellä monin tavoin, ja meitä nyt kiinnostava metodi on <code>filter</code>, jonka avulla streamistä voidaan tehdä uusi streami, josta on poistettu ne alkiot, jotka eivät täytä filtterille annettua boolean-arvoista, funktionaalisen rajapinnan <code>Predicate<String></code> toteuttavaa ehtoa.

Määrittelemämme rajapinta <code>Ehto</code> on oikeastaan juuri tarkoitukseen sopiva, jotta voisimme käyttää rajapintaa, tulee meidän kuitenkin tyyppitarkastusten takia määritellä että rajapintamme laajentaa rajapintaa  <code>Predicate<String></code>:

``` java
import java.util.function.Predicate;

public interface Ehto extends Predicate<String>{
    boolean test(String rivi);
}
```

Nyt saamme muutettua kirjan rivien streamin _ehdon_ täyttävien rivien streamiksi seuraavasti:


``` java
    public List<String> rivitJotkaTayttavatEhdon(Ehto ehto) {
       // ei toimi vielä
       rivit.stream().filter(ehto)
    }
```

Metodin tulee palauttaa filtteröidyn streamin alkioista koostuva lista. Stream saadaan muutettua listaksi "keräämällä" sen sisältämät alkiot kutsumalla streamille metodia <code>collect</code> ja määrittelemällä, että palautetaan streamin sisältämät alkiot niemenomaan listana. Näin luotu filtteröity lista voidaan sitten palauttaa metodin kutsujalle.

Metodi on siis seuraavassa:

``` java
    public List<String> rivitJotkaTayttavatEhdon(Ehto ehto) {
        return rivit.stream().filter(ehto).collect(Collectors.toList());
    }
```

Kuten huomaamme, Javan version 8 tarjoamat funktionaaliset piirteet muuttavat lähes vallankumouksellisella tavalla kielen ilmaisuvoimaa!


## Komposiitti

Dokumentti koostuu erilaisista elementeistä. Elementtejä ovat mm.

* normaalit tekstielementit
* erotinelementit, erotin tulostuu viivana
* kooste-elementit
  * sisältävät listan elementtejä
  * kooste tulostuu samoin kuin sen sisältämän elementtilistan elementit tulostuvat

Haluamme käyttää dokumenttia seuraavaan tapaan:

``` java
public static void main(String[] args) {
    Dokumentti doku = new Dokumentti();

    Elementti detalji = Elementtitehdas.kooste(
            Elementtitehdas.teksti("kannattaa myös huomata builderi"),
            Elementtitehdas.teksti("sopii joihinkin tilanteisiin factoryä paremmin"));


    Elementti asiaa = Elementtitehdas.kooste(
            Elementtitehdas.teksti("Factory-metodit helpottavat olioiden luomista"),
            Elementtitehdas.teksti("ei tarvetta new:lle ja konkreettiset riippuvuudet vähenevät"),
            detalji);

    doku.lisaa(Elementtitehdas.teksti("Suunnittelumallit"));
    doku.lisaa(Elementtitehdas.erotin());
    doku.lisaa(asiaa);
    doku.lisaa(Elementtitehdas.teksti("yhteenvetona voidaan todeta, että kannattaa käyttää"));
    doku.lisaa(Elementtitehdas.erotin());

    doku.print();
    doku.tallenna("suunnittelumallit.txt");
}
```

Tulostuu:

<pre>
Suunnittelumallit
-------------------------
Factory-metodit helpottavat olioiden luomista
ei tarvetta new:lle ja konkreettiset riippuvuudet vähenevät
kannattaa myös huomata builderi
sopii joihinkin tilanteisiin factoryä paremmin
yhteenvetona voidaan todeta, että kannattaa käyttää
-------------------------
</pre>

Luokka Dokumentti on suoraviivainen:

``` java
public class Dokumentti {
    private List<Elementti> elementit;

    public Dokumentti() {
        elementit = new ArrayList<Elementti>();
    }

    public void lisaa(Elementti elementti){
        elementit.add(elementti);
    }

    public void print(){
        for (Elementti elementti : elementit) {
            elementti.tulosta();
        }
    }

    public void tallenna(String tiedosto){
        // to be implemented
    }
}
```

Käyttäjää varten on siis luotu elementtitehdas jonka avulla elementtejä voidaan muodostaa:

``` java
public class Elementtitehdas {
    public static Elementti erotin(){
        return new ErotinElementti();
    }

    public static Elementti teksti(String teksti){
        return new TekstiElementti(teksti);
    }

    public static Elementti kooste(Elementti... elementit){
        return new KoosteElementti(elementit);
    }
}
```
Ainoa huomionarvoinen seikka on viimeisen rakentajametodin varargs-tyyppinen parametri, jos se ei ole tuttu, ks esim: [http://www.javadb.com/using-varargs-in-java](http://www.javadb.com/using-varargs-in-java)

Käytännössä varargs-parametri tarkoittaa, että metodilla saa olla Elementti-tyyppisiä parametreja vapaavalintainen määrä.

Dokumentin sisältävien elementtien toteuttamiseen sopii erinomaisesti *komposiitti (engl composite) -suunnittelumalli*, ks. esim. [http://sourcemaking.com/design_patterns/composite](http://sourcemaking.com/design_patterns/composite)

Elementti on rajapinta joka määrittelee kaikkien elementtien yhteisen toiminnallisuuden:

``` java
public interface Elementti {
    void tulosta();
}
```

Yksinkertaiset elementit ovat triviaaleja:

``` java
public class ErotinElementti implements Elementti{

    public void tulosta() {
        System.out.println("-------------------------");
    }

}

public class TekstiElementti implements Elementti {

    String teksti;

    public TekstiElementti(String teksti) {
        this.teksti = teksti;
    }

    public void tulosta() {
        System.out.println(teksti);
    }
}
```

KoosteElementti sisältää listan elementtejä, lista annetaan konstruktorin parametrina, jälleen varargsia hyödyntäen. Kooste tulostaa itsensä pyytämällä kaikkia osiaan tulostumaan:

``` java
public class KoosteElementti implements Elementti {
    private List<Elementti> osat;

    public KoosteElementti(Elementti... osat) {
        this.osat = new ArrayList<Elementti>(Arrays.asList(osat));
    }

    public void tulosta() {
        for (Elementti osa : osat) {
            osa.tulosta();
        }
    }

}
```

Koska KoosteElementti toteuttaa itsekin rajapinnan Elementti, tarkoittaa tämä että kooste voi sisältää koosteita. Eli hyvin yksinkertaisella luokkarakenteella saadaan aikaan mielivaltaisista puumaisesti muodostuneista elementeistä koostuvia dokumentteja!

Huomaamme, että <code>Elementti</code> on _funktionaalinen rejapinta_ eli se määrittelee ainoastaan yhden sen metodin joka rajapinnan toteuttavien luokkien on toteutettava. Kuten [edellisellä viikolla ](https://github.com/mluukkai/ohtu2014/blob/master/web/luento8.md#koodissa-olevan-ep%C3%A4triviaalin-copypasten-poistaminen-strategy-patternin-avulla-java-8a-hy%C3%B6dynt%C3%A4v%C3%A4-versio) totesimme Java 8:ssa voimme käyttää lambda-lausekkeita korvaamaan funktionaalisen rajapinnan toteuttavien luokkien instanssien tilalla. Koska luokat <code>TekstiElementti</code>, <code>ErotinElementti</code> ja <code>KoosteElementti</code> ovat niin yksinkertaisia, ei luokkia välttämättä tarvitse määritellä eksplisiittisesti. Voimmekin palauttaa elementtitehtaasta niiden tilalla sopivat lambda-lausekkeen avulla määritellyt elementit:

``` java
public class Elementtitehdas {
    public static Elementti erotin(){
        return ()->{ System.out.println("-------------------------"); };
    }

    public static Elementti teksti(String teksti){
        return ()->{ System.out.println(teksti); };
    }

    public static Elementti kooste(Elementti... elementit){
        return () -> { Stream.of(elementit).forEach(e->e.tulosta()); };
    }
}
```

Riittää siis että kukin tehdasmetodi palauttaa lambda-lausekkeen, joka määrittelee kyseessä olevan elementin metodin <code>tulosta</code> toiminnallisuuden.

## Proxy

Oletetaan että asiakas haluaa elementtityypin WebElementti joka kapseloi tietyssä www-osoitteessa olevan sisällön. Ei ongelmaa:

``` java
public class WebElementti implements Elementti {

    private String source;

    public WebElementti(String url) {
        try {
            Scanner lukija = new Scanner(new URL(url).openStream());
            while( lukija.hasNextLine()) {
                source+= lukija.nextLine();
            }
        } catch (Exception e) {
            source = "page "+url+" does not exist";
        }
    }

    public void tulosta() {
        System.out.println(source);
    }
}
```

Hieman ruma koodi (konstruktori tekee vähän liian monta asiaa), mutta toimii.

Laajentamalla elementtitehdasta sopivasti pääsemme käyttämään dokumentin uusia ominaisuuksia:


``` java
public static void main(String[] args) {
    Dokumentti doku = new Dokumentti();

    doku.lisaa(Elementtitehdas.web("http://www.jatkoaika.fi"));
    doku.lisaa(Elementtitehdas.web("http://olutopas.info/"));

    doku.tallenna("webista.html");
}
```

Asiaks toteaa, että hänellä on usein tarve koostaa "varalta" webelementtejä sisältäviä dokumentteja. Dokumenteista ei kuitenkaan todellisuudessa tarvita kuin muutamaa, niitä pitää olla kuitenkin määriteltynä valmiina suuria määriä.

Ongelmaksi muodostuu nyt se, että elementtien lataaminen webistä on hidasta. Ja on ikävää jos elementtejä on pakko ladata suuria määriä kaiken varalta.

Proxy-suunnittelumalli tuo ongelmaan ratkaisun. Periaatteena on luoda varsinaiselle "raskaalle" oliolle edustaja joka toimii raskaan olion sijalla niin kauan kunnes olioa oikeasti tarvitaan. Tälläisessä tilanteessa edustaja sitten luo todellisen olion ja delegoi sille kaikki operaatiot.

Tehdään WebElementille proxy:


``` java
public class WebElementtiProxy implements Elementti {
    private String url;
    private WebElementti webElementti;

    public WebElementtiProxy(String url) {
        this.url = url;
    }

    public void tulosta() {
        if ( webElementti==null ) {
            webElementti = new WebElementti(url);
        }
        webElementti.tulosta();
    }
}
```

Eli proxy luo varsinaisen olion vasta kun metodia tulosta() kutsutaan ensimmäisen kerran.

Elementtitehdas konfiguroidaan antamaan WebElementin käyttäjille proxy. Käyttäjät eivät eivät tiedä proxystä mitään ja luulevat käyttävänsä koko ajan täysimittaista olioa!


``` java
public class Elementtitehdas {
    // ...

    public static Elementti web(String url){
        return new WebElementtiProxy(url);
    }
}
```

Asiakas on tyytyväinen aikaansaannokseemme.
