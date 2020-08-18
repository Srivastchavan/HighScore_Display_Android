
/*Written by Srivastchavan Rengarajan for CS6326.001 Android Assignment Phase 1, starting March 10
Net ID: sxr190067

This is FileIO class for reading and saving the text file.

 */

package com.example.asg5_sxr190067;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// File IO class for handling reading and writing to a text file
public class FileIO {
    Context context;
    private static final String FILE_NAME="highscores.txt";

    //constructor for the FileIO Class
    public FileIO(Context context){
        this.context=context;
    }
    // Reads text file and returns list of scores
    public List<Score> loadScores(){

        List<Score> scoreList = new ArrayList<>();

        FileInputStream inputStream;
        BufferedReader bufferedReader;
        String filePath = context.getFilesDir()+"/"+FILE_NAME;
        final File txtfile = new File(filePath);

        try{
            if(txtfile.exists()){
                inputStream = new FileInputStream(txtfile);
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line = bufferedReader.readLine();
                while (line !=null){
                    String[] lnarr = line.split("\t");
                    Score lnscore = new Score(lnarr[0],lnarr[1],lnarr[2]);
                    scoreList.add(lnscore);
                    line = bufferedReader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    // saves the list of scores to a file.
    public boolean saveScore(List<Score> scoreList){
        FileOutputStream fOut;
        OutputStreamWriter myOutWriter;
        String filePath = context.getFilesDir()+"/"+FILE_NAME;
        File file = new File(filePath);

        if(!file.exists()){
            try{
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{

            fOut = new FileOutputStream(file.getAbsoluteFile(),true);
            new FileOutputStream(filePath).close();
            myOutWriter = new OutputStreamWriter(fOut);
            Collections.sort(scoreList);


            int linenumber = 0;

            for(Score score: scoreList) {
                myOutWriter.write(score.getName().toString() + "\t" + score.getScore().toString() + "\t" + score.getDate().toString() + "\n");

                linenumber++;

                if(linenumber==12) {
                    break;
                }

            }

            myOutWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
