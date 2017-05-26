
package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface Ikp {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}
