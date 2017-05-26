package ohtu.komennot;

import ohtu.AbstraktiKomento;
import ohtu.Sovelluslogiikka;

import javax.swing.*;

public class Summa extends AbstraktiKomento {

    public Summa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        super(sovellus, tuloskentta, syotekentta);
    }

    @Override
    public void suorita() {
        lueArvo().ifPresent(sovellus::plus);
    }
}

