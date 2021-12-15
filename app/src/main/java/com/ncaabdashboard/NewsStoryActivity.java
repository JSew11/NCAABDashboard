package com.ncaabdashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Activity to display news stories for the NCAABDashboard app
 * @author - Joshua Seward
 * @version - 1.0.0
 * @since - 14 December 2021
 */
public class NewsStoryActivity extends AppCompatActivity {
    private NewsStory newsStory;
    // data to create the newsStory object
    private String title = "TITLE";
    private int imageId = R.drawable.placeholder;
    private String story = "UNKNOWN";
    private String imageUrl = "NONE";
    // GUI objects
    private TextView newsStoryTitle;
    private ImageView newsStoryImage;
    private TextView newsStoryText;


    /**
     * onCreate method called when the NewsStoryActivity begins
     * @param savedInstanceState - savedInstanceState (not used in this scope)
     */
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
            imageUrl = newsStoryIntent.getStringExtra("imageUrl");
        }
        // create a NewsStory object using the data given by the Intent
        newsStory = new NewsStory(title, imageId, story);

        // link up GUI objects
        newsStoryTitle = findViewById(R.id.newsStoryTitle);
        newsStoryTitle.setText(title);

        newsStoryImage = findViewById(R.id.newsStoryImage);
        new DownloadImageTask(newsStoryImage).execute(imageUrl);

        newsStoryText = findViewById(R.id.newsStoryText);
        newsStoryText.setText(story);

        // include a back arrow in the NewsStoryActivity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Method to give functionality to back arrow (no other menu options should be in this Activity)
     * @param item - MenuItem that was pressed
     * @return - boolean value telling whether the MenuItem press was handled
     */
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}