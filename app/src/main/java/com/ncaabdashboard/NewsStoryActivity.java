package com.ncaabdashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class NewsStoryActivity extends AppCompatActivity {
    private NewsStory newsStory;
    // data to create the newsStory object
    private String title = "TITLE";
    private int imageId = R.drawable.placeholder;
    private String synopsis = "UNKNOWN";
    private String url = "WWW.SAMPLEURL.COM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_story);

        // use the Intent that started this Activity to display a NewsStory object
        Intent newsStoryIntent = getIntent();
        if(newsStoryIntent != null) {
            title = newsStoryIntent.getStringExtra("title");
            imageId = newsStoryIntent.getIntExtra("imageId", R.drawable.placeholder);
            synopsis = newsStoryIntent.getStringExtra("synopsis");
            url = newsStoryIntent.getStringExtra("url");
        }
        // create a NewsStory object using the data given by the Intent
        newsStory = new NewsStory(title, imageId, synopsis, url);



        // include a back arrow in the NewsStoryActivity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}