package com.quizscores.model;

/**
 * Created by jinge on 7/14/15.
 */
public class Student {
    private int num;
    private double[] scores;

    public Student(){}
    public Student(int num, double[] scores){
        this.num= num;
        this.scores= scores;
    }
    public int getNum(){
        return num;
    }

    public double[] getScores() {
        return scores;
    }
}
