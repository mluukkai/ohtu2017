package ohtu.komennot;

import ohtu.AbstraktiKomento;
import ohtu.Sovelluslogiikka;

import javax.swing.*;

public class Erotus extends AbstraktiKomento {

    public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        super(sovellus, tuloskentta, syotekentta);
    }

    @Override
    public void suorita() {
        lueArvo().ifPresent(sovellus::miinus);
    }
}
