package rrdl.topquiz.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import rrdl.topquiz.R;
import rrdl.topquiz.model.User;

public class MainActivity extends AppCompatActivity {
    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUser=new User();
        //Bind the widgets
        mGreetingText=findViewById(R.id.activity_main_greeting_txt);
        mNameInput=findViewById(R.id.activity_main_name_input);
        mPlayButton=findViewById(R.id.activity_main_play_btn);
        mPlayButton.setEnabled(false);//sets the let's play button as disabled by default
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPlayButton.setEnabled(charSequence.toString().length()!=0);//enables the play button
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname=mNameInput.getText().toString();
                mUser.setFirstName(firstname);
                //Loads the Game activity
                Intent gameActivity=new Intent(MainActivity.this,GameActivity.class);
                startActivity(gameActivity);
            }
        });
    }
}
