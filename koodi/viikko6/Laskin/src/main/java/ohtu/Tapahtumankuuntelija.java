package ohtu;

import ohtu.komennot.Erotus;
import ohtu.komennot.Nollaa;
import ohtu.komennot.Summa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton nollaa;
    private JButton undo;
    private Sovelluslogiikka sovellus;
    private final HashMap<JButton, AbstraktiKomento> komennot;

    private AbstraktiKomento edellinenKomento;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();

        komennot = new HashMap<>();
        komennot.put(plus, new Summa(sovellus, tuloskentta, syotekentta));
        komennot.put(miinus, new Erotus(sovellus, tuloskentta, syotekentta));
        komennot.put(nollaa, new Nollaa(sovellus, tuloskentta, syotekentta));

        edellinenKomento = null;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == undo && edellinenKomento != null) {
            edellinenKomento.peru();
            return;
        }

        AbstraktiKomento komento = komennot.get(ae.getSource());
        if (komento == null) return;

        edellinenKomento = komento;
        komento.suoritaJaPaivita();

        nollaa.setEnabled(sovellus.tulos() != 0);
        undo.setEnabled(true);
    }
}