
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
    
    public IntJoukko() {
        lukujono = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti > 0) {
            lukujono = new int[kapasiteetti];
            alkioidenLkm = 0;
            this.kasvatuskoko = OLETUSKASVATUS;
        }
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti > 0 && kasvatuskoko > 0) {
            lukujono = new int[kapasiteetti];
            alkioidenLkm = 0;
            this.kasvatuskoko = kasvatuskoko;
        }
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
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return fixLukujono(i);
            }
        }
        return false;    
    }
    
    
    public int mahtavuus() {
        return alkioidenLkm;
    }
        

    // Joukko-operaatiot
    
    public IntJoukko yhdiste(IntJoukko b) {
        IntJoukko tama = new IntJoukko();
        tama.setLukujono(lukujono);
        tama.setAlkioidenLkm(alkioidenLkm);
        return yhdista(tama,b.getLukujono());
    }
    
    
    public IntJoukko leikkaus(IntJoukko b) {
        IntJoukko tama = new IntJoukko();
        tama.setLukujono(lukujono);
        tama.setAlkioidenLkm(alkioidenLkm);
        return leikkaa(tama,b);
    }
    
       
    public IntJoukko erotus (IntJoukko b) {
        IntJoukko tama = new IntJoukko();
        tama.setLukujono(lukujono);
        tama.setAlkioidenLkm(alkioidenLkm);
        return erota(tama,b.getLukujono());
    }
    
    // Joukko-operaatioden apumetodeja 
    
     private IntJoukko leikkaa(IntJoukko joukko, IntJoukko toinenJoukko){
        for (int i = 0; i < lukujono.length; i++) {
            if(!toinenJoukko.isInArray(lukujono[i])){
                joukko.poista(lukujono[i]);
            }
        }
        return joukko;
    }
    
         
    private IntJoukko yhdista(IntJoukko joukko, int [] toinenLukujono){
        for (int i = 0; i < toinenLukujono.length; i++) {
            if(!isInArray(toinenLukujono[i])){
                joukko.lisaa(toinenLukujono[i]);
            }
        }
        return joukko;
    } 
    
    private IntJoukko erota(IntJoukko joukko, int [] toinenLukujono){
       for (int i = 0; i < toinenLukujono.length; i++) {
            if(isInArray(toinenLukujono[i])){
                joukko.poista(toinenLukujono[i]);
            }
        }
        return joukko;
    }
    
    
    // muita apumetodeja
    
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
        for (int i = 0; i < old.length; i++) {
            luvut[i] = old[i];
        }
    }
    
    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        }else{
            String tuotos = createTuotos();
            return tuotos;
        }    
    }
    
    private String createTuotos(){
        if (alkioidenLkm == 1) {
            return "{" + lukujono[0] + "}";
        }else{
            return tulostaLista();
        }
    }
    
    private String tulostaLista(){
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += lukujono[i] + ", ";
        }
        tuotos += lukujono[alkioidenLkm - 1] +  "}";
        return tuotos;
    }
        
}