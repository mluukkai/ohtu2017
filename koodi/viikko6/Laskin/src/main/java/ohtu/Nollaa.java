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
public class Nollaa implements Komento{
    
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private int tulos;
    private int syote;
   
    public Nollaa(JTextField syotekentta, JTextField tuloskentta){
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.tulos = 0;
    }
    
     @Override
    public void suorita() {
        tulos = Integer.parseInt(this.tuloskentta.getText());
        this.tuloskentta.setText(Integer.toString(0));
    }

    @Override
    public void peru() {
        this.tuloskentta.setText(Integer.toString(tulos));
        
    }
    
    
}
