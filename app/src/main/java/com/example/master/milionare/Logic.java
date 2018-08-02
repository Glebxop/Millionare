package com.example.master.milionare;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Logic {


    public ArrayList<Integer> getIntegers(){
    ArrayList<Integer> integers=new ArrayList<>(4);
        for (int i = 0; i <100 ; i++) {
            int a=(int)(Math.random()*4);
            if (!integers.contains(a)){
                integers.add(a);
                if (integers.size()==4){
                    break;
                }
            }
        }

    return integers;}



    public String findTrueAnswerTXT(Qestion qestion){
        String trueAnswer = null;
        for (int i = 0; i <qestion.getAnswers().size() ; i++) {
            if (qestion.getAnswers().get(i).isRightOrNo())
                trueAnswer=qestion.getAnswers().get(i).getAnswer();
        }return trueAnswer;
    }

}
