/*Written by Srivastchavan Rengarajan for CS6326.001 Android Assignment Phase 1, starting March 10
Net ID: sxr190067

This is class to describe the score object.
 */
package com.example.asg5_sxr190067;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
// Class with score object definition and compare score method
public class Score implements Comparable<Score> {
    private String name;
    private String score;
    private String date;

    public Score(String playerName, String score, String date){
        this.name = playerName;
        this.score = score;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public void setPlayerName(String playerName) {
        this.name = playerName;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int compareTo(Score newScore) {

        int score1 = Integer.parseInt(this.getScore());
        int score2 = Integer.parseInt(newScore.getScore());

        if(score1 < score2){
            return 1;
        }
        else if(score1 == score2){
            Date dt1 = null;
            Date dt2 = null;

            try{
                dt1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(this.getDate());
                dt2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(newScore.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int val = dt2.compareTo(dt1);
            if(val!=0){
                return   val;
            }
            else{
                String pnm1 = this.getName();
                String pnm2 = newScore.getName();
                int rVal = pnm1.compareToIgnoreCase(pnm2);
                if(rVal < 0){
                    return -1;
                }
                else if(rVal > 0){
                    return 1;
                }
                else{
                    return 0;
                }
            }

        }
        else{
            return -1;
        }
    }
}
