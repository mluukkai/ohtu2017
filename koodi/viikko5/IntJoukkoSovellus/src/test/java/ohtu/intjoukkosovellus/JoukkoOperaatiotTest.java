
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {
    
    
    @Test
    public void testYhdiste() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(2,3,4);
        IntJoukko tulos = eka.yhdiste(toka);
        int[] vastauksenLuvut = tulos.getLukujono();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {1,2,3,4};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    } 
    
    @Test
    public void testErotus() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(2,3);
        IntJoukko tulos = eka.erotus(toka);
        int[] vastauksenLuvut = tulos.getLukujono();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {1};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    } 
    
    @Test
    public void testLeikkaus() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(2,3);
        IntJoukko tulos = eka.leikkaus(toka);
        int[] vastauksenLuvut = tulos.getLukujono();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {2};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    }     

    
    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();
        
        for (int luku : luvut) {
            joukko.lisaa(luku);
        }
        
        return joukko;
    }
}
