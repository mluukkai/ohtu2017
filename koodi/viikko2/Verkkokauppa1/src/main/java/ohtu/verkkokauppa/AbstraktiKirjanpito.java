package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface AbstraktiKirjanpito {
    void lisaaTapahtuma(String tapahtuma);

    ArrayList<String> getTapahtumat();
}
