/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import org.junit.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author jarkko
 */
public class KauppaTest {
    
    Kauppa kauppa;
    
    public KauppaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void osta(){
        kauppa = mock(Kauppa.class);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        
    }

    @Test
    public void ostaKaksiEri(){
        
    }
    
    @Test
    public void ostaKaksiSamaa(){
        
    }
    
    @Test
    public void ostaLoppunut(){
        
    }
    
    @Test
    public void aloitaAsiointiNollaaEdelliset(){
        
    }
    
    @Test
    public void pyytaaViiteNro(){
        
    }
}
