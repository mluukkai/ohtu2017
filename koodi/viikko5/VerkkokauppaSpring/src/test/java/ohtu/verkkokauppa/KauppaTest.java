package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42, 43);

        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 3));

        // sitten testattava kauppa
        k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        k.tilimaksu("pekka", "12345");
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void tilisiirtoaKutsutaanOikeillaTiedoilla() {
        k.tilimaksu("haloo", "715517");
        verify(pankki).tilisiirto(eq("haloo"), anyInt(), eq("715517"), anyString(), eq(5));
    }

    @Test
    public void tilisiirtoaKutsutaanOikeillaTiedoillaKaksiTuotetta() {
        k.lisaaKoriin(2);
        k.tilimaksu("haloo", "715517");
        verify(pankki).tilisiirto(eq("haloo"), anyInt(), eq("715517"), anyString(), eq(8));
    }

    @Test
    public void tilisiirtoaKutsutaanOikeillaTiedoillaKaksiSamaatuotetta() {
        k.lisaaKoriin(1);
        k.tilimaksu("haloo", "715517");
        verify(pankki).tilisiirto(eq("haloo"), anyInt(), eq("715517"), anyString(), eq(10));
    }

    @Test
    public void tilisiirtoaKutsutaanOikeillaTiedoillaTuoteLoppuu() {
        when(varasto.saldo(2)).thenReturn(0);
        k.lisaaKoriin(2);
        k.tilimaksu("haloo", "715517");
        verify(pankki).tilisiirto(eq("haloo"), anyInt(), eq("715517"), anyString(), eq(5));
    }

    @Test
    public void asioinninAloittaminenNollaaTiedot() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("haloo", "715517");
        verify(pankki).tilisiirto(eq("haloo"), anyInt(), eq("715517"), anyString(), eq(3));
    }

    @Test
    public void kauppaPyytääViitenumeronJokaiselleMaksutapahtumalle() {
        k.tilimaksu("haloo", "715517");
        k.tilimaksu("haloo", "715517");
        verify(viite, times(2)).uusi();
    }

}
