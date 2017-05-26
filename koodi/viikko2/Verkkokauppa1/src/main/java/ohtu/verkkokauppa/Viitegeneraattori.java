package ohtu.verkkokauppa;

public class Viitegeneraattori implements AbstraktiViitegeneraattori {

    private int seuraava;
    
    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
