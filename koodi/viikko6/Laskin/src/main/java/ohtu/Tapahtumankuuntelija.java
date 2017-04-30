package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;
 
public class Tapahtumankuuntelija implements ActionListener {
    
    private JButton undo;
    private JButton nollaa;
    private JTextField tulos;
    private Komento edellinen;
    
    private HashMap<JButton,Komento> komennot;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        
        this.tulos = tuloskentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Plus(syotekentta,tuloskentta));
        this.komennot.put(miinus, new Miinus(syotekentta,tuloskentta));
        this.komennot.put(nollaa, new Nollaa(syotekentta,tuloskentta));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        Komento komento = this.komennot.get(ae.getSource());
        if(komento != null){
            komento.suorita();
            edellinen = komento;
        }else{
            edellinen.peru();
            edellinen = null;
        }
        
        
        nollaa.setEnabled(Integer.parseInt(this.tulos.getText()) > 0);
        undo.setEnabled(edellinen != null);
    }
 
}