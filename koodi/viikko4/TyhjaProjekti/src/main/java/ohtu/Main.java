package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "123456789";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/" + studentNr + "/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();

        String url2 = "https://ohtustats2017.herokuapp.com/courses/1.json";
        String kurssiText = Request.Get(url2).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Kurssi kurs = mapper.fromJson(kurssiText, Kurssi.class);

        System.out.println("\n\nKurssi: " + kurs.getName() + ", " + kurs.getTerm() + "\n");

        System.out.println("opiskelijanumero: " + studentNr + "\n");
        int tunnit = 0;
        int tehtavat = 0;

        for (Submission submission : subs) {

            int montaTehtavaa = 0;
            for (Boolean tehdytTehtavat : submission.getTehdytTehtavat()) {
                if (!((tehdytTehtavat) == null)) {
                    if (tehdytTehtavat == true) {
                        montaTehtavaa++;
                    }
                }
            }

            System.out.print("viikko " + submission.getWeek() + ": tehtyjä tehtäviä yhteensä: "
                    + montaTehtavaa + " (maksimi " + kurs.getWeek(submission.getWeek()) + "), aikaa kului " + submission.getHours()
                    + " tuntia, tehdyt tehtävät: ");

            int i = 1;
            for (Boolean tehdytTehtavat : submission.getTehdytTehtavat()) {
                if (!((tehdytTehtavat) == null)) {
                    System.out.print(i + " ");
                }
                i++;
            }
            tunnit += submission.getHours();
            System.out.println("\n\nyhteensä: " + montaTehtavaa + " tehtävää " + tunnit
                    + "tuntia");
        }

    }
}
