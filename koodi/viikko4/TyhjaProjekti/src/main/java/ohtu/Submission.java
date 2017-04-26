package ohtu;

public class Submission {
    private String student_number;

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    @Override
    public String toString() {
        return student_number;
    }
    
}