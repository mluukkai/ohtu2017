
package ohtu.intjoukkosovellus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoKaksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    @Before
    public void setUp() {
        joukko = new IntJoukko(4, 2);
        joukko.lisaa(10);
        joukko.lisaa(3);
    }
    
    @Test
    public void konstruktoriEiToimiVäärilläParametreillä(){
        IntJoukko intJoukko = new IntJoukko(0,0);
        assertNull(intJoukko.getLukujono());
    }
    
    @Test
    public void konstruktoriEiToimiVäärälläKasvatuskoolla(){
        IntJoukko intJoukko = new IntJoukko(1,0);
        assertNull(intJoukko.getLukujono());
    }
    
    @Test
    public void konstruktoriEiToimiVäärälläKapasiteetilla(){
        IntJoukko intJoukko = new IntJoukko(0,1);
        assertNull(intJoukko.getLukujono());
    }
    
    
    @Test
    public void konstruktoriToimiOikeillarametreillä(){
        IntJoukko intJoukko = new IntJoukko(3,3);
        assertNotNull(intJoukko.getLukujono());
    }
}
