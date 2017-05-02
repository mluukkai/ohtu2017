package ohtu.verkkokauppa;

public class Viitegeneraattori implements ViitegeneraattoriInt {

    private int seuraava;
    
    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
