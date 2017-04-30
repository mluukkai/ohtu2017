
package ohtu.verkkokauppa;

import java.util.ArrayList;

public class Kirjanpito {
    
    public ArrayList<String> tapahtumat = new ArrayList<String>();

    public Kirjanpito() {
        tapahtumat.ensureCapacity(100);
    }
    
    public void lisaaTapahtuma(String tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    public ArrayList<String> getTapahtumat() {
        return tapahtumat;
    }       
}
