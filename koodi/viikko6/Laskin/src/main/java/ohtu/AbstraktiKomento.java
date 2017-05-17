package ohtu;

import javax.swing.*;
import java.util.Optional;

public abstract class AbstraktiKomento implements Komento {
    protected final Sovelluslogiikka sovellus;
    protected final JTextField tuloskentta;
    private final JTextField syotekentta;

    private Integer edellinenTulos;

    public AbstraktiKomento(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        edellinenTulos = null;
    }

    public void suoritaJaPaivita() {
        edellinenTulos = sovellus.tulos();
        suorita();
        paivitaTulos();
        tyhjennaSyote();
    }

    protected Optional<Integer> lueArvo() {
        try {
            Integer arvo = Integer.parseInt(syotekentta.getText());
            return Optional.of(arvo);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    protected void paivitaTulos() {
        tuloskentta.setText("" + sovellus.tulos());
    }

    protected void tyhjennaSyote() {
        syotekentta.setText("");
    }

    public void peru() {
        if (edellinenTulos == null) return;
        sovellus.setTulos(edellinenTulos);
        paivitaTulos();
    }
}
