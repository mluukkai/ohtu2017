
package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface KirjanpitoInterface {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}
