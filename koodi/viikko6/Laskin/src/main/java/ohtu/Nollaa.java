package ohtu;

import javax.swing.*;

/**
 * Created by migho on 2.5.2017.
 */
public class Nollaa implements Komento {

    Sovelluslogiikka sovellus;
    JTextField tuloskentta;
    JTextField syotekentta;
    int arvo = 0;

    public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        arvo = sovellus.tulos();
        sovellus.nollaa();
    }

    @Override
    public void peru() {
        sovellus.plus(arvo);
    }
}
