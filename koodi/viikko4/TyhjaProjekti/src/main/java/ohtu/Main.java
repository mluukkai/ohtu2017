package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "0123456";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/"+studentNr+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        String url2 = "https://ohtustats2017.herokuapp.com/courses/1.json";
        String bodyText2 = Request.Get(url2).execute().returnContent().asString();
        System.out.println( bodyText2);
        mapper = new Gson();
        Course course = mapper.fromJson(bodyText2, Course.class);

        System.out.println("Kurssi: " + course.getName() + ", " + course.getTerm() + "\n\n" +
                "opiskelijanumero: " + subs[0].getStudent_number() + "\n");
        int points = 0;
        int hours = 0;
        for (Submission submission : subs) {
            submission.setMaxPoints(course.getNext());
            System.out.println(submission);
            points += submission.getPoints();
            hours += submission.getTime();
        }
        System.out.println(String.format("\nyhteensä: %d tehtävää %d tuntia" ,points,hours));

    }
}