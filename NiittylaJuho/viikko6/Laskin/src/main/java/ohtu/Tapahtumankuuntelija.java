package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Tapahtumankuuntelija implements ActionListener {

    private JButton nollaa;
    private JButton undo;
    private Sovelluslogiikka sovellus;
    private Map<JButton, Komento> komennot;
    private Komento edellinen;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();

        komennot.put(plus, new Plussa(sovellus, tuloskentta, syotekentta));
        komennot.put(miinus, new Miinus(sovellus, tuloskentta, syotekentta));
        komennot.put(nollaa, new Nollaus(sovellus, tuloskentta, syotekentta));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        Komento komento = komennot.get(ae.getSource());
        if (komento != null) {
            komento.suorita();
            edellinen = komento;
            System.out.println(sovellus.tulos());
        } else {
            edellinen.peru();
            edellinen = null;
        }

        nollaa.setEnabled(sovellus.tulos() != 0);
        undo.setEnabled(edellinen != null);
    }

}
