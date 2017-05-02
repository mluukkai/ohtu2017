## Dekoroitu Random

Alla on yksinkertainen sovellus, joka arpoo viikon lottorivin ja antaa käyttäjän tarkastaa kuinka monta käyttäjän syöttämistä numeroista oli viikon lottonumeroita.

``` java
public class ViikonLottonumerot {

    public static void main(String[] args) {
        ViikonLottonumerot lotto = new ViikonLottonumerot();

        int[] omat = {1,4,7,8,33,24,12};
        System.out.println( "omat numerot: "+Arrays.toString(omat));
        System.out.println( "oikein: "+lotto.oikeita(omat));
        System.out.println( "arvottulottorivi oli: "+lotto );

    }

    private ArrayList<Integer> viikonLottorivi;

    public ViikonLottonumerot() {
        Random arpa = new Random();

        viikonLottorivi = new ArrayList<Integer>();
        int vielaArvottavana = 7;
        while (vielaArvottavana > 0) {
            int arvottu = 1 + arpa.nextInt(39);
            if (!viikonLottorivi.contains(arvottu)) {
                viikonLottorivi.add(arvottu);
                vielaArvottavana--;
            }
        }

    }

    public int oikeita(int[] omatNumerot) {
        if (omatNumerot.length != 7) {
            throw new IllegalArgumentException();
        }

        int oikein = 0;

        for (int numero : omatNumerot) {
            if (viikonLottorivi.contains(numero)) {
                oikein++;
            }
        }

        return oikein;
    }

    @Override
    public String toString() {
        return viikonLottorivi.toString();
    }

}
```

Haluamme automatisoida luokan __ViikonLottonumerot__ testit. Miten se on mahdollista? Ensinnäkin muutamme luokan rakennetta siten, että lisäämme toisen konstruktorin, joka mahdollistaa __Random__-olion injektoinnin luokalle.

``` java
public class ViikonLottonumerot {

    private ArrayList<Integer> viikonLottorivi;

    public ViikonLottonumerot() {
        this(new Random());
    }

    public ViikonLottonumerot(Random arpa) {
        viikonLottorivi = new ArrayList<Integer>();
        int vielaArvottavana = 7;
        while (vielaArvottavana > 0) {
            int arvottu = 1 + arpa.nextInt(39);
            if (!viikonLottorivi.contains(arvottu)) {
                viikonLottorivi.add(arvottu);
                vielaArvottavana--;
            }
        }
    }

    // ...

}
```

Säästetään myös parametriton konstruktori jotta luokkaa voi käyttää myös ilman Randomin injektointia.

Tehdään sitten Randomista *dekoroitu versio*, eli toinen luokka, joka näyttää käyttäjän kannalta täysin randomilta, mutta osaa testaamisen kannalta hyödyllisiä asioita:

``` java
public class OmaRandom extends Random {

    private ArrayList<Integer> arvotut;

    public OmaRandom() {
        arvotut = new ArrayList<Integer>();
    }

    @Override
    public int nextInt(int i) {
        int arvottu = super.nextInt(i);
        arvotut.add(arvottu);

        return arvottu;
    }

    public ArrayList<Integer> arvotut(){
        return arvotut;
    }
}
```

OmaRandom perii luokan Random, eli mikä tahansa Randomia käyttävä luokka voi käyttää OmaRandom-luokkaa. Metodi joka arpoo kokonaisluvun on ylikirjoitettu siten, että OmaRandom ottaa talteen arvotun luvun. OmaRandom-luokalle on tehty myös metodi, jolla se palauttaa arvotut luvut.

Näin voimme käyttää dekoroitua randomia testissä siten, että testi tietää mitkä lottonumerot tuli arvotuksi:

``` java
public class ViikonLottonumerotTest {

    OmaRandom random;
    ViikonLottonumerot lotto;

    @Before
    public void setUp() {
        random = new OmaRandom();
        lotto = new ViikonLottonumerot(random);
    }

    @Test
    public void testi1() {
        ArrayList<Integer> oikeat = random.arvotut();

        int[] omat = {1, 2, 3, 4, 5, 6, 7};

        assertEquals(samoja(oikeat, omat), lotto.oikeita(omat));
    }

    // ...

    private int samoja(ArrayList<Integer> oikeat, int[] omat) {
        int sama = 0;
        for (int oma : omat) {
            if (oikeat.contains(oma)) {
                sama++;
            }
        }

        return sama;
    }
}
```

## Dekoroitu pino

Olemme toteuttaneet asiakkaalle pinon:

``` java
public class Pino {

    private LinkedList<String> alkiot;

    public Pino() {
        alkiot = new LinkedList<String>();
    }

    public void push(String alkio){
        alkiot.addFirst(alkio);
    }

    public String pop(){
        return alkiot.remove();
    }

    public boolean empty(){
        return alkiot.isEmpty();
    }
}

public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        Pino pino = new Pino();

        System.out.println("pinotaan, tyhjä lopettaa:");
        while (true) {
            String pinoon = lukija.nextLine();
            if (pinoon.isEmpty()) {
                break;
            }
            pino.push(pinoon);
        }
        System.out.println("pinossa oli: ");
        while (!pino.empty()) {
            System.out.println( pino.pop() );
        }
}
```

Asiakkaamme haluaa pinosta muutaman uuden version:

* KryptattuPino jossa alkiot talletetaan pinoon kryptattuina, alkiot tulevat pinosta ulos normaalisti
* LokiPino jossa tieto pinoamisoperaatioista ja niiden parametreista ja paluuarvoista talletetaan lokiin
* PrepaidPino joka lakkaa toimimasta kun sillä on suoritettu konstruktoriparametrina määritelty määrä operaatioita

On lisäksi toteutettava kaikki mahdolliset kombinaatiot:

* KryptattuLokiPino
* LokiKryptattuPino (erona edelliseen että lokiin ei kirjata parametreja kryptattuna)
* KryptattuPrepaidPino
* KryptattuLokiPrepaidPino
* LokiPrepaidPino

Alkaa kuulostaa pahalta varsinkin kun Product Owner vihjaa, että seuraavassa sprintissä tullaan todennäköisesti vaatimaan lisää versioita pinosta, mm. ÄänimerkillinenPino, RajallisenkapasiteetinPino ja tietysti kaikki kombinaatiot tarvitaan myös...

Onneksi dekoraattori sopii tilanteeseen kuin nyrkki silmään! Luodaan pinon kolme uutta versiota dekoroituina pinoina. Tarkastellaan ensin PrepaidPinoa:

``` java
public class PrepaidPino extends Pino {

    private Pino pino;
    private int krediitteja;

    public PrepaidPino(Pino pino, int krediitteja) {
        this.pino = pino;
        this.krediitteja = krediitteja;
    }

    @Override
    public String pop() {
        if (krediitteja == 0) {
            throw new IllegalStateException("pinossa ei enää käyttöoikeutta");
        }
        krediitteja--;

        return pino.pop();
    }

    @Override
    public void push(String alkio) {
        if (krediitteja == 0) {
            throw new IllegalStateException("pinossa ei enää käyttöoikeutta");
        }
        krediitteja--;
        pino.push(alkio);
    }

    @Override
    public boolean empty() {
        if (krediitteja == 0) {
            throw new IllegalStateException("pinossa ei enää käyttöoikeutta");
        }
        krediitteja--;
        return pino.empty();
    }
}
```

PrepaidPino siis perii pinon, mutta kun tarkkaa katsotaan, niin yliluokan operaatiot ylikirjoitetaan ja yliluokkaa ei hyödynnetä millään tavalla!

PrepaidPino siis perii luokan Pino, mutta se ei käytä "perittyä" pinouttaan, vaan sensijaan PrepaidPino __sisältää__ pinon, jonka se saa konstruktoriparametrina. Tätä sisältämäänsä pinoa PrepaidPino käyttää tallettamaan kaikki alkionsa. Eli jokainen PrepaidPinon operaatio delegoi operaation sisältämälleen pinolle.

PrepaidPino luodaan seuraavalla tavalla:

``` java
Pino pino = new PrepaidPino(new Pino(), 5);
```

Eli luodaan normaali Pino ja annetaan se PrepaidPinolle konstruktoriparametrina yhdessä pinon krediittien kanssa.

Muut kaksi:

