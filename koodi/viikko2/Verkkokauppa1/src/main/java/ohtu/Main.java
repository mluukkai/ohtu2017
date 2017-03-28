package ohtu;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kirjanpito;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.TuoteVarasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;



public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
        Kauppa kauppa = ctx.getBean(Kauppa.class);
        Kirjanpito kirjanpito = ctx.getBean(Kirjanpito.class);
        TuoteVarasto varasto  = ctx.getBean(TuoteVarasto.class);
        Pankki pankki = ctx.getBean(Pankki.class);
        Viitegeneraattori viitegen = ctx.getBean(Viitegeneraattori.class);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }
    }
}
