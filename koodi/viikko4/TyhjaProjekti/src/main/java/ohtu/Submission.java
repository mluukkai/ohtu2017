package ohtu;

public class Submission {

    public Submission() {
        
    }
    private String student_number;
    private String week;
    private String hours;
//
//    public Submission() {
//        Submission.class.getAnnotations();
//    }
    
    
    
    public String getHours() {
        return hours;
    }
    

    public void setHours(String hours) {
        this.hours = hours;
    }
    
   

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    @Override
    public String toString() {
//        System.out.println(Submission.class.);
        return  "viikko " + week + ": tehtyjä tehtäviä yhteensä: " + "luku" + ", aikaa kului " + hours + " tuntia, tehdyt tehtävät: ";
//         viikko 1: tehtyjä tehtäviä yhteensä: 9, aikaa kului 3 tuntia, tehdyt tehtävät: 1 2 3 4 5 6 7 9 11 

    }
    
}