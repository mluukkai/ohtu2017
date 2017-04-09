package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "014691751";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        String url2 = "https://ohtustats2017.herokuapp.com/courses/1.json";

        String bodyText2 = Request.Get(url2).execute().returnContent().asString();

        Gson mapper2 = new Gson();
        Course course = mapper2.fromJson(bodyText2, Course.class);
  
        System.out.println("Kurssi: " + course.getName() + ", " + course.getTerm());
        System.out.println("");
        System.out.println("Opiskelijanumero: " + subs[0].getStudent_number());
        System.out.println("");
        int totalA = 0;
        int totalH = 0;
        course.createMaxlist();
        int week = 0;
        for (Submission submission : subs) {
            submission.createDone();
            totalA += submission.getDone().size();
            totalH += submission.getHours();
            System.out.print(" viikko " + submission.getWeek() +": ");
            System.out.print("tehtyjä tehtäviä yhteensä: " + submission.getDone().size() + " (maksimi "+ course.getMaxlist().get(week) + "), ");
            System.out.print("aikaa kului " + submission.getHours() + " tuntia, ");
            System.out.println("tehdyt tehtävät: " + submission.getDone());
            System.out.println("");
            week++;
        }
        System.out.print("yhteensä: " + totalA + " tehtävää " + totalH + " tuntia");

    }
}