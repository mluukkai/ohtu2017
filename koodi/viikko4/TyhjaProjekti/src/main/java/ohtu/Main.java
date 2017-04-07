package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan
        String studentNr = "013593724";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);

        Gson mapper = new Gson();
        System.out.println(kurssintiedot());
        int tunnitYht = 0;
        int tehtavatYht = 0;
        JsonArray array = mapper.fromJson(bodyText, JsonArray.class);
        for (JsonElement viikko : array) {

            JsonObject weeklyStats = mapper.fromJson(viikko, JsonObject.class);
            ArrayList<Integer> viikonPisteet = new ArrayList<>();
            int maxTehtavat = 0;


            for (int i = 1; i < 20; i++) {
                String arvo = "" + weeklyStats.get("a" + i);
                if (!arvo.equals("null")) {
                    maxTehtavat++;
                }
                if (arvo.equals("true")) {
                    viikonPisteet.add(i);
                }

            }
            tunnitYht += Integer.parseInt("" + weeklyStats.get("hours"));
            tehtavatYht += viikonPisteet.size();
            System.out.println("Viikko " + weeklyStats.get("week") + ": tehtyjä tehtäviä yhteensä: " + viikonPisteet.size() + " (maksimi " + maxTehtavat  + "), aikaa kului " + weeklyStats.get("hours") + " tuntia, tehdyt tehtävät: " + lukuTulostus(viikonPisteet));

        }
        System.out.println("yhteensä: " + tehtavatYht + " tehtävää " + tunnitYht + " tuntia");

    }

    public static String lukuTulostus(ArrayList<Integer> luvut) {
        String palautus = "";
        for (Integer luku : luvut) {
            palautus = palautus + luku + " ";
        }
        return palautus;
    }
    
    public static String kurssintiedot() throws IOException {
        Gson mapper = new Gson();

        String urlKurssi = "https://ohtustats2017.herokuapp.com/courses/1.json";

        String course = Request.Get(urlKurssi).execute().returnContent().asString();
        JsonObject kurssi = mapper.fromJson(course, JsonObject.class);
        String ret = "Kurssi: " + kurssi.get("name") + ", " + kurssi.get("term");
        return ret.replaceAll("\"", "");
    }
}
