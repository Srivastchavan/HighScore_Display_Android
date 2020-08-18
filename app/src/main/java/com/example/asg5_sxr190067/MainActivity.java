/*Written by Srivastchavan Rengarajan for CS6326.001 Android Assignment Phase 1, starting March 10
Net ID: sxr190067

This is activity for main function to display home page
When add button is clicked it is redirected to new activity add new score.
 */

package com.example.asg5_sxr190067;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Class to describe the main activity
public class MainActivity extends AppCompatActivity {

    static final int ADD_HIGH_SCORE_REQUEST=1;
    List<Score> scoreList;
    ListView scoresLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreList = new ArrayList<>();
        scoresLV = findViewById(R.id.highScoresLV);
        displayScores();

    }

    private void displayScores(){
        FileIO flIO = new FileIO(this);
        scoreList = flIO.loadScores();
        Collections.sort(scoreList);
        StableArrayAdapter stArrAdt = new StableArrayAdapter(this,R.layout.recycler_display,scoreList);
        scoresLV.setAdapter(stArrAdt);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==ADD_HIGH_SCORE_REQUEST){
            if(resultCode==RESULT_OK){
                String newName = data.getStringExtra("newName");
                String newScore = data.getStringExtra("newScore");
                String newDate = data.getStringExtra("newDate");

                Score scoreObj = new Score(newName, newScore, newDate);

                scoreList.add(scoreObj);

                FileIO flIO = new FileIO(this);
                flIO.saveScore(scoreList);
                displayScores();
            }
            if(resultCode==RESULT_CANCELED){
                String msg = "No score added.";
                Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem itm){
        System.out.println("Button is Pressed");
        if(itm.getItemId()==R.id.add){
            openAddHighScoresActivity();
            System.out.println("Button is pressed");
            return true;
        }
        return false;
    }
    // open the new activity add high scores
    private void openAddHighScoresActivity(){
        Intent addHighScoreIntent = new Intent(this,AddHighScoresActivity.class);
        startActivityForResult(addHighScoreIntent,ADD_HIGH_SCORE_REQUEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }
}
