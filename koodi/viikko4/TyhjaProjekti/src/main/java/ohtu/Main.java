package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan
        String studentNr = "123456789";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/"+studentNr+"/submissions";
        String courseUrl = "https://ohtustats2017.herokuapp.com/courses/1.json";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
        String courseBodyText = Request.Get(courseUrl).execute().returnContent().asString();
     
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course c = mapper.fromJson(courseBodyText, Course.class);
        
        System.out.println(c);
        System.out.println();
        System.out.println("opiskelijanumero: " + subs[0].getStudent_number() );
        System.out.println();
        for (int i= 0 ; i<subs.length;i++) {
            Submission submission = subs[i];
            System.out.println(submission + " (maksimi "+ c.returnWeek(i) + ")" + submission.tehtavat());
            
            
            
        }
        
    }
}
