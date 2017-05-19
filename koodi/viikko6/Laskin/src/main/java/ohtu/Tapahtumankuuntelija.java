package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JTextField;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;
    private Map<JButton, Komento> komennot;
    private Komento edellinen;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = new Sovelluslogiikka();
        
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Plus(sovellus, tuloskentta, syotekentta));
        this.komennot.put(miinus, new Miinus(sovellus, tuloskentta, syotekentta));
        this.komennot.put(nollaa, new Nollaa(sovellus, tuloskentta, syotekentta));
        this.edellinen = null;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(komennot.containsKey(ae.getSource())){
            Komento komento = komennot.get(ae.getSource());
            komento.suorita();
            this.edellinen = komento;
        } else {
            this.edellinen.peru();
            this.edellinen = null;
        }
        
        nollaa.setEnabled(sovellus.tulos() != 0);
        undo.setEnabled(edellinen != null);
    }
 
}