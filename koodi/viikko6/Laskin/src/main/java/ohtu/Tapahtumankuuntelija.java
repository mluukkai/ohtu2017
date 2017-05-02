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
    private JTextField tuloskentta;
    private JTextField syotekentta;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {

        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();

        komennot = new HashMap<>();
        komennot.put(plus, new Summa(sovellus, tuloskentta, syotekentta));
        komennot.put(miinus, new Erotus(sovellus, tuloskentta, syotekentta));
        komennot.put(nollaa, new Nollaa(sovellus, tuloskentta, syotekentta));

        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        Komento komento = komennot.get(ae.getSource());
        int laskunTulos = 0;
        if (komento != null) {
            komento.syote = this.syotekentta;
            int aa;
            if (komento.getClass() == Nollaa.class) {
                // nollataan : )
            } else {
                aa = Integer.parseInt(this.syotekentta.getText());
                laskunTulos = komento.suorita(aa);
                edellinen = komento;
            }
        } else {
            // toiminto oli undo
            laskunTulos = edellinen.peru();
            edellinen = null;
        }

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);

        nollaa.setEnabled(sovellus.tulos() != 0);
        undo.setEnabled(edellinen != null);
    }
}
