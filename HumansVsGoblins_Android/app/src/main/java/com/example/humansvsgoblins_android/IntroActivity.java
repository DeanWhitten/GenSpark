package com.example.humansvsgoblins_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button enter_btn = findViewById(R.id.button);
        EditText userInputName = (EditText) findViewById(R.id.editTextTextPersonName);


            enter_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userInput = userInputName.getText().toString();

                    String tooManyChar = "WHOA...too many characters!";
                    String hasSpaces ="No Spaces!";
                    String inputPrompt = "Please input Name!";

                    if (!(userInput.equals("Name") || userInput.equals("")
                            || userInput.equals(inputPrompt) || userInput.equals(tooManyChar)
                            || userInput.length() > 10 || userInput.contains(" "))) {

                        Intent enterIntent = new Intent((getApplicationContext()), GameBoardActivity.class);
                        enterIntent.putExtra("passedName", userInput);
                        startActivity(enterIntent);

                    }else{

                        if(userInput.length() > 10 && !userInput.equals(tooManyChar)){
                            userInputName.setText(tooManyChar);
                        }else if(userInput.contains(" ") && !(userInput.equals(tooManyChar)|| userInput.equals(hasSpaces))){
                            userInputName.setText(hasSpaces);
                        }else{
                            userInputName.setText(inputPrompt);
                        }

                    }
                }
            });



    }
}