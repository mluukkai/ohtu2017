package ohtu;

public class Sovelluslogiikka {
 
    private int tulos;
 
    public void plus(int syote) {
        tulos += syote;
    }
     
    public void miinus(int syote) {
        tulos -= syote;
    }
 
    public void nollaa() {
        tulos = 0;
    }
 
    public int tulos() {
        return tulos;
    }
}