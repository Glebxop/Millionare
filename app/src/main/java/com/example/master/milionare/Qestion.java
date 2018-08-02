package com.example.master.milionare;

import java.util.ArrayList;

public class Qestion {

    private String qestion;
    private ArrayList<Answer> answers;

    public Qestion(String qestion, ArrayList<Answer> answers) {
        this.qestion = qestion;
        this.answers = answers;
    }

    public String getQestion() {
        return qestion;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
