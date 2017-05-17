package ohtu;

import javax.swing.*;

/**
 * Created by migho on 2.5.2017.
 */
public class Erotus implements Komento {

    Sovelluslogiikka sovellus;
    JTextField tuloskentta;
    JTextField syotekentta;
    int arvo = 0;

    public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;

    }

    @Override
    public void suorita() {
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        sovellus.miinus(arvo);
    }

    @Override
    public void peru() {
        sovellus.plus(arvo);
    }
}
