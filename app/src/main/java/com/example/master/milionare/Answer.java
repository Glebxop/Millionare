package com.example.master.milionare;

public class Answer {

   private String answer;
   private boolean rightOrNo;


    public Answer(String answer, boolean rightOrNo) {
        this.answer = answer;
        this.rightOrNo = rightOrNo;

    }



    public String getAnswer() {
        return answer;
    }

    public boolean isRightOrNo() {
        return rightOrNo;
    }
}
