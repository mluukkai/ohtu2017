package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "Nönnönnöö";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String submissionUrl = "http://ohtustats2017.herokuapp.com/students/" + studentNr + "/submissions";
        String courseUrl = "http://ohtustats2017.herokuapp.com/courses/1.json";

        String submissionsJson = Request.Get(submissionUrl).execute().returnContent().asString();
        String courseJson = Request.Get(courseUrl).execute().returnContent().asString();

//        System.out.println("json-muotoinen data:");
//        System.out.println( submissionsJson );
        Gson mapper = new Gson();
        Course course = mapper.fromJson(courseJson, Course.class);
        Submission[] subs = mapper.fromJson(submissionsJson, Submission[].class);
        if (subs.length > 0) {
            System.out.println(course.getName() + ", " + course.getTerm());
            System.out.println("");
            System.out.println("Opiskelijanumero: " + subs[0].getStudent_number());
            System.out.println("");

            for (Submission submission : subs) {
                submission.setCourse(course);
                System.out.println(submission);
            }
        }

    }
}
