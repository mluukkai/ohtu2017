package ohtu;

public class Submission {

    private String student_number;
    private int week;
    private int points;
    private int hours;
    private Boolean a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21;
    
    

    public int getWeek() {
        return week;
    }

    public int getPoints() {
        return points;
    }

    public int getHours() {
        return hours;
    }

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
    
    public Boolean[] getTehdytTehtavat(){
        Boolean[] tehdytTehtavat = {a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21};
        return tehdytTehtavat;
    }

}
