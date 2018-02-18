package rrdl.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import rrdl.topquiz.R;
import rrdl.topquiz.model.User;

public class MainActivity extends AppCompatActivity {
    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;
    public static final int GAME_ACTIVITY_REQUEST_CODE=42;
    private SharedPreferences mPreferences;
    public static final String PREF_KEY_SCORE="PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRST_NAME="PREF_KEY_FIRST_NAME";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(GAME_ACTIVITY_REQUEST_CODE==requestCode && RESULT_OK==resultCode){
        int score=data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE,0);
        mPreferences.edit().putInt("PREF_KEY_SCORE",score).apply();

    }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"Hello "+getPreferences(MODE_PRIVATE).getString("PREF_KEY_FIRST_NAME",null)
                +" \n your last score is "+getPreferences(MODE_PRIVATE).getInt("PREF_KEY_SCORE",0)
                +" Points",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUser=new User();
        mPreferences=getPreferences(MODE_PRIVATE);
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
                mPreferences.edit().putString("PREF_KEY_FIRST_NAME",mUser.getFirstName()).apply();
                //Loads the Game activity
                Intent gameActivity=new Intent(MainActivity.this,GameActivity.class);
                startActivityForResult(gameActivity,GAME_ACTIVITY_REQUEST_CODE);
            }
        });

    }
}
