/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author Leevi
 */
public abstract class Komento {
    
    protected Sovelluslogiikka s;
    protected JTextField syote;
    protected JTextField tuloste;

    public Komento(Sovelluslogiikka s, JTextField syote, JTextField tuloste) {
        this.s = s;
        this.syote = syote;
        this.tuloste = tuloste;
    }

    public int suorita(int a) {
        
        return -1;
    }

    public int peru() {
        
        return -1;
    }
}
