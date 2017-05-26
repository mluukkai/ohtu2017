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
public class Nollaa implements Komento {

    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private int edellinenTulos;

    public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
    }

    @Override
    public void suorita() {
        edellinenTulos = Integer.parseInt(tuloskentta.getText());
//        edellinenSyote = Integer.parseInt(syotekentta.getText());
        sovellus.nollaa();
        tuloskentta.setText(sovellus.tulos() + "");
    }

    @Override
    public void peru() {
        sovellus.plus(edellinenTulos);
        tuloskentta.setText(sovellus.tulos() + "");
    }

}
