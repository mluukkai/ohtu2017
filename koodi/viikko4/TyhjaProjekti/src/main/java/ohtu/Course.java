/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;

/**
 *
 * @author villepaa
 */
public class Course {
    
    private String name;
    private String term;
    private int week1;
    private int week2;
    private int week3;
    private int week4;
    private int week5;
    private int week6;
    
    public Course(String term,String name, int week1, int week2, int week3, int week4, int week5, int week6){
        this.name = name;
        this.term = term;
        this.week1 = week1;
        this.week2 = week2;
        this.week3 = week3;
        this.week4 = week4;
        this.week5 = week5;
        this.week6 = week6;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getWeek1() {
        return week1;
    }

    public void setWeek1(int week1) {
        this.week1 = week1;
    }

    public int getWeek2() {
        return week2;
    }

    public void setWeek2(int week2) {
        this.week2 = week2;
    }

    public int getWeek3() {
        return week3;
    }

    public void setWeek3(int week3) {
        this.week3 = week3;
    }

    public int getWeek4() {
        return week4;
    }

    public void setWeek4(int week4) {
        this.week4 = week4;
    }

    public int getWeek5() {
        return week5;
    }

    public void setWeek5(int week5) {
        this.week5 = week5;
    }

    public int getWeek6() {
        return week6;
    }

    public void setWeek6(int week6) {
        this.week6 = week6;
    }

    public int returnWeek(int number){
        ArrayList weeks = new ArrayList<Integer>();
        weeks.add(week1);
        weeks.add(week2);
        weeks.add(week3);
        weeks.add(week4);
        weeks.add(week5);
        weeks.add(week6);
        return (int)weeks.get(number);
    }
    
    @Override
    public String toString() {
        
        return "Kurssi: " + name + ", " + term;
    }
}
