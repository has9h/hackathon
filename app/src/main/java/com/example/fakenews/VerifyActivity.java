package com.example.fakenews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VerifyActivity extends AppCompatActivity {
    static EditText verifyNews;
    static Button btnVerify;
    public static String headline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyNews = (EditText) findViewById(R.id.verifyNews);
        btnVerify =  (Button) findViewById(R.id.btnVerify);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.verify:
                intent = new Intent(this, VerifyActivity.class);
                startActivity(intent);
                return true;
            case R.id.news:
                intent = new Intent(this, NewsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void verifyFunction(View view){
        headline = verifyNews.getText().toString(); //gets you the contents of edit text
        Toast toast = Toast.makeText(this, "The non-credibility ranking is "  + fakeHeadlineIndex(headline), Toast.LENGTH_LONG);
        toast.show();
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
}
