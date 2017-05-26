package ohtu;

import java.util.List;

public class Submission {
    private String student_number;
    private int week;
    private int hours;

    private List<Boolean> answers;

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public List<Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Boolean> answers) {
        this.answers = answers;
    }

    public int getWeek() {
        return week;
    }

    public int getHours() {
        return hours;
    }

    public Submission(String student_number, int week, int hours, List<Boolean> answers) {
        this.student_number = student_number;
        this.week = week;
        this.hours = hours;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return student_number;
    }
    
}