package ohtu;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Kauppa kauppa;

    @Test
    public void kutsutaanPankkia() {
        // luodaan kaksi mock-olioa
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);

        // injektoidaan ne kaupalle normaalien olioiden tapaan
        kauppa = new Kauppa(mockPankki, mockViite);

        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(5);
        kauppa.lisaaOstos(5);
        kauppa.maksa("1111");

        // varmistetaan pankilta että sen metodia maksa on kutsuttu
        verify(mockPankki).maksa(anyString(), anyInt(), anyInt());
        // kutsussa olevein parametrien arvoilla ei testissä ole väliä
        // kokeile muuttaa koodia siten että testi menee rikki!        
    }

    @Test
    public void kutsutaanPankkiaOikeallaTilinumerolla() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);

        kauppa = new Kauppa(mockPankki, mockViite);

        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(5);
        kauppa.lisaaOstos(5);
        kauppa.maksa("1111");

        // tällä kertaa vaaditaan että ensimmäisen parametrin arvo on oikea
        verify(mockPankki).maksa(eq("1111"), anyInt(), anyInt());
        // kokeile jälleen rikkoa koodi
    }

    @Test
    public void kutsutaanPankkiaOikeallaTilinumerollaJaSummalla() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);

        kauppa = new Kauppa(mockPankki, mockViite);

        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(5);
        kauppa.lisaaOstos(5);
        kauppa.maksa("1111");

        // nyt vaadimme myös toisen parametrin arvon olevan oikea
        verify(mockPankki).maksa(eq("1111"), eq(10), anyInt());
    }

    @Test
    public void kaytetaanMaksussaPalautettuaViiteta() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        
        // määrittelemme minkä arvon viitegeneraattori palauttaa kun sen metodia
        // seuraava() kutsutaan
        when(mockViite.seruaava()).thenReturn(55);

        kauppa = new Kauppa(mockPankki, mockViite);

        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(5);
        kauppa.lisaaOstos(5);
        kauppa.maksa("1111");

        // nyt kaksi ensimmäistä parametria saa olla arvoiltaan mitä sattuu
        // kolmannen on oltava sama mikä mock-olion määriteltiin palauttavan
        verify(mockPankki).maksa(anyString(), anyInt(), eq(55));
    }

    @Test
    public void pyydetaanUusiViiteJokaiseenMaksuun() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);

        kauppa = new Kauppa(mockPankki, mockViite);

        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(5);
        kauppa.maksa("1111");

        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kerran
        verify(mockViite, times(1)).seruaava();

        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(1);
        kauppa.maksa("1234");

        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kaksi kertaa
        verify(mockViite, times(2)).seruaava();

        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(3);
        kauppa.maksa("4444");

        // tarkistetaan että tässä vaiheessa viitegeneraattorin metodia seuraava()
        // on kutsuttu kolme kertaa        
        verify(mockViite, times(3)).seruaava();
    }

    @Test
    public void kaytetaanPerakkaistenViitekutsujenArvoja() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        // määritellään että metodi palauttaa ensimmäisellä kutsukerralla 1, toisella 2 
        // ja kolmannella 3
        when(mockViite.seruaava()).
                thenReturn(1).
                thenReturn(2).
                thenReturn(3);

        kauppa = new Kauppa(mockPankki, mockViite);

        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(5);
        kauppa.maksa("1111");

        // varmistetaan, että nyt käytössä ensimmäisenä pyydetty viite
        verify(mockPankki).maksa(anyString(), anyInt(), eq(1));
        
        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(1);
        kauppa.maksa("1222");

        // ... toisena pyydetty viite
        verify(mockPankki).maksa(anyString(), anyInt(), eq(2));   
        
        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(1);
        kauppa.maksa("4321");

        // ... ja kolmantena pyydetty viite        
        verify(mockPankki).maksa(anyString(), anyInt(), eq(3));           

    }
}
