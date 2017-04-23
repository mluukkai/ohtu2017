package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, 
        //ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "asd";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = 
                "http://ohtustats2017.herokuapp.com/students/" +
                studentNr +
                "/submissions";

        String bodyText = Request.Get(url)
                .execute()
                .returnContent()
                .asString();

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        System.out.println("Oliot:");
        
        System.out.println("Kurssi: Ohjelmistotuotanto, kevät 2017\n" +
            "\n" +
            "opiskelijanumero: " + subs[0].student_number);
        int points = 0;
        int hours = 0;
        for (Submission submission : subs) {
            System.out.println(submission);
            points += submission.points;
            hours += submission.hours;
        }
        System.out.println(String.format("\n\nyhteensä: %d tehtävää %d tuntia" ,points,hours));

    }
}