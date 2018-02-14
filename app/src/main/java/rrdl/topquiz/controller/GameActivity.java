package rrdl.topquiz.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import rrdl.topquiz.R;

public class GameActivity extends AppCompatActivity {
    private TextView mGameQuestion;
    private Button mAnswer1;
    private Button mAnswer2;
    private Button mAnswer3;
    private Button mAnswer4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mGameQuestion=findViewById(R.id.game_activity_textview);
        mAnswer1=findViewById(R.id.activity_game_answer1_btn);
        mAnswer2=findViewById(R.id.activity_game_answer2_btn);
        mAnswer3=findViewById(R.id.activity_game_answer3_btn);
        mAnswer4=findViewById(R.id.activity_game_answer4_btn);


    }
}
