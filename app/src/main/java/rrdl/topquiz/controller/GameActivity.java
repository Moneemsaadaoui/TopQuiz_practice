package rrdl.topquiz.controller;

import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import rrdl.topquiz.R;
import rrdl.topquiz.model.Question;
import rrdl.topquiz.model.QuestionBank;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mGameQuestion;
    private Button mAnswer1;
    private Button mAnswer2;
    private Button mAnswer3;
    private Button mAnswer4;
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mScore;
    private int mNumberOfQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mQuestionBank=this.GenerateQuestions();
        mNumberOfQuestions=3;
        //Wiring the widgets..
        mGameQuestion=findViewById(R.id.game_activity_textview);
        mAnswer1=findViewById(R.id.activity_game_answer1_btn);
        mAnswer2=findViewById(R.id.activity_game_answer2_btn);
        mAnswer3=findViewById(R.id.activity_game_answer3_btn);
        mAnswer4=findViewById(R.id.activity_game_answer4_btn);
        //Making buttons do action when clicked
        mAnswer1.setOnClickListener(this);
        mAnswer2.setOnClickListener(this);
        mAnswer3.setOnClickListener(this);
        mAnswer4.setOnClickListener(this);
        //Assigning tags to buttons
        mAnswer1.setTag(0);
        mAnswer2.setTag(1);
        mAnswer3.setTag(2);
        mAnswer4.setTag(3);


        //Get the current Question
        mCurrentQuestion=mQuestionBank.getQuestion();
        //Assign Values to the views
        this.DisplayQuestion(mCurrentQuestion);
    }
    //Method that sets that populates the game interface with correct values and texts.
    private void DisplayQuestion(final Question question)
    {
        mGameQuestion.setText(question.getQuestion());
        mAnswer1.setText(question.getChoiceList().get(0));
        mAnswer2.setText(question.getChoiceList().get(1));
        mAnswer3.setText(question.getChoiceList().get(2));
        mAnswer4.setText(question.getChoiceList().get(3));

    }
    //Generate and populate a QuestionsBank (i think there's better ways to do this)
    private QuestionBank GenerateQuestions(){

        Question question1=new Question("What is the best band ever ?", Arrays.asList("Metallica"
        ,"Megadeth","OHM","Nightwish"),3);
        Question question2=new Question("What's the best power metal band from finland ?", Arrays.asList("Sonata arctica"
        ,"Blind guardian","OHM","Nightwish"),0);
        Question question3=new Question("This synthesizer was release in 1989 and features 8 part multi-timberality ?", Arrays.asList("Korg M1"
        ,"Roland D50","Fender stratocaster","Piano"),0);
         Question question4=new Question("What is the answer to everything ?", Arrays.asList("42"
        ,"Netbeans","Gta vice city","Nightwish"),0);
         return new QuestionBank(Arrays.asList(question1,question2,question3,question4));
    }
    @Override
    //Implementation of the onclickListener
    public void onClick(View view) {
        int responseIndex = (int) view.getTag();

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
                    } else {
            // Wrong answer
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
        }
        if(--mNumberOfQuestions==0)
        {
            endGame();
        }else
        {
            mCurrentQuestion=mQuestionBank.getQuestion();
            DisplayQuestion(mCurrentQuestion);
        }
    }
    private void endGame()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Well done:").setMessage("Your score is :"+mScore).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).create().show();
    }
}
