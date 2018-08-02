package com.example.master.milionare;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mA;
    private Button mB;
    private Button mC;
    private Button mD;

    private TextView mQestion;
    private TextView score;
    Logic logic;
    private ArrayList<Qestion>mArrQestions;
    private int mNumberQestion;
    private Qestion qestion;
    private String trueAnswer;
    private boolean mIhaveChoice;
    private int scoreInt;
    private static final String KEY_SKORE = "SKORE";
    private static final String KEY_NUMQES = "NUMQES";
    private static final String KEY_IHAVE = "IHAVE";
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            mNumberQestion++;
            checNumber();
            fillActivity();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mA=(Button)findViewById(R.id.buttonOne);
        mA.setOnClickListener(this);
        mB=(Button)findViewById(R.id.buttonTwice);
        mB.setOnClickListener(this);
        mC=(Button)findViewById(R.id.button2Three);
        mC.setOnClickListener(this);
        mD=(Button)findViewById(R.id.buttonFour);
        mD.setOnClickListener(this);

        mQestion=(TextView)findViewById(R.id.textViewMil);
        mArrQestions=new ArrayList<>();
        logic=new Logic();
        fillQest();
        score=(TextView)findViewById(R.id.textViewScore);
        scoreInt=0;
        if (savedInstanceState!=null){
            scoreInt=savedInstanceState.getInt(KEY_SKORE);
            mIhaveChoice=savedInstanceState.getBoolean(KEY_IHAVE);
            mNumberQestion=savedInstanceState.getInt(KEY_NUMQES);
        }
        String a=String.valueOf(scoreInt);
        score.setText(a);
        fillActivity();


    }

   private void fillActivity(){
        mIhaveChoice=true;

        mA.setBackgroundResource(R.color.botton);
        mB.setBackgroundResource(R.color.botton);
        mC.setBackgroundResource(R.color.botton);
        mD.setBackgroundResource(R.color.botton);
        qestion=mArrQestions.get(mNumberQestion);
        trueAnswer=logic.findTrueAnswerTXT(qestion);
        mQestion.setText(qestion.getQestion());
        fillAnswers();



    }

    private void fillAnswers(){

        ArrayList<Integer>arr=logic.getIntegers();

        mA.setText(qestion.getAnswers().get(arr.get(0)).getAnswer());
        mB.setText(qestion.getAnswers().get(arr.get(1)).getAnswer());
        mC.setText(qestion.getAnswers().get(arr.get(2)).getAnswer());
        mD.setText(qestion.getAnswers().get(arr.get(3)).getAnswer());
    }

    @Override
    public void onClick(View v) {
if (mIhaveChoice){
        switch (v.getId()){
            case R.id.buttonOne:
               if (mA.getText().equals(trueAnswer)){
                   ifTrueAnswer(mA);
                                }else {

                   ifFalseAnswer(mA);
               }

                break;
            case R.id.buttonTwice:
                if (mB.getText().equals(trueAnswer)){

                    ifTrueAnswer(mB);
                }else {
                   ifFalseAnswer(mB);
                }

                break;
            case R.id.button2Three:
                if (mC.getText().equals(trueAnswer)){

                    ifTrueAnswer(mC);
                }else {
                    ifFalseAnswer(mC);
                }

                break;
            case R.id.buttonFour:
                if (mD.getText().equals(trueAnswer)){
                     ifTrueAnswer(mD);
                }else {
                    ifFalseAnswer(mD);
                }

                break;
        }}
        mIhaveChoice=false;



    }

    public void ifTrueAnswer(Button button){
        button.setBackgroundResource(R.color.green);
        scoreInt++;
        score.setText(String.valueOf(scoreInt));
        timerNextAnsw();
    }
    void timerNextAnsw(){
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(runnable);
            }
        };
        timer.schedule(timerTask,2000);
    }

    public void ifFalseAnswer(Button button){
        button.setBackgroundResource(R.color.colorRed);
        findTrue();
        final Intent intent=new Intent(MainActivity.this,GameOver.class);
        intent.putExtra("scoreIneger",scoreInt);


        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);


                    }
                });
            }
        };
        Timer timer=new Timer();
        timer.schedule(timerTask,1000);



        scoreInt=0;

    }

    private void findTrue(){
        if (mA.getText().equals(trueAnswer)){
            mA.setBackgroundResource(R.color.green);
        }
        else if (mB.getText().equals(trueAnswer)){
            mB.setBackgroundResource(R.color.green);
        }
        else if (mC.getText().equals(trueAnswer)){
            mC.setBackgroundResource(R.color.green);
        }
        else if (mD.getText().equals(trueAnswer)){
            mD.setBackgroundResource(R.color.green);
        }
    }




    private void checNumber(){
        if (mNumberQestion>=mArrQestions.size()){
            mNumberQestion=0;
        }
    }

    private void fillQest(){
        ArrayList<Answer>firstA=new ArrayList<>();
        firstA.add(new Answer("Леопард",false));
        firstA.add(new Answer("Пума",true));
        firstA.add(new Answer("Лев",false));
        firstA.add(new Answer("Гиена",false));
        Qestion first=new Qestion("Какое животное имеет второе название — кугуар?",firstA);
        mArrQestions.add(first);

        ArrayList<Answer>firstb=new ArrayList<>();
        firstb.add(new Answer("Кинолог",false));
        firstb.add(new Answer("Зоолого",false));
        firstb.add(new Answer("Браколог",false));
        firstb.add(new Answer("Уфолог",true));
        Qestion q2=new Qestion("Какой специалист занимается изучением неопознанных летающих объектов? ",firstb);
        mArrQestions.add(q2);

        ArrayList<Answer>firstc=new ArrayList<>();
        firstc.add(new Answer("Глаз",false));
        firstc.add(new Answer("Ухо",false));
        firstc.add(new Answer("Вымя",false));
        firstc.add(new Answer("Рука",true));
        Qestion q3=new Qestion("Что такое десница?",firstc);
        mArrQestions.add(q3);

        ArrayList<Answer>firstd=new ArrayList<>();
        firstd.add(new Answer("Пыл девичества ",false));
        firstd.add(new Answer("Пыл лесничества ",false));
        firstd.add(new Answer("Ум Отечества ",false));
        firstd.add(new Answer("Дым Отечества",true));
        Qestion q4=new Qestion("О чём писал Грибоедов, отмечая, что он «нам сладок и приятен» ? ",firstd);
        mArrQestions.add(q4);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SKORE,scoreInt);
        outState.putInt(KEY_NUMQES,mNumberQestion);
        outState.putBoolean(KEY_IHAVE,mIhaveChoice);

    }
}
