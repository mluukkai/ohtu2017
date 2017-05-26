
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import java.util.stream.Stream;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        tarkistaKonstruktoriParametrit(kapasiteetti, kasvatuskoko);
        luvut = new int[kapasiteetti];
        for (int i = 0; i < luvut.length; i++) {
            luvut[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    private IntJoukko(int[] arvot) {
        this(KAPASITEETTI, OLETUSKASVATUS);
        luvut = arvot;
        alkioidenLkm = arvot.length;
    }

    private void tarkistaKonstruktoriParametrit(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti alle nollan");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kasvatuskoko alle nollan");
        }
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            luvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == luvut.length - 1) kasvata();
            return true;
        }
        return false;
    }

    private void kasvata() {
        int[] vanhatLuvut = Arrays.copyOf(luvut, luvut.length);
        luvut = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(vanhatLuvut, luvut);
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku != luvut[i]) continue;
            int[] tempLuvut = Arrays.copyOf(luvut, luvut.length);
            kopioiTaulukko(tempLuvut, i + 1, luvut, i, luvut.length - i - 1);
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        kopioiTaulukko(vanha, 0, uusi, 0, vanha.length);
    }

    private void kopioiTaulukko(int[] vanha, int vanhaAloitus, int[] uusi, int uusiAloitus, int pituus) {
        for (int i = 0; i < pituus; i++) {
            uusi[i + uusiAloitus] = vanha[i + vanhaAloitus];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) return "{}";
        if (alkioidenLkm == 1) return "{" + luvut[0] + "}";
        return muotoile();
    }

    private String muotoile() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += luvut[i];
            tuotos += ", ";
        }
        tuotos += luvut[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = luvut[i];
        }
        return taulu;
    }
   
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        int[] yhdiste = Stream.concat(
                Arrays.stream(a.toIntArray()).boxed(),
                Arrays.stream(b.toIntArray()).boxed()
        ).mapToInt(x -> x).distinct().toArray();
        return new IntJoukko(yhdiste);
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        int[] leikkaus = Arrays.stream(a.toIntArray()).filter(x -> b.kuuluu(x)).toArray();
        return new IntJoukko(leikkaus);
    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        int[] erotus = Arrays.stream(a.toIntArray()).filter(x -> !b.kuuluu(x)).toArray();
        return new IntJoukko(erotus);
    }
}