package com.example.master.milionare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class GameOver extends AppCompatActivity {
private TextView mGsmeOverScore;
private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mGsmeOverScore=(TextView)findViewById(R.id.textViewGameOver);
      // score= Objects.requireNonNull(getIntent().getExtras()).getInt("score");
        score= Objects.requireNonNull(getIntent().getExtras()).getInt("scoreIneger");
       String a="You have "+score+"point";
       mGsmeOverScore.setText(a);
    }


    public void onClickNewGame(View view) {
        Intent intent=new Intent(GameOver.this,MainActivity.class);
        startActivity(intent);
    }
}
