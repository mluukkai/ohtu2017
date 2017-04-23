package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkiot;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        luoTaulukko(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        luoTaulukko(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        luoTaulukko(kapasiteetti, kasvatuskoko);

    }

    private void luoTaulukko(int kapasiteetti, int kasvatuskoko) {
        validoiKapasiteetti(kapasiteetti);
        validoiKasvatuskoko(kasvatuskoko);
        alkiot = new int[kapasiteetti];
        for (int i = 0; i < alkiot.length; i++) {
            alkiot[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public void validoiKapasiteetti(int kapasiteetti) {
        if (kapasiteetti <= 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei saa olla nolla tai negatiivinen");
        }
    }

    public void validoiKasvatuskoko(int kasvatuskoko) {
        if (kasvatuskoko <= 0) {
            throw new IndexOutOfBoundsException("kasvatuskoko ei saa olla nolla tai negatiivinen");
        }
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            lisaaTyhjaanTaulukkoon(luku);
            return true;
        }
        if (!kuuluu(luku)) {
            lisaaTaulukkoonKuulumatonLuku(luku);
            return true;
        }
        return false;
    }

    public void lisaaTyhjaanTaulukkoon(int luku) {
        alkiot[0] = luku;
        alkioidenLkm++;
    }

    public void lisaaTaulukkoonKuulumatonLuku(int luku) {
        alkiot[alkioidenLkm] = luku;
        alkioidenLkm++;
        kasvataTaulukkoaJosTaynna();
    }

    public void kasvataTaulukkoaJosTaynna() {
        if (alkioidenLkm % alkiot.length == 0) {
            int[] apuTaulukko = new int[alkiot.length];
            apuTaulukko = alkiot;
            alkiot = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(apuTaulukko, alkiot);
        }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkiot[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = etsiLuvunSijainti(luku);
        if (kohta != -1) {
            muutaLukuaSijainnissa(luku, kohta);
            return true;
        }
        return false;
    }

    public void muutaLukuaSijainnissa(int luku, int sijainti) {
        for (int j = sijainti; j < alkioidenLkm - 1; j++) {
            int apu = alkiot[j];
            alkiot[j] = alkiot[j + 1];
            alkiot[j + 1] = apu;
        }
        alkioidenLkm--;
    }

    public int etsiLuvunSijainti(int luku) {
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkiot[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
            }
        }
        return kohta;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int koko() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + alkiot[0] + "}";
        } else {
            return toStringMonta();
        }
    }

    public String toStringMonta() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += alkiot[i];
            tuotos += ", ";
        }
        tuotos += alkiot[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = alkiot[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < bTaulu.length; i++) {
            a.lisaa(bTaulu[i]);
        }
        return a;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < bTaulu.length; i++) {
            if (a.kuuluu(bTaulu[i])) {
                y.lisaa(bTaulu[i]);
            }
        }
        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < bTaulu.length; i++) {
            if (a.kuuluu(bTaulu[i])) {
                a.poista(bTaulu[i]);
            }
        }
        return a;
    }
}
