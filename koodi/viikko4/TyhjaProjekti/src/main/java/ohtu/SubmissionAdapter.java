package ohtu;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SubmissionAdapter implements JsonDeserializer<Submission> {
    @Override
    public Submission deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();

        List<Boolean> answers = obj.entrySet().stream()
            .filter(e -> e.getKey().startsWith("a"))
            .map(e -> {
                Boolean value = e.getValue().isJsonNull() ? null : e.getValue().getAsBoolean();
                return Tuple2.of(Integer.parseInt(e.getKey().substring(1)), value);
            })
            .sorted(Comparator.comparingInt(Tuple2::getA))
            .map(Tuple2::getB)
            .collect(Collectors.toList());

        // Java ei sisällä .takeWhileä (ennen JDK9), joten typistetään lista käsin
        answers = answers.subList(0, answers.indexOf(null));

        Gson gson = new Gson();
        Submission submission = gson.fromJson(obj, Submission.class);
        submission.setAnswers(answers);
        return submission;
    }
}
