package ohtu.verkkokauppa;


import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;




public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa kauppa; 
    
@Before
    public void setUp() {
        pankki = mock(Pankki.class);
    
        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi())
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3);
                
        varasto = mock(Varasto.class);
        
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(2); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "voi", 3));
        when(varasto.saldo(3)).thenReturn(0); 
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "lehmä", 30));
        
        
        // sitten testattava kauppa 
        kauppa = new Kauppa(varasto, pankki, viite);           
    }    


@Test
public void yhdenTuotteenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
   
    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    kauppa.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
    
}

@Test
public void kahdenEriTuotteenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
   
    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    kauppa.lisaaKoriin(2); 
    kauppa.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(8));   

}

@Test
public void kahdenSamanTuotteenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
   
    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(1);
    kauppa.lisaaKoriin(1);// ostetaan tuotetta numero 1 eli maitoa
    kauppa.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10));   
    
}

@Test
public void tuotteenJaLoppuneenTuotteenOstoksenJalkeenPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
   
    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(1);
    kauppa.lisaaKoriin(3);// ostetaan tuotetta numero 1 eli maitoa
    kauppa.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
    
}

@Test
public void aloitaAsiointiTyhjentaaEdellisenOstoksenTiedot() {
   
    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(1);
    kauppa.tilimaksu("pekka", "12345");
    
    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(2);
    kauppa.tilimaksu("pekka", "12345");
    
    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(3));   
    
}

@Test
public void jokaiselleTapahtumalleUusiViiteNumero() {
   
    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(1);
    kauppa.tilimaksu("pekka", "12345");
    
    verify(pankki).tilisiirto(anyString(),eq(1) , anyString(), anyString(), anyInt());   
    
    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(2);
    kauppa.tilimaksu("pekka", "12345");
    
    verify(pankki).tilisiirto(anyString(),eq(2) , anyString(), anyString(), anyInt());
    
    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(1);
    kauppa.tilimaksu("pekka", "12345");
    
    verify(pankki).tilisiirto(anyString(),eq(3) , anyString(), anyString(), anyInt());
    
    
}

@Test
public void koristaPoistaminenPoistaaKorista() {
   
    kauppa.aloitaAsiointi();
    kauppa.lisaaKoriin(1);
    kauppa.lisaaKoriin(1);
    kauppa.poistaKorista(1);
    kauppa.tilimaksu("pekka", "12345");
    verify(pankki).tilisiirto(anyString(),anyInt() , anyString(), anyString(), eq(5));   
    
    
    
    
}

    
}
