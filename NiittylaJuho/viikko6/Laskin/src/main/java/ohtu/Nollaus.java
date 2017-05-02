/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author jjniitty
 */
public class Nollaus implements Komento {

    private Sovelluslogiikka sovellus;
    private JTextField tulos;
    private JTextField syote;
    private Integer tulosEnnen;

    public Nollaus(Sovelluslogiikka sovellus, JTextField tulos, JTextField syote) {
        this.sovellus = sovellus;
        this.tulos = tulos;
        this.syote = syote;
    }

    @Override
    public void suorita() {
        tulosEnnen = sovellus.tulos();
        sovellus.nollaa();
        syote.setText("");
        tulos.setText(sovellus.tulos() + "");
    }

    @Override
    public void peru() {
        tulos.setText(tulosEnnen + "");
        sovellus.plus(tulosEnnen);
    }

}
