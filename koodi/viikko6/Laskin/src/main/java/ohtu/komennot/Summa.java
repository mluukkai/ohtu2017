/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.komennot;

import javax.swing.JTextField;
import ohtu.Sovelluslogiikka;

/**
 *
 * @author ekalaja
 */
public class Summa implements Komento {

    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private JTextField edellinenSyote;
    private int edellinen;

    public Summa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
    }

    @Override
    public void suorita() {
        edellinenSyote = syotekentta;
        sovellus.plus(Integer.parseInt(syotekentta.getText()));
        tuloskentta.setText(sovellus.tulos() + "");
    }

    @Override
    public void peru() {
        new Erotus(sovellus, tuloskentta, edellinenSyote).suorita();
    }
}
