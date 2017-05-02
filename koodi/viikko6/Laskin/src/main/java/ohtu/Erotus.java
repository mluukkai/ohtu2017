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
public class Erotus extends Komento {
    
    int temp;

    public Erotus(Sovelluslogiikka s, JTextField syote, JTextField tuloste) {
        
        super(s, syote, tuloste);
    }
    
    @Override
    public int suorita(int a) {
        
        temp = a;
        s.miinus(a);
        return s.tulos();
    }

    @Override
    public int peru() {
        
        s.plus(temp);
        return s.tulos();
    }
}
