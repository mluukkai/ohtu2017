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
public class Miinus implements Komento {

    private Sovelluslogiikka sovellus;
    private JTextField syote;
    private JTextField tulos;
    private Integer edellinen;

    public Miinus(Sovelluslogiikka sovellus, JTextField tulos, JTextField syote) {
        this.sovellus = sovellus;
        this.syote = syote;
        this.tulos = tulos;
    }

    @Override
    public void suorita() {
        Integer luku = 0;
        try {
            luku = Integer.parseInt(syote.getText());
            edellinen = luku;
        } catch (Exception e) {
        }
        sovellus.miinus(luku);
        syote.setText("");
        tulos.setText(sovellus.tulos() + "");
    }

    @Override
    public void peru() {
        tulos.setText(sovellus.tulos() + edellinen + "");
        sovellus.plus(edellinen);
    }

}
