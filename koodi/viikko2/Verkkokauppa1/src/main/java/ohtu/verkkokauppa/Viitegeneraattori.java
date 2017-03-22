package ohtu.verkkokauppa;

public class Viitegeneraattori implements AbstraktiViitegeneraattori {

    private static Viitegeneraattori instanssi;

    private int seuraava;
    
    private Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }

    public static Viitegeneraattori getInstance() {
        if (instanssi == null) {
            instanssi = new Viitegeneraattori();
        }

        return instanssi;
    }
}
