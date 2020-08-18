/*Written by Srivastchavan Rengarajan for CS6326.001 Android Assignment Phase 1, starting March 10
Net ID: sxr190067

This is activity for adding high scores
The inputs for this functionality is Name, highscore and datetime.
 If the save button is pressed the data is sent to the MainActivity.
 */


package com.example.asg5_sxr190067;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// CLass for activity to add high scores
public class AddHighScoresActivity extends AppCompatActivity {

    EditText name;
    EditText score;
    EditText date;

    MenuItem save;

    boolean btnFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhighscores);

        setTitle("Add High Score");

        name = (EditText) findViewById(R.id.newName);
        score = (EditText) findViewById(R.id.newScore);
        date = (EditText) findViewById(R.id.newDate);

        SimpleDateFormat simpledate= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        date.setText(simpledate.format(new Date()));

        name.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                validate();
            }
        });

        score.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                validate();
            }
        });

        date.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                validate();
            }
        });
    }
    // Validate name, score and date and enable the save button
    private void validate(){
        String newName = name.getText().toString();
        String newScore = score.getText().toString();
        String newDate = date.getText().toString();

        boolean isValidName = false;
        boolean isValidScore = false;
        boolean isValidDate = false;

        if(newName.length()>0 && newName.length() <=30){
            isValidName = true;
            name.setError(null);
        }
        else{
            name.setError("Player name can be maximum of 30 characters and can't be empty");
        }

        if(!newScore.isEmpty()){
            try {
                int intScore = Integer.parseInt(newScore);
                if (intScore <= 0) {
                    score.setError("Score should be a positive integer");
                } else {
                    isValidScore = true;
                    score.setError(null);
                }
            } catch (Exception e){
                score.setError("Score should be a positive integer");
            }
        }

        if(!newDate.isEmpty()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            dateFormat.setLenient(false);
            Date current = new Date();
            try {
                Date nDate = dateFormat.parse(newDate.trim());
                int value = current.compareTo(nDate);
                if(value<0){
                    btnFlag = false;
                    date.setError("Date cannot be from the future");
                }
                isValidDate = true;
                date.setError(null);

            } catch (ParseException pe) {
                date.setError("Date should of the format MM/dd/yyyy HH:mm:ss");

            }

        }

        if(isValidName && isValidDate && isValidScore){
            btnFlag=true;
        }
        invalidateOptionsMenu();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_score, menu);
        save = menu.findItem(R.id.save);
        save.setEnabled(btnFlag);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        MenuItem itm = menu.findItem(R.id.save);

        if(btnFlag){
            itm.setEnabled(true);
            name.setError(null);
            score.setError(null);
            date.setError(null);
            itm.getIcon().setAlpha(255);
        }
        else{
            itm.setEnabled(false);
            itm.getIcon().setAlpha(130);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.save){
            Intent rtnIntent = new Intent();
            rtnIntent.putExtra("newName", name.getText().toString());
            rtnIntent.putExtra("newScore", score.getText().toString());
            rtnIntent.putExtra("newDate", date.getText().toString());

            setResult(Activity.RESULT_OK, rtnIntent);
            finish();
            return true;
        }
        return false;
    }

}
