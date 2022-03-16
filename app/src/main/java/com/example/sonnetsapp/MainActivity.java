package com.example.sonnetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/***************************************************
 * Class: MainActivity
 * Purpose: Primary screen for sonnetsapp project.
 * Displays a NumberPicker to the user to select
 * a sonnet number. Passes sonnet information to
 * DisplaySonnetActivity when a sonnet is selected.
 ***************************************************/
public class MainActivity extends AppCompatActivity {

    // class variables
    private TextView textView; // display selection prompt
    private NumberPicker numberPicker; // UI for selecting a sonnet number
    private long numberPickerUBound; // holds max number for numberPicker

    public static final String SONNET = "com.example.sonnetsapp.SONNET"; // name for Extra

    private FirebaseFirestore db; // connection to Firestore
    private ArrayList<String> selectedSonnet; // ArrayList for holding sonnet text from Firestore

    /***************************************************
     * Function: onCreate
     * Purpose: sets up the primary app screen when the
     * app is started. Includes a text prompt, number
     * picker, and select button.
     ***************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set textView and numberPicker to the views in xml file
        textView = (TextView) findViewById(R.id.textSelectSonnet);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);

        // establish connection to Firestore
        db = FirebaseFirestore.getInstance();


        // get reference to Data document in sonnets collection in Firestore
        DocumentReference docRef = db.collection("sonnets").document("Data");

        // get data from Data document
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    // store document data in data Map
                    Map<String, Object> data = document.getData();
                    // set upper bound variable for number picker to number of sonnets in data
                    numberPickerUBound = (long) data.get("numSonnets");
                } else {

                    numberPickerUBound = 1;
                }
            } else {
                // set to one if query fails
                numberPickerUBound = 1;
            }

            // set lower, upper, and starting values for the number picker
            numberPicker.setMinValue(1);
            numberPicker.setMaxValue((int) numberPickerUBound);
            numberPicker.setValue(1);
        }); // CompleteListener

    }

    /***************************************************
     * Function: displaySonnet
     * Purpose: When the select button is touched,
     * this function queries Firestore for the selected
     * sonnet's information. The sonnet's text is passed
     * to a new DisplaySonnetActivity for display.
     ***************************************************/
    public void displaySonnet(View view) {

        // get the value from the number picker
        int pickValue = numberPicker.getValue();
        // convert that value to document name format in Firestore
        String pickSonnet = "Sonnet " + pickValue;

        // get reference to sonnet document in sonnets collection in Firestore
        DocumentReference docRef = db.collection("sonnets").document(pickSonnet);

        // get sonnet text from the sonnet document
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    // store document data in data Map
                    Map<String, Object> data = document.getData();
                    // store text of sonnet
                    selectedSonnet = (ArrayList<String>) data.get("text");
                } else {
                    // store error message if query fails
                    selectedSonnet = (ArrayList<String>) Arrays.asList(("This sonnet could not be displayed").split(" "));
                }
            } else {
                // store error message if query fails
                selectedSonnet = (ArrayList<String>) Arrays.asList(("This sonnet could not be displayed").split(" "));
            }

            // create new intent for DisplaySonnetActivity
            Intent intent = new Intent(this, DisplaySonnetActivity.class);

            // add sonnet text as Extra then start activity
            intent.putExtra(SONNET, selectedSonnet);
            startActivity(intent);
        });
    }
}