package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan
        String studentNr = "014248162";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        System.out.println("Kurssi: Ohjelmistotuotanto, kevät 2017");
        System.out.println("");
        System.out.println("opiskelijanumero: "+subs[0].getStudent_number());
        System.out.println("");
        
        Integer teht = 0;
        Integer tuntia = 0;
        
        for (Submission submission : subs) {
            teht += submission.getA().size();
            tuntia += submission.getHours();
            System.out.println(submission);
        }
        System.out.println("");
        System.out.println("yhteensä: " + teht+ " tehtävää "+tuntia+" tuntia");
    }
}