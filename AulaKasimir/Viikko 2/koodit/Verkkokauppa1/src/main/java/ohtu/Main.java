package ohtu;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kirjanpito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
//        Kauppa kauppa = new Kauppa();
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

//        Kirjanpito k = ctx.getBean(Kirjanpito.class);
        Kauppa kauppa = ctx.getBean(Kauppa.class);

//        Varasto varasto = new Varasto(k);
//        Pankki pankki = new Pankki(k);
//        Viitegeneraattori viitegen = new Viitegeneraattori();
//        Kauppa kauppa = new Kauppa(varasto, pankki, viitegen);
//        Kauppa kauppa = ctx.getBean(Kauppa.class);
        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");

        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");

        Kirjanpito kp = ctx.getBean(Kirjanpito.class);

        // kirjanpito
        for (String tapahtuma : kp.getTapahtumat()) {
            System.out.println(tapahtuma);
        }
    }
}
