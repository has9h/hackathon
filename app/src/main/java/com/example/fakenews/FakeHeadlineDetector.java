package com.example.fakenews;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import static com.example.fakenews.VerifyActivity.verifyNews;

public class FakeHeadlineDetector {
    public static String headline;
    public static double ranking;
    Toast displayRanking;

    public String verifyFunction(Context context){
        headline = verifyNews.getText().toString(); //gets you the contents of edit text
        Toast.makeText(context, "The credibility ranking is "  + fakeHeadlineIndex(headline), Toast.LENGTH_SHORT);

        return headline;
    }

    public static int howManyChars(String headline){
        int charCount = 0;
        for (int i=0; i<headline.length(); i++){
            char ch = headline.charAt(i);
            if (Character.isLetter(ch)){
                charCount++;
            }
        }
        return charCount;
    }

    public static double howCapitalized(String headline, int charCount){
        int capCount = 0;
        for (int i=0; i<headline.length(); i++){
            Character ch = headline.charAt(i);
            if (Character.isLetter(ch) && ch.equals(Character.toUpperCase(ch))){
                capCount++;
            }
        }
        double propCaps = capCount/charCount;
        // if less than 0.3 of chars are capitalized
        // based on fact that avg. word length is 5.1
        // so ~0.2 of chars in headline should be capitalized
        // i.e. if less than 0.2 of chars are capitalized,
        // degree of capitalization likely not a problem,
        // hence return 0
        if (propCaps < 0.3){
            return 0;
        }
        return propCaps;
    }

    public static double howExclamatory(String headline){
        int exclamCount = 0;
        for (int i=0; i<headline.length(); i++){
            Character ch = headline.charAt(i);
            if (ch.equals('!')){
                exclamCount++;
            }
        }
        if (exclamCount == 0){
            return 0;
        }
        if (exclamCount == 1){
            return 0.5;
        }
        return 1;
    }

    public static double phraseCount(String headline){
        //linear regression equationN: 0.006702x  + 9.352
        if(headline.contains("will make you"))
            return 69.4;
        else if(headline.contains("this is why"))
            return 36.82;
        else if(headline.contains("can we guess"))
            return 30.79;
        else if(headline.contains("the reason is"))
            return 20.14;
        else if(headline.contains("are freaking out"))
            return 19.81;
        else if(headline.contains("stunning photos"))
            return 18.90;
        else if(headline.contains("tears of joy"))
            return 18.65;
        else if(headline.contains("is what happens"))
            return 18.31;
        else if(headline.contains("make you cry"))
            return 17.98;
        else if(headline.contains("give you goosebumps"))
            return 17.98;
        else if(headline.contains("talking about it"))
            return 17.83;
        else if(headline.contains("is too cute"))
            return 17.80;
        return 0.0;
    }

    public static double fakeHeadlineIndex(String headline){
        int numChars = howManyChars(headline);
        double propCaps = howCapitalized(headline, numChars);
        double capIndex = propCaps*5;
        double propExclam = howExclamatory(headline);
        double exclamIndex = propExclam*20;
        double phraseIndex = phraseCount(headline);
        return capIndex+exclamIndex+phraseIndex;
    }

    public static void main(String[] args) {
//        String headline = JOptionPane.showInputDialog("Enter the full headline.");
//        System.out.println("The apparent, face-value fakeness of this news headline is "
//                +fakeHeadlineIndex(headline)+" out of 100.0 points");
    }

}