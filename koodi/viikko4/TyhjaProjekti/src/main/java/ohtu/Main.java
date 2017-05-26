package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) {
        String studentNr = "013736714";
        if (args.length > 0) {
            studentNr = args[0];
        }
        String kurssi = null;
        String vuosi = null;
        String url = "https://ohtustats2017.herokuapp.com/courses/1.json";
        String bodyText = null;
        try {
            bodyText = Request.Get(url).execute().returnContent().asString();
        } catch (Exception e) {
            System.err.println("Failed to get JSON data.");
            System.exit(-1);
        }

        kurssi = new JsonParser().parse(bodyText).getAsJsonObject().get("nimi").toString();
        vuosi = new JsonParser().parse(bodyText).getAsJsonObject().get("vuosi").toString();

        url = "http://ohtustats2017.herokuapp.com/students/" + studentNr + "/submissions";
        bodyText = null;
        try {
            bodyText = Request.Get(url).execute().returnContent().asString();
        } catch (Exception e) {
            System.err.println("Failed to get JSON data.");
            System.exit(-1);
        }

        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        System.out.println("Oliot:");
        System.out.println("Kurssi: " + kurssi + ", " + vuosi);
        System.out.println();
        System.out.println("opiskelijanumero: " + studentNr);
        System.out.println();
        Integer tunnitYht = 0;
        Integer tehtYht = 0;
        for (Submission submission : subs) {
            System.out.println(submission);
            tehtYht += submission.yht;
        }
        System.out.println();
        System.out.println("yhteensä: " + tehtYht + " tehtävää " + tunnitYht + " tuntia");
    }
}
