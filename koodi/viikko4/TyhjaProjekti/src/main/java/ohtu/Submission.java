package ohtu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Submission {

    private String student_number;
    private Integer week;
    private Integer hours;
    private Boolean a1;
    private Boolean a2;
    private Boolean a3;
    private Boolean a4;
    private Boolean a5;
    private Boolean a6;
    private Boolean a7;
    private Boolean a8;
    private Boolean a9;
    private Boolean a10;
    private Boolean a11;
    private Boolean a12;
    private Boolean a13;
    private Boolean a14;
    private Boolean a15;
    private Boolean a16;
    private Boolean a17;
    private Boolean a18;
    private Boolean a19;
    private Boolean a20;
    private Boolean a21;

    public Boolean getA1() {
        return a1;
    }

    public void setA1(Boolean a1) {
        this.a1 = a1;
    }

    public Boolean getA2() {
        return a2;
    }

    public void setA2(Boolean a2) {
        this.a2 = a2;
    }

    public Boolean getA3() {
        return a3;
    }

    public void setA3(Boolean a3) {
        this.a3 = a3;
    }

    public Boolean getA4() {
        return a4;
    }

    public void setA4(Boolean a4) {
        this.a4 = a4;
    }

    public Boolean getA5() {
        return a5;
    }

    public void setA5(Boolean a5) {
        this.a5 = a5;
    }

    public Boolean getA6() {
        return a6;
    }

    public void setA6(Boolean a6) {
        this.a6 = a6;
    }

    public Boolean getA7() {
        return a7;
    }

    public void setA7(Boolean a7) {
        this.a7 = a7;
    }

    public Boolean getA8() {
        return a8;
    }

    public void setA8(Boolean a8) {
        this.a8 = a8;
    }

    public Boolean getA9() {
        return a9;
    }

    public void setA9(Boolean a9) {
        this.a9 = a9;
    }

    public Boolean getA10() {
        return a10;
    }

    public void setA10(Boolean a10) {
        this.a10 = a10;
    }

    public Boolean getA11() {
        return a11;
    }

    public void setA11(Boolean a11) {
        this.a11 = a11;
    }

    public Boolean getA12() {
        return a12;
    }

    public void setA12(Boolean a12) {
        this.a12 = a12;
    }

    public Boolean getA13() {
        return a13;
    }

    public void setA13(Boolean a13) {
        this.a13 = a13;
    }

    public Boolean getA14() {
        return a14;
    }

    public void setA14(Boolean a14) {
        this.a14 = a14;
    }

    public Boolean getA15() {
        return a15;
    }

    public void setA15(Boolean a15) {
        this.a15 = a15;
    }

    public Boolean getA16() {
        return a16;
    }

    public void setA16(Boolean a16) {
        this.a16 = a16;
    }

    public Boolean getA17() {
        return a17;
    }

    public void setA17(Boolean a17) {
        this.a17 = a17;
    }

    public Boolean getA18() {
        return a18;
    }

    public void setA18(Boolean a18) {
        this.a18 = a18;
    }

    public Boolean getA19() {
        return a19;
    }

    public void setA19(Boolean a19) {
        this.a19 = a19;
    }

    public Boolean getA20() {
        return a20;
    }

    public void setA20(Boolean a20) {
        this.a20 = a20;
    }

    public Boolean getA21() {
        return a21;
    }

    public void setA21(Boolean a21) {
        this.a21 = a21;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
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
        Map<Integer, Boolean> a = getA();
        String teht = "";
        Integer tehdyt = 0;
        for (Map.Entry<Integer, Boolean> entry
                : a.entrySet()) {
            Integer key = entry.getKey();
            Boolean value = entry.getValue();

            if (value) {
                tehdyt++;
            }
            if (value) {
                teht = teht + " " + key;
            }
        }
        return "viikko " + week + ": tehtyjä tehtäviä yhteensä: " + tehdyt + " (maksimi " + a.size() + "), aikaa kului " + hours + " tuntia, tehdyt tehtävät:" + teht;
    }

    public Map<Integer, Boolean> getA() {
        Map<Integer, Boolean> a = new HashMap<Integer, Boolean>();
        if (a1 != null) {
            a.put(1, a1);
        }
        if (a2 != null) {
            a.put(2, a2);
        }
        if (a3 != null) {
            a.put(3, a3);
        }
        if (a4 != null) {
            a.put(4, a4);
        }
        if (a5 != null) {
            a.put(5, a5);
        }
        if (a6 != null) {
            a.put(6, a6);
        }
        if (a7 != null) {
            a.put(7, a7);
        }
        if (a8 != null) {
            a.put(8, a8);
        }
        if (a9 != null) {
            a.put(9, a9);
        }
        if (a10 != null) {
            a.put(10, a10);
        }
        if (a11 != null) {
            a.put(11, a11);
        }
        if (a12 != null) {
            a.put(12, a12);
        }
        if (a13 != null) {
            a.put(13, a13);
        }
        if (a14 != null) {
            a.put(14, a14);
        }
        if (a15 != null) {
            a.put(15, a15);
        }
        if (a16 != null) {
            a.put(16, a16);
        }
        if (a17 != null) {
            a.put(17, a17);
        }
        if (a18 != null) {
            a.put(18, a18);
        }
        if (a19 != null) {
            a.put(19, a19);
        }
        if (a20 != null) {
            a.put(20, a20);
        }
        if (a21 != null) {
            a.put(21, a21);
        }

        return a;
    }

}
