package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "014589009";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/"+studentNr+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        Gson mapperSubs = new Gson();
        Submission[] subs = mapperSubs.fromJson(bodyText, Submission[].class);
        
        String courseString = "https://ohtustats2017.herokuapp.com/courses/1.json";
        String courseBodyText = Request.Get(courseString).execute().returnContent().asString();
        Gson mapperCourse = new Gson();
        Course course = mapperCourse.fromJson(courseBodyText, Course.class);
        
        System.out.println("Kurssi: " + course.getName() + ", " + course.getTerm() + "\n");        
        System.out.println("Opiskelijanumero: " + studentNr + "\n");
        
        int overallHours = 0;
        int overallDone = 0;

        for (Submission submission : subs) {
            System.out.println("viikko " + submission.getWeek() + ": tehtyjä tehtäviä yhteensä: " 
                    + submission.howManyDone() + "(maksimi " + course.getWeeks()[submission.getWeek()] + ", aikaa kului " 
                    + submission.getHours() + " tuntia, tehdyt tehtävät: " + submission.whichDone());
            overallHours += submission.getHours();
            overallDone += submission.howManyDone();
        }
        System.out.println("Yhteensä: " + overallDone + " tehtävää " + overallHours + " tuntia");
    }
}