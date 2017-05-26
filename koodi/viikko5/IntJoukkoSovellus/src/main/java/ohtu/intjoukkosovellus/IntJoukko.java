
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) 
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");
        if (kasvatuskoko < 0) 
            throw new IndexOutOfBoundsException("kapasiteetti2");
        ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    private void expand(){
        int[] replacement = new int[alkioidenLkm + kasvatuskoko];
        System.arraycopy(ljono, 0, replacement, 0, alkioidenLkm);
        ljono = replacement;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % ljono.length == 0)
                expand();
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        return find(luku) != -1;
    }
    
    public int find(int num){
        for(int i = 0; i < alkioidenLkm; i++)
            if(ljono[i] == num)
                return i;
        return -1;
    }

    public boolean poista(int luku) {
        int kohta = find(luku);
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) 
                ljono[j] = ljono[j+1];
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String str = "{";
        int i = 0;
        while(i < alkioidenLkm){
            str += ljono[i++];
            if(i < alkioidenLkm)
                str += ", ";
        }
        return str + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(ljono, 0, taulu, 0, taulu.length);
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) 
            x.lisaa(aTaulu[i]);
        for (int i = 0; i < bTaulu.length; i++) 
            x.lisaa(bTaulu[i]);
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) 
            for (int j = 0; j < bTaulu.length; j++) 
                if (aTaulu[i] == bTaulu[j]) 
                    y.lisaa(bTaulu[j]);
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) 
            z.lisaa(aTaulu[i]);
        for (int i = 0; i < bTaulu.length; i++) 
            z.poista(i);
        return z;
    }
        
}