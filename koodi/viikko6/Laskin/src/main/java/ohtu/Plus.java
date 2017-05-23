
package ohtu;

import javax.swing.JTextField;


public class Plus implements Komento {
    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private int edellinenLuku;

    public Plus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    public void suorita() {
        edellinenLuku = sovellus.tulos();
        int luku = Integer.parseInt(syotekentta.getText());
        sovellus.plus(luku);
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }

    public void peru() {
        sovellus.nollaa();
        sovellus.plus(edellinenLuku);
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }
    
    
}
