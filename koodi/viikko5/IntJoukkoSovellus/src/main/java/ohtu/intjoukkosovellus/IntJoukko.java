
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 
    
    
    // konstruktorit
    
     public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti > 0 && kasvatuskoko > 0) {
            lukujono = new int[kapasiteetti];
            alkioidenLkm = 0;
            this.kasvatuskoko = kasvatuskoko;
        }
    }
    
    public IntJoukko() {
        this(KAPASITEETTI,OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti,OLETUSKASVATUS);
    }
      

    
    // Julkiset metodit
    
    public boolean lisaa(int luku) {
        if (!isInArray(luku)) {
            hoidaLisaaminen(luku);
        }
        return false;
    }
       
    
    public int[] getLukujono() {
        if(lukujono == null){
            return null;
        }
        return Arrays.copyOf(lukujono,alkioidenLkm);
    }

    public void setLukujono(int[] lukujono) {
        this.lukujono = lukujono;
    }
        
    
    public boolean isInArray(int luku) {
        return luvunIndeksi(luku) != -1;
    }

    public boolean poista(int luku) {
        int indeksi = luvunIndeksi(luku);
        if(indeksi != -1){
            return fixLukujono(indeksi);
            
        }
        return false;    
    }
    
    
    public int mahtavuus() {
        return alkioidenLkm;
    }
        

    // Joukko-operaatiot
    
    public IntJoukko yhdiste(IntJoukko b) {
        IntJoukko joukko = this;
         for (int i = 0; i < b.getLukujono().length; i++) {
            if(!isInArray(b.getLukujono()[i])){
                joukko.lisaa(b.getLukujono()[i]);
            }
        }
        return joukko;
    }
    
    
    public IntJoukko leikkaus(IntJoukko b) {
        IntJoukko joukko = this;
        for (int i = 0; i < lukujono.length; i++) {
            if(!b.isInArray(lukujono[i])){
                joukko.poista(lukujono[i]);
            }
        }
        return joukko;
    }
    
       
    public IntJoukko erotus (IntJoukko b) {
        IntJoukko joukko = this;
        for (int i = 0; i < b.getLukujono().length; i++) {
            if(isInArray(b.getLukujono()[i])){
                joukko.poista(b.getLukujono()[i]);
            }
        }
        return joukko;
    }   
    
        
    // muita apumetodeja
    
    private int luvunIndeksi(int luku){
         for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return i;
            }
         }   
         return -1;   
    }
    
    
    private boolean fixLukujono(int index){
        lukujono[index] = 0;
        for (int i = index+1; i < alkioidenLkm; i++) {
            lukujono[i-1] = lukujono[i];
        }
        alkioidenLkm--;
        return true;
    }

    private boolean expandArray(){
        int[] taulukkoOld = Arrays.copyOf(lukujono,lukujono.length);
        lukujono = new int[alkioidenLkm + kasvatuskoko];
        copyExcistingInts(taulukkoOld, lukujono);
        return true;
    }
    
    private boolean hoidaLisaaminen(int luku){
        if(alkioidenLkm == lukujono.length){
            expandArray();
        }
        lukujono[alkioidenLkm] = luku;
        alkioidenLkm++;
        return true;
    }
    
    private void setAlkioidenLkm(int lkm){
        alkioidenLkm = lkm;
    }

    private void copyExcistingInts(int[] old, int[]luvut) {
        System.arraycopy(old, 0, luvut, 0, old.length);
    }
    
    // toString()-metodin luominen
    
    @Override
    public String toString() {
        String tuotos = createTuotos();
        return "{" + tuotos + "}";
        
    }
    
    private String createTuotos(){
        String tuotos = "";
        if (alkioidenLkm > 0) {
            for (int i = 0; i < alkioidenLkm-1; i++) {
                tuotos += lukujono[i] + ", ";
            }
            tuotos += lukujono[alkioidenLkm-1];
        }    
        return tuotos;
    }
    
    
        
}