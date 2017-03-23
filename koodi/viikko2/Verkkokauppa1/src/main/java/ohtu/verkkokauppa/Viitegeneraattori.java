package ohtu.verkkokauppa;

public class Viitegeneraattori implements ViitegeneraattoriIO {

    private static ViitegeneraattoriIO instanssi;

    public static ViitegeneraattoriIO getInstance() {
        if (instanssi == null) {
            instanssi = new Viitegeneraattori();
        }

        return instanssi;
    }
    
    private int seuraava;
    
    private Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
