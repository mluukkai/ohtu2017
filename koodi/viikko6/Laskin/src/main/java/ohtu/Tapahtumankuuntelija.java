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
    private Map<JButton, Command> komennot;
    private Command previous;
 
    public Tapahtumankuuntelija(
            JButton plus, JButton miinus, 
            JButton nollaa, JButton undo, 
            JTextField tuloskentta, JTextField syotekentta) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Sum(sovellus, tuloskentta, syotekentta));
        komennot.put(miinus, new Subtract(sovellus, tuloskentta, syotekentta));
        komennot.put(nollaa, new Reset(sovellus, tuloskentta, syotekentta));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Command komento = komennot.get(ae.getSource());
        if  (komento!=null) {
            komento.execute();
            previous = komento;
        } else { // toiminto oli undo
            previous.undo();
            previous = null;
        }
        nollaa.setEnabled(sovellus.tulos()!=0);
        undo.setEnabled(previous!=null);
    }
    
    
    
    
    
    
    
    
    
    private abstract class Command{
        Sovelluslogiikka sovelluslogiikka; 
        JTextField tuloskentta;
        JTextField syotekentta;
        int prev;
        public Command(
                Sovelluslogiikka sovelluslogiikka, 
                JTextField tuloskentta, 
                JTextField syotekentta){
            this.sovelluslogiikka = sovelluslogiikka;
            this.tuloskentta = tuloskentta;
            this.syotekentta = syotekentta;                    
        }
        public void undo(){
            sovelluslogiikka.nollaa();
            sovelluslogiikka.plus(prev);
            syotekentta.setText("");
            tuloskentta.setText("" + sovelluslogiikka.tulos());
        }
        abstract public void execute();
    }
    private class Sum extends Command{
        public Sum(
                Sovelluslogiikka s, 
                JTextField tuloskentta, 
                JTextField syotekentta) {
            super(s, tuloskentta, syotekentta);
        }
        @Override
        public void execute() {
            this.sovelluslogiikka
                    .plus(Integer.parseInt(
                            this.syotekentta.getText()));
            syotekentta.setText("");
            tuloskentta.setText("" + sovelluslogiikka.tulos());
        }
    }
    private class Subtract extends Command{
        public Subtract(
                Sovelluslogiikka s, 
                JTextField tuloskentta, 
                JTextField syotekentta) {
            super(s, tuloskentta, syotekentta);
        }
        @Override
        public void execute() {
            this.sovelluslogiikka
                    .miinus(Integer.parseInt(
                            this.syotekentta.getText()));
            syotekentta.setText("");
            tuloskentta.setText("" + sovelluslogiikka.tulos());
        }
    }
    private class Reset extends Command{
        public Reset(
                Sovelluslogiikka s, 
                JTextField tuloskentta, 
                JTextField syotekentta) {
            super(s, tuloskentta, syotekentta);
            prev = sovelluslogiikka.tulos();
        }
        @Override
        public void execute() {
            this.sovelluslogiikka.nollaa();
            syotekentta.setText("");
            tuloskentta.setText("" + sovelluslogiikka.tulos());
        }
    }
}