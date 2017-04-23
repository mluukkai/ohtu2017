package ohtu;

public class Submission {
    private String student_number;
    private String hours;
    private int week;
    
    private Course course;
    
    private boolean a1;
    private boolean a2;
    private boolean a3;
    private boolean a4;
    private boolean a5;
    private boolean a6;
    private boolean a7;
    private boolean a8;
    private boolean a9;
    private boolean a10;
    private boolean a11;
    private boolean a12;
    

    
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public boolean getA1() {
        return a1;
    }

    public boolean getA2() {
        return a2;
    }

    public boolean getA3() {
        return a3;
    }

    public boolean getA4() {
        return a4;
    }

    public boolean getA5() {
        return a5;
    }

    public boolean getA6() {
        return a6;
    }

    public boolean getA7() {
        return a7;
    }

    public boolean getA8() {
        return a8;
    }

    public boolean getA9() {
        return a9;
    }

    public boolean getA10() {
        return a10;
    }

    public boolean getA11() {
        return a11;
    }

    public boolean getA12() {
        return a12;
    }

    public void setA1(boolean a1) {
        this.a1 = a1;
    }

    public void setA2(boolean a2) {
        this.a2 = a2;
    }

    public void setA3(boolean a3) {
        this.a3 = a3;
    }

    public void setA4(boolean a4) {
        this.a4 = a4;
    }

    public void setA5(boolean a5) {
        this.a5 = a5;
    }

    public void setA6(boolean a6) {
        this.a6 = a6;
    }

    

    public void setA7(boolean a7) {
        this.a7 = a7;
    }

    public void setA8(boolean a8) {
        this.a8 = a8;
    }

    public void setA9(boolean a9) {
        this.a9 = a9;
    }

    public void setA10(boolean a10) {
        this.a10 = a10;
    }

    public void setA11(boolean a11) {
        this.a11 = a11;
    }

    public void setA12(boolean a12) {
        this.a12 = a12;
    }
    
    
    
    @Override
    public String toString() {
        return "Viikko " + week + ": tehtyjä tehtäviä " + 
                countDone() + " (maksimi " + course.maxEx(week) + "), aikaa meni " + hours + " tuntia. Tehdyt tehtävät: " + doneString();
    }
    
    private String doneString(){
        String done = "";
        if(a1) {
            done += "1 ";
        }
        if(a2) {
            done += "2 ";
        }
        if(a3) {
            done += "3 ";
        }
        if(a4) {
            done += "4 ";
        }
        if(a5) {
            done += "5 ";
        }
        if(a6) {
            done += "6 ";
        }
        if(a7) {
            done += "7 ";
        }
        if(a8) {
            done += "8 ";
        }
        if(a9) {
            done += "9 ";
        }
        if(a10) {
            done += "10 ";
        }
        if(a11) {
            done += "11 ";
        }
        if(a12) {
            done += "12 ";
        }
        return done;
    }
    
    private int countDone(){
        int i = 0;
        if(a1) {
            i++;
        }
        if(a2) {
            i++;
        }
        if(a3) {
            i++;
        }
        if(a4) {
            i++;
        }
        if(a5) {
            i++;
        }
        if(a6) {
            i++;
        }
        if(a7) {
            i++;
        }
        if(a8) {
            i++;
        }
        if(a9) {
            i++;
        }
        if(a10) {
            i++;
        }
        if(a11) {
            i++;
        }
        if(a12) {
            i++;
        }
        return i;
    }
    
}