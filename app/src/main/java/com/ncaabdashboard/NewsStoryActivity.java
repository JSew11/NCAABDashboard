package com.ncaabdashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsStoryActivity extends AppCompatActivity {
    private NewsStory newsStory;
    // data to create the newsStory object
    private String title = "TITLE";
    private int imageId = R.drawable.placeholder;
    private String story = "UNKNOWN";
    // GUI objects
    private TextView newsStoryTitle;
    private ImageView newsStoryImage;
    private TextView newsStoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_story);

        // use the Intent that started this Activity to display a NewsStory object
        Intent newsStoryIntent = getIntent();
        if(newsStoryIntent != null) {
            title = newsStoryIntent.getStringExtra("title");
            imageId = newsStoryIntent.getIntExtra("imageId", R.drawable.placeholder);
            story = newsStoryIntent.getStringExtra("story");
        }
        // create a NewsStory object using the data given by the Intent
        newsStory = new NewsStory(title, imageId, story);

        // link up GUI objects
        newsStoryTitle = findViewById(R.id.newsStoryTitle);
        newsStoryTitle.setText(title);

        newsStoryImage = findViewById(R.id.newsStoryImage);
        newsStoryImage.setImageResource(imageId);

        newsStoryText = findViewById(R.id.newsStoryText);
        newsStoryText.setText(story);

        // include a back arrow in the NewsStoryActivity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}