package com.example.fakenews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity {
    static ImageView imageView1,imageView2,imageView3;
    static TextView header1,header2,header3,publishedon1,publishedon2,publishedon3,content1,content2,content3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        imageView1= findViewById(R.id.imageView1);
        imageView2= findViewById(R.id.imageView2);
        imageView3= findViewById(R.id.imageView2);
        header1=findViewById(R.id.source1);
        header2=findViewById(R.id.source2);
        header3=findViewById(R.id.source3);
        publishedon1=findViewById(R.id.publishedon1);
        publishedon2=findViewById(R.id.publishedon2);
        publishedon3=findViewById(R.id.publishedon3);
        content1=findViewById(R.id.content1);
        content2=findViewById(R.id.content2);
        content3=findViewById(R.id.content3);

        NewsPage getData= new NewsPage();
        getData.execute("https://newsapi.org/v2/" + "top-headlines?country=us&apiKey=106a04e2a6f949c28f2b273ad212b795");
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
}
