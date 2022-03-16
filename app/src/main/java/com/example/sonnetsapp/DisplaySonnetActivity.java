package com.example.sonnetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;

/***************************************************
 * Class: DisplaySonnetActivity
 * Purpose: Activity to display the text of a sonnet.
 ***************************************************/
public class DisplaySonnetActivity extends AppCompatActivity {

    private ArrayList<String> sonnet;

    /***************************************************
     * Function: onCreate
     * Purpose: When the activity is started, extracts
     * the sonnet text provided as an Extra and adds
     * it to the textView.
     ***************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sonnet);

        Intent intent = getIntent();
        sonnet = intent.getStringArrayListExtra(MainActivity.SONNET);

        StringBuilder joinedSonnet = new StringBuilder();

        for (String text : sonnet) {
            joinedSonnet.append(text).append("\n\n");
        }

        TextView textView = findViewById(R.id.textView);
        textView.setText(joinedSonnet.toString());
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}