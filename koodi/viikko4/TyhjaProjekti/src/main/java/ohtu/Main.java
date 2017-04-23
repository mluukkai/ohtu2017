package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.GsonBuilder;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan
        String studentNr = "014587292";
        if ( args.length>0) {
            studentNr = args[0];
        }

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Submission.class, new SubmissionAdapter());
        Gson mapper = builder.create();

        String courseInfoJson = Request.Get("https://ohtustats2017.herokuapp.com/courses/1.json").execute().returnContent().asString();
        CourseInfo courseInfo = mapper.fromJson(courseInfoJson, CourseInfo.class);

        String submissionsUrl = "http://ohtustats2017.herokuapp.com/students/"+studentNr+"/submissions";
        String submissionsJson = Request.Get(submissionsUrl).execute().returnContent().asString();
        Submission[] subs = mapper.fromJson(submissionsJson, Submission[].class);

        System.out.printf("Kurssi: %s, %s\n\n", courseInfo.getName(), courseInfo.getTerm());

        System.out.printf("opiskelijanumero: %s\n\n", studentNr);

        for (Submission submission : subs) {
            List<Boolean> answers = submission.getAnswers();

            List<Integer> done = IntStream.range(0, answers.size())
                .filter(answers::get)
                .mapToObj(i -> i + 1)
                .collect(Collectors.toList());

            String doneS = done.stream().map(Object::toString).collect(Collectors.joining(" "));

            System.out.printf("  viikko %d: ", submission.getWeek());
            System.out.printf("tehtyjä tehtäviä yhteensä: %d, ", done.size());
            System.out.printf("aikaa kului %d tuntia, ", submission.getHours());
            System.out.printf("tehdyt tehtävät: %s\n", doneS);
        }

        long done = Arrays.stream(subs).flatMap(s -> s.getAnswers().stream()).filter(x -> x).count();
        int hours = Arrays.stream(subs).mapToInt(s -> s.getHours()).sum();

        System.out.println();
        System.out.printf("yhteensä: %d tehtävää %d tuntia", done, hours);
    }
}