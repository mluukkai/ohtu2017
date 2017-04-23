package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha

    private int kasvatusKoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukonLuvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int taulukonKoko) {
        this(taulukonKoko, OLETUSKASVATUS);
    }

    public IntJoukko(int taulukonKoko, int koonLisays) {
        if (taulukonKoko < 0 || koonLisays < 0) {
            throw new IllegalArgumentException("Taulukon koko ja koon lisäys eivät voi olla negatiivisia");
        }
        this.joukonLuvut = new int[taulukonKoko];
        this.alkioidenLkm = 0;
        this.kasvatusKoko = koonLisays;
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0 || !kuuluu(luku)) {
            kasvataJosTarvetta();
            joukonLuvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonLuvut[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int luvunPaikka = etsi(luku);
        if(luvunPaikka == alkioidenLkm){
            return false;
        }
        siirraLuvutVasemmalle(luvunPaikka);
        alkioidenLkm--;
        return true;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } 
        String mjono = "{" + joukonLuvut[0];
        for(int i = 1; i < alkioidenLkm; i++){
            mjono += ", " + joukonLuvut[i];
        }
        return mjono + "}";
    }

    public int[] toIntArray() {
        return Arrays.copyOf(joukonLuvut, alkioidenLkm);
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        IntJoukko uusiJoukko = new IntJoukko(aTaulu.length + bTaulu.length);
        lisaaKaikki(uusiJoukko, aTaulu);
        lisaaKaikki(uusiJoukko, bTaulu);
        return uusiJoukko;
    }

    
    public static IntJoukko leikkaus(IntJoukko aJoukko, IntJoukko bJoukko) {
        int[] aTaulu = aJoukko.toIntArray();
        IntJoukko uusiJoukko = new IntJoukko(aTaulu.length);
        for (int i = 0; i < aTaulu.length; i++) {
            if(bJoukko.kuuluu(aTaulu[i])){
                uusiJoukko.lisaa(aTaulu[i]);
            }
        }
        return uusiJoukko;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        IntJoukko uusiJoukko = new IntJoukko(aTaulu.length);
        lisaaKaikki(uusiJoukko, aTaulu);
        for (int i = 0; i < bTaulu.length; i++) {
            uusiJoukko.poista(bTaulu[i]);
        }
        return uusiJoukko;
    }

    private void kasvataJosTarvetta() {
        if (alkioidenLkm == joukonLuvut.length) {
            joukonLuvut = Arrays.copyOf(joukonLuvut, joukonLuvut.length + kasvatusKoko);
        }
    }

    private int etsi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (joukonLuvut[i] == luku) {
                return i;
            }
        }
        return alkioidenLkm;
    }
    
    private void siirraLuvutVasemmalle(int aloitusIndeksi){
        for (int i = aloitusIndeksi; i < alkioidenLkm - 1; i++) {
            joukonLuvut[i] = joukonLuvut[i + 1];
        }
    }
    
    private static void lisaaKaikki(IntJoukko joukko, int[] luvut){
        for(int i = 0; i < luvut.length; i++){
            joukko.lisaa(luvut[i]);
        }
    }
    
}
