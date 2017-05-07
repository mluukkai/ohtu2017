/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/**
 *
 * @author jjniitty
 */
public class Plussa implements Komento {

    private Sovelluslogiikka sovellus;
    private JTextField tulos;
    private JTextField syote;
    private Integer edellinen;

    public Plussa(Sovelluslogiikka sovellus, JTextField tulos, JTextField syote) {
        this.sovellus = sovellus;
        this.syote = syote;
        this.tulos = tulos;
    }

    @Override
    public void suorita() {
        edellinen = sovellus.tulos();
        Integer luku = 0;
        try {
            luku = Integer.parseInt(syote.getText());
            edellinen = luku;
        } catch (Exception e) {
        }
        sovellus.plus(luku);
        syote.setText("");
        tulos.setText(sovellus.tulos() + "");
    }

    @Override
    public void peru() {
        tulos.setText(sovellus.tulos() - edellinen + "");
        sovellus.miinus(edellinen);
    }

}
