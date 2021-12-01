package com.ncaabdashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;

/**
 * MainActivity Class for the NCAABDashboard App
 * @author - Alan Poblette
 * @author - Dustin Cassell
 * @author - Joshua Seward
 * @version - 1.0.0
 * Pennant Image - Icons made by Freepik (https://www.freepik.com) from https://www.flaticon.com/
 * Placeholder Image - https://www.royalcontainers.com/100-years-of-knowledge-connections/placeholder/
 */
public class MainActivity extends AppCompatActivity {
    protected String TAG = "MainActivityTag";
    // GUI objects
    private RecyclerView recyclerView;

    /**
     * onCreate method called when starting the app
     * @param savedInstanceState - Bundle object created from the last time the app was closed
     *                             and saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up RecyclerView
        recyclerView = findViewById(R.id.NewsStories);
        // set up LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // wire up CustomAdapter
        CustomAdapter adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        // demo loading a news story from an api
        loadNewsStory();
    }

    /**
     * Private method to make an api call and create a new news story in our demo
     */
    private void loadNewsStory() {
        // call method to get latest news by university
        StringRequest response = NewsAPIFactory.getLatestNewsByUniversity(this, "Gonzaga");
    }

    /**
     * Class for a CustomAdapter for the NCAABDashboard App
     * @author - Joshua Seward
     */
    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{
        // GUI objects
        CardView newsStoryCardView;
        TextView newsHeadline;
        ImageView newsImage;
        TextView newsSource;

        /**
         * Class for a CustomViewHolder for the NCAABDashboard App
         * @author - Joshua Seward
         */
        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            /**
             * Constructor for CustomViewHolder to set up GUI objects
             * @param itemView - View object for the item
             */
            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);

                // link up GUI objects
                newsStoryCardView = itemView.findViewById(R.id.NewsStoryCardView);
                newsHeadline = itemView.findViewById(R.id.NewsHeadline);
                newsImage = itemView.findViewById(R.id.NewsImage);
                newsSource = itemView.findViewById(R.id.NewsSource);

                // link up click listener
                itemView.setOnClickListener(this);
            }

            /**
             * Method that updates the view of the RecyclerView with a given News Story
             */
            public void updateView(/*TODO - add param of new story class*/) {
                // TODO - Write updateView so it properly displays the news story card in the
                //  RecyclerView
            }

            /**
             * Method that redirects the user to the desired news story when they click on a
             * certain news story card item
             * @param v - View object that contains the news story card item
             */
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCLick: ");
                // TODO - Finish onCLick method to redirect to the news story
            }
        }

        /**
         * Method that inflates the layout of each view in the RecyclerView to be the news
         * story card item
         * @param parent - parent ViewGroup
         * @param viewType - type of View
         * @return - CustomViewHolder object with the inflated layout
         */
        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // inflate the custom news_story_card_item layout
            View view = LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.news_story_card_item, parent, false);
            return new CustomViewHolder(view);
        }

        /**
         * Binds each individual news story to the RecyclerView
         * @param holder - CustomViewHolder to hold the news story
         * @param position - position of the news story in the RecyclerView
         */
        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            // TODO - Finish onBindViewHolder to bind data from database to RecyclerView using
            //  CustomViewHolder.updateView()
        }

        /**
         * Gets the number of items in the RecyclerView's data source
         * @return - number of items in the RecyclerView's data source
         */
        @Override
        public int getItemCount() {
            // TODO - Finish getItemCount
            return 0;
        }
    }
}