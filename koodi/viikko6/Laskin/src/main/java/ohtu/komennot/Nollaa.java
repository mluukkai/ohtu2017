package ohtu.komennot;

import ohtu.AbstraktiKomento;
import ohtu.Sovelluslogiikka;

import javax.swing.*;

public class Nollaa extends AbstraktiKomento {

    public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        super(sovellus, tuloskentta, syotekentta);
    }

    @Override
    public void suorita() {
        sovellus.nollaa();
    }
}
