package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

@Component
public class Viitegeneraattori implements ViitegeneraattoriInterface {

//    private static Viitegeneraattori instanssi;
//
//    public static Viitegeneraattori getInstance() {
//        if (instanssi == null) {
//            instanssi = new Viitegeneraattori();
//        }
//
//        return instanssi;
//    }
    
    private int seuraava;
    
    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
