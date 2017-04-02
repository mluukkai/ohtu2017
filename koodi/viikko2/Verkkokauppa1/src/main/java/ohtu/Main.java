package ohtu;

import ohtu.verkkokauppa.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

public class Main {

    public static final int CASE_OF_BEER = 24;

    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

        Kauppa kauppa = ctx.getBean(Kauppa.class);
        addTestData(kauppa);

        AbstraktiKirjanpito kirjanpito = ctx.getBean(AbstraktiKirjanpito.class);

        // kirjanpito
        for (String tapahtuma : kirjanpito.getTapahtumat()) {
            System.out.println(tapahtuma);
        }
    }

    private static void addTestData(Kauppa kauppa) {
        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");
        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < CASE_OF_BEER; i++) {
            kauppa.lisaaKoriin(5);
        }
        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");
    }
}
