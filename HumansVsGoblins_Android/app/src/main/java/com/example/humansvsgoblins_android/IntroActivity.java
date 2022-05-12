package com.example.humansvsgoblins_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
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
                    //Clean up with string variables for text box validation msg values

                    if (!(userInput.equals("Name") || userInput.equals("")
                            || userInput.equals("Please input Name!")
                            || userInput.equals("WHOA...too many characters!")
                            || userInput.length() > 10
                            || userInput.contains(" "))) {
                        Intent enterIntent = new Intent((getApplicationContext()), GameBoardActivity.class);
                        startActivity(enterIntent);
                    }else{
                        if(userInput.length() > 10 && !userInput.equals("WHOA...too many characters!")){
                            userInputName.setText("WHOA...too many characters!");
                        }else if(userInput.contains(" ") && !(userInput.equals("WHOA...too many characters!")|| userInput.equals("No Spaces!"))){
                            userInputName.setText("No Spaces!");
                        }else{
                            userInputName.setText("Please input Name!");
                        }
                    }
                }
            });



    }
}