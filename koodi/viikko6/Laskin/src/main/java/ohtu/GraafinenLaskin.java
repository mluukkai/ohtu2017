package ohtu;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
 
public class GraafinenLaskin implements Runnable {  
    private JFrame frame;   
    int laskunTulos;
     
    @Override
    public void run() {
        frame = new JFrame("Laskin");
        frame.setPreferredSize(new Dimension(300, 150));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         
        luoKomponentit(frame.getContentPane());
 
        frame.pack();
        frame.setVisible(true);
    }
 
    private void luoKomponentit(Container container) {
        frame.setLayout(new GridLayout(3, 1));
        JTextField tuloskentta = new JTextField("0");
        container.add(tuloskentta);
        tuloskentta.setEnabled(false);
        JTextField syotekentta = new JTextField("");
        container.add(syotekentta);
 
        JButton plus = new JButton("+");
        JButton miinus = new JButton("-");
        JButton nollaa = new JButton("Z");
        JButton undo = new JButton("undo");
        
        Tapahtumankuuntelija kasittelija = new Tapahtumankuuntelija(plus, miinus, nollaa, undo, tuloskentta, syotekentta);
         
        plus.addActionListener(kasittelija);
        miinus.addActionListener(kasittelija);
        nollaa.addActionListener(kasittelija);
        undo.addActionListener(kasittelija);
        nollaa.setEnabled(false);
        undo.setEnabled(false);
        JPanel paneli = new JPanel(new GridLayout(1, 4));
        paneli.add(plus);
        paneli.add(miinus);
        paneli.add(nollaa);
        paneli.add(undo);
        container.add(paneli);
    }
 
}