``` java
public class KryptattuPino extends Pino{
    private Pino pino;

    public KryptattuPino(Pino pino) {
        this.pino = pino;
    }

    @Override
    public String pop() {
        String alkio = pino.pop();
        return dekryptaa(alkio);
    }

    @Override
    public void push(String alkio) {
        pino.push(kryptaa(alkio));
    }

    @Override
    public boolean empty() {
        return pino.empty();
    }

    private String dekryptaa(String alkio) {
        String dekryptattu = "";
        for (int i = 0; i < alkio.length(); i++) {
            dekryptattu += (char)(alkio.charAt(i)-1);
        }

        return dekryptattu;
    }

    private String kryptaa(String alkio) {
        String kryptattu = "";
        for (int i = 0; i < alkio.length(); i++) {
            kryptattu += (char)(alkio.charAt(i)+1);
        }

        return kryptattu;
    }
}

public class LokiPino extends Pino {

    private Pino pino;
    private PrintWriter loki;

    public LokiPino(Pino pino, PrintWriter loki) {
        this.pino = pino;
        this.loki = loki;
    }

    @Override
    public String pop() {
        String popattu = pino.pop();
        loki.println("pop: "+popattu);

        return popattu;
    }

    @Override
    public void push(String alkio) {
        loki.println("push: "+alkio);

        pino.push(alkio);
    }

    @Override
    public boolean empty() {
        loki.println("empty: "+pino.empty());

        return pino.empty();
    }
}
```

Eli periaate on sama, pinodekoraattorit LokiPino ja KryptattuPino delegoivat kaikki operaationsa sisältämilleen Pino-olioille.

Koska kaikki dekoraattorit perivät luokan Pino, voidaan dekoraattorille antaa parametriksi toinen dekoraattori. Esim. KryptattuLokiPino luodaan seuraavasti:

``` java
PrintWriter loki = new PrintWriter( new File("loki.txt") );
Pino pino = new KryptattuPino( new LokiPino( new Pino(), loki ) );
```

Dekoroinnin avulla saamme siis suhteellisen vähällä ohjelmoinnilla pinolle paljon erilaisia ominaisuuskombinaatioita. Jos olisimme yrittäneet hoitaa kaiken normaalilla perinnällä, olisi luokkien määrä kasvanut eksponentiaalisesti eri ominaisuuksien määrän suhteen ja uusiokäytöstäkään ei olisi tullut mitään.

Dekorointi siis ei oleellisesti ole perintää vaan delegointia, jälleen kerran oliosuunnitteun periaate "favour composition over inheritance" on näyttänyt voimansa.

## Pintotehdas

Huomaamme, että eri ominaisuuksilla varustettujen pinojen luominen on käyttäjän kannalta hieman ikävää. Teemmekin luomista helpottamaan pinotehtaan:

``` java
public class Pinotehdas {
    public Pino prepaidPino(int krediitit){
        return new PrepaidPino(new Pino(), krediitit);
    }

    public Pino lokiPino(PrintWriter loki){
        return new LokiPino(new Pino(), loki);
    }

    public Pino kryptattuPino(){
        return new KryptattuPino(new Pino());
    }

    public Pino kryptattuPrepaidPino(int krediitit){
        return new KryptattuPino(prepaidPino(krediitit));
    }

    public Pino kryptattuLokiPino(PrintWriter loki){
        return new KryptattuPino(lokiPino(loki));
    }

    public Pino prepaidKryptattuLokiPino(int krediitit, PrintWriter loki){
        return new PrepaidPino(kryptattuLokiPino(loki), krediitit);
    }

    // monta monta muuta rakentajaa...
}
```

Factoryluokka on ikävä ja sisältää hirveän määrän metodeja. Jos pinoon lisätään vielä ominaisuuksia, tulee factory karkaamaan käsistä.

Pinon luominen on kuitenkin factoryn ansiosta helppoa:

``` java
Pinotehdas tehdas = new Pinotehdas();

Pino omapino = tehdas.kryptattuPrepaidPino(100);
```
Factoryperiaate ei kyllä ole tilanteeseen ideaali. Kokeillaan rakentaja (engl. builder) -suunnittelumallia:

## Pinorakentaja

Rakentaja-suunnittelumalli sopii tilanteeseemme erittäin hyvin. Pyrkimyksenämme on mahdollistaa pinon luominen seuraavaan tyyliin:

``` java
    Pinorakentaja rakenna = new Pinorakentaja();

    Pino pino = rakenna.prepaid(10).kryptattu().pino();
```

Rakentajan metodinimet ja rakentajan muuttujan nimi on valittu mielenkiinoisella tavalla. On pyritty mahdollisimman luonnollista kieltä muistuttavaan ilmaisuun pinon luonnissa. Kyseessä onkin oikeastaan DSL (domain specific language) pinojen luomiseen!

Luodaan ensin rakentajasta perusversio, joka soveltuu vasta normaalien pinojen luomiseen:

``` java
    Pinorakentaja rakenna = new Pinorakentaja();

    Pino pino = rakenna.pino();
```

Saamme rakentajan ensimmäisen version toimimaan seuraavasti:

``` java
public class Pinorakentaja {
    Pino pino;

    public Pinorakentaja() {
        pino = new Pino();
    }

    public Pino pino(){
        return pino;
    }
}
```

eli kun <code>Rakentaja</code>-olio luodaan, rakentajan luo pinon. Rakentajan "rakennusvaiheen alla" olevan pinon voi pyytää rakentajalta kutsumalla metodia <code>pino()</code>.

Laajennetaan nyt rakentajaa siten, että voimme luoda prepaidpinoja seuraavasti:

``` java
    Pinorakentaja rakenna = new Pinorakentaja();

    Pino pino = rakenna.prepaid(10).pino();
```

Jotta edellinen menisi kääntäjästä läpi, tulee rakentajalle lisätä metodi jonka tyyppi on <code>Pinorakentaja prepaid(int kreditit)</code>, eli jotta metodin tuloksena olevalle oliolle voitaisiin kutsua metodia <code>pino</code>, on metodin <code>prepaid</code> palautettava rakentaja. Rakentajamme runko laajenee siis seuravasti:

``` java
public class Pinorakentaja {
    Pino pino;

    public Pinorakentaja() {
        pino = new Pino();
    }

    Pinorakentaja prepaid(int kreditit) {
        // ????
    }

    public Pino pino(){
        return pino;
    }
}
```

Rakentaja siis pitää oliomuuttujassa rakentumassa olevaa pinoa. Kun kutsumme rakentajalle metodia <code>prepaid</code> ideana on, että rakentaja dekoroi rakennuksen alla olevan pinon prepaid-pinoksi. Metodi palauttaa viitteen <code>this</code> eli rakentajan itsensä. Tämä mahdollistaa sen, että metodikutsun jälkeen päästään edelleen käsiksi työn alla olevaan pinoon. Koodi siis seuraavassa:

``` java
public class Pinorakentaja {
    Pino pino;

    public Pinorakentaja() {
        pino = new Pino();
    }

    public Pino pino(){
        return pino;
    }

    Pinorakentaja prepaid(int kreditit) {
        this.pino = new PrepaidPino(pino, kreditit);
        return this;
    }
}
```

Samalla periaatteella lisätään rakentajalle metodit, joiden avulla työn alla oleva pino saadaan dekoroitua lokipinoksi tai kryptaavaksi pinoksi:

``` java
public class Pinorakentaja {
    Pino pino;

    public Pinorakentaja() {
        pino = new Pino();
    }

    public Pino pino(){
        return pino;
    }

    Pinorakentaja prepaid(int kreditit) {
        this.pino = new PrepaidPino(pino, kreditit);
        return this;
    }

    Pinorakentaja kryptattu() {
        this.pino = new KryptattuPino(pino);
        return this;
    }

    Pinorakentaja loggaava(PrintWriter loki) {
        this.pino = new LokiPino(pino, loki);
        return this;
    }
}
```

Rakentajan koodi voi vaikuttaa aluksi hieman hämmentävältä.

Rakentajaa siis käytetään seuraavasti:

``` java
Pinorakentaja rakenna = new Pinorakentaja();

Pino pino = rakenna.kryptattu().prepaid(10).pino();
```
Tässä pyydettiin rakentajalta kryptattu prepaid-pino, jossa krediittejä on 10.

Vastaavalla tavalla voidaan luoda pinoja muillakin ominaisuuksilla:

``` java
Pinorakentaja rakenna = new Pinorakentaja();

Pino pino1 = rakenna.pino();  // luo normaalin pinon
Pino pino2 = rakenna.kryptattu().loggaava(loki).prepaid.pino();  // luo sen mitä odottaa saattaa!
```

Rakentajan toteutus perustuu tekniikkaan nimeltään [method chaining](http://en.wikipedia.org/wiki/Method_chaining) eli metodien ketjutukseen. Metodit jotka ovat muuten luonteeltaan void:eja onkin laitettu palauttamaan rakentajaolio. Tämä taas mahdollistaa metodin kutsumisen toisen metodin palauttamalle rakentajalle, ja näin metodikutsuja voidaan ketjuttaa peräkkäin mielivaltainen määrä. Metodiketjutuksen motivaationa on yleensä saada olion rajapinta käytettävyydeltään mahdollisimman luonnollisen kielen kaltaiseksi DSL:ksi.

## adapteri

Äsken käsiteltyjen suunnittelmallien, dekoraattorin, komposiitin ja proxyn yhteinen puoli on, että saman ulkokuoren eli rajapinnan takana voi olla yhä monimutkaisempaa toiminnallisuutta joka on kuitenkin täysin kapseloitu käyttäjältä.

Nyt tarkastelemme tilannetta, jossa meillä on käytettävissä luokka joka oleellisesti ottaen tarjoaa haluamamme toiminnallisuuden, mutta sen rajapinta on hieman vääränlainen. Emme kuitenkaan voi muuttaa alkuperäistä luokkaa sillä muutos rikkoisi luokan muut käyttäjät.

Adapteri-suunnittelumalli sopii tälläisiin tilanteisiin [http://sourcemaking.com/design_patterns/adapter]
(http://sourcemaking.com/design_patterns/adapter)

Tehdään aiemmasta esimerkistä tutulle Pinolle adapteri HyväPino joka muuttaa metodien nimiä ja tarjoaa muutaman lisätoiminnallisuuden:

``` java
public class HyväPino {
    private Pino pino;

    public HyväPino() {
        pino = new Pino();
    }

    public boolean onTyhja(){
        return pino.empty();
    }

    public boolean eiOleTyhja(){
        return !onTyhja();
    }

    public void pinoon(String pinottava){
        pino.push(pinottava);
    }

    public void pinoon(String... pinottavat){
        for (String pinottava : pinottavat) {
            pinoon(pinottava);
        }
    }

    public String pinosta(){
        return pino.pop();
    }

    public List<String> kaikkiPinosta(){
        ArrayList<String> alkiot = new ArrayList<String>();

        while(eiOleTyhja()){
            alkiot.add(pinosta());
        }

        return alkiot;
    }
}
```

Eli adapteri __HyväPino__ kapseloi adaptoitavan Pino-olion jolle se delegoi kaikkien metodiensa toiminnallisuuden suorittamisen. Käyttäjä tuntee vaan HyväPino-luokan:


``` java
public static void main(String[] args) {
        HyväPino pino = new HyväPino();
        pino.pinoon("eka", "toka", "kolmas", "neljäs");

        System.out.println("pinossa oli: ");
        for (String alkio : pino.kaikkiPinosta()) {
            System.out.println( alkio );
        }
}
```

## MVC eli Model View Controller

Teemme erittäin yksinkertaisen MVC-periaatetta noudattavan sovelluksen.

Sovelluslogiikka seuraavassa:


``` java
public class Sovellusolio {
    private ArrayList<Integer> luvut;

    public Sovellusolio() {
        luvut = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getLuvut() {
        return luvut;
    }

    public void arvoLuku(){
        int luku = 1+new Random().nextInt(20);
        luvut.add(luku);
    }

}
```

Eli sovelluksella voi arpoa lukuja koko ajan uusia lukuja. Sovellus muistaa kaikki arpomansa luvut.

Näytössä on painike, jolla pyydetään uuden luvun arpomista sekä tekstikenttä missä arvotut luvut näytetään:


``` java
public class Naytto extends JFrame {

    private JButton nappi;
    private JTextArea teksti;

    public Naytto() {
        setLayout(new GridLayout(2, 1));
        teksti = new JTextArea(1, 80);
        teksti.setText("[]");
        nappi = new JButton("uusi");
        add(teksti);
        add(nappi);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void update(String sisalto){
        teksti.setText(sisalto);
    }

    public void asetaKontrolleri(ActionListener listener){
        nappi.addActionListener(listener);
    }
}
```

Näyttö on täysin passiivinen, se ei sisällä edes tapahtumakäsittelijää joka on MVC:n hengen mukaisesti laitettu kontrolleriin:


``` java
public class Kontrolleri implements ActionListener {
    private Naytto naytto;
    private Sovellusolio model;

    public Kontrolleri(Naytto naytto, Sovellusolio model) {
        this.naytto = naytto;
        this.model = model;
        naytto.asetaKontrolleri(this);
    }

    public void actionPerformed(ActionEvent ae) {
        model.arvoLuku();
        String dataNaytolle = model.getLuvut().toString();
        naytto.update( dataNaytolle );
    }
}
```

Kontrolleri tuntee näytön ja sovelluslogiikan eli mallin. Alussa kontrolleri asettaa itsensä tapahtumakuuntelijaksi näytössä olevalle painikkeelle.

Kun nappia painetaan, pyytää kontrolleri modelia arpomaan uuden luvun. Sen jälkeen näyttö hakee luvut modelilta ja asettaa ne tekstimuoisena näytölle käyttäen näytön update-metodia.

Itse sovellus ainoastaan luo oliot ja antaa näytön sekä modelin kontrollerille:

``` java
public class MVCSovellus {

    public void kaynnista(){
        Naytto naytto = new Naytto();
        Sovellusolio model = new Sovellusolio();
        Kontrolleri kontrolleri = new Kontrolleri(naytto, model);
    }
}
```

Model eli sovellusolio on nyt täysin tietämätön siitä kuka sitä kutsuu. Päätämme lisätä ohjelmaan useampia näyttöjä, joille kaikille tulee oma kontrolleri.


``` java
public class MVCSovellus2 {

    public void kaynnista() {
        Sovellusolio model = new Sovellusolio();
        for (int i = 0; i < 3; i++) {
            luoNaytto(model);
        }
    }

    private void luoNaytto(Sovellusolio model) {
        Naytto naytto = new Naytto();
        Kontrolleri kontrolleri = new Kontrolleri(naytto, model);
    }
}
```

Sovelluksessamme on pieni ongelma. Haluaisimme kaikkien näyttöjen olevan koko ajan ajantasalla. Nyt ainoastaan se näyttö minkä nappia painetaan päivittyy ajantasaiseksi.

## Observer

Siirrymme käyttämään luentokalvoilla selitettyä Observer-suunnittelumallia.

``` java
public interface Observer {
    void update();
}
```

Sovellusolio tuntee joukon tarkkailijoita:

``` java
public class Sovellusolio {
    private ArrayList<Integer> luvut;
    private List<Observer> tarkkailijat;

    public Sovellusolio() {
        luvut = new ArrayList<Integer>();
        tarkkailijat = new ArrayList<Observer>();
    }

    public void rekisteroiTarkkailija(Observer tarkkailija){
        tarkkailijat.add(tarkkailija);
    }

    public void ilmoitaTarkkailijoille(){
        for (Observer tarkkailija : tarkkailijat) {
            tarkkailija.update();
        }
    }

    public ArrayList<Integer> getLuvut() {
        return luvut;
    }

    public void arvoLuku(){
        int luku = 1+new Random().nextInt(20);
        luvut.add(luku);
    }

}
```

Tarkkailijat voivat rekisteröidä itsensä sovellukselle. Kun sovelluksen metodia ilmoitaTarkkailijoille kutsutaan, kutsuu sovellusolio jokaisen tarkkailijan update-metodia.

Sovellusoliolla ei siis ole konkreettista riippuvuutta mihinkään tarkkailijaan, se tuntee ne ainoastaan rajapinnan kautta.

``` java
public class Kontrolleri implements ActionListener, Observer {
    private Naytto naytto;
    private Sovellusolio model;

    public Kontrolleri(Naytto naytto, Sovellusolio model) {
        this.naytto = naytto;
        this.model = model;
        naytto.asetaKontrolleri(this);
        model.rekisteroiTarkkailija(this);
    }

    public void actionPerformed(ActionEvent ae) {
        model.arvoLuku();
        model.ilmoitaTarkkailijoille();
    }

    public void update() {
        String dataNaytolle = model.getLuvut().toString();
        naytto.update( dataNaytolle );
    }
}
```

Kontrolleri toimii tarkkailijana eli toteuttaa rajapinnan Observer. Kun nappia painetaan, eli actionPerformed-metodissa, kontrolleri pyytää modelia arpomaan uuden luvun ja samalla pyytää modelia ilmoittamaan tarkkailijoille muuttuneen arvon.

update-metodia kutsuttaessa (jota siis sovellusolio kutsuu) suorittaa kontrolleri näytön päivityksen.

Luokkaa Naytto ei tässä ratkaisussa tarvitse muuttaa.
