/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author villepaa
 */
public class Plus implements Komento{

    private JTextField tulos;
    private JTextField syotekentta;
    private int syote;
    
    
    public Plus(JTextField syotekentta, JTextField tuloskentta){
        tulos= tuloskentta;
        this.syotekentta= syotekentta;
        this.syote = 0;
    }
    
    @Override
    public void suorita() {
        syote = Integer.parseInt(this.syotekentta.getText());
        int tulostus = Integer.parseInt(this.tulos.getText()) + syote;
        this.tulos.setText(Integer.toString(tulostus));
        this.syotekentta.setText("");
    }

    @Override
    public void peru() {
        int tulostus = Integer.parseInt(this.tulos.getText()) - syote;
        this.tulos.setText(Integer.toString(tulostus));
        this.syotekentta.setText(Integer.toString(syote));
    }
    
}
