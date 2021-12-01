package com.ncaabdashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Placeholder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    protected int PLACEHOLDER_ID = R.drawable.placeholder;
    // static data source for demo
    List<NewsStory> stories;
    // GUI objects
    private EditText searchBar;
    private View gameView;
    private RecyclerView recyclerView;
    private Button findTickets;
    private Button whereToWatch;

    /**
     * onCreate method called when starting the app
     * @param savedInstanceState - Bundle object created from the last time the app was closed
     *                             and saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up static NewsStory data source for demo
        stories = new ArrayList<NewsStory>();
        stories.add(new NewsStory("NCAA Basketball News", PLACEHOLDER_ID,
                "Something happened in College basketball", "www.google.com"));
        stories.add(new NewsStory("More NCAA Basketball News", PLACEHOLDER_ID,
                "Something else happened in College basketball", "www.google.com"));

        // set up SearchBar
        searchBar = findViewById(R.id.SearchBar);
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // TODO - Have search bar connect with Google API and provide search results
                return false;
            }
        });

        // set up RecyclerView
        recyclerView = findViewById(R.id.NewsStories);
        // set up LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // wire up CustomAdapter
        CustomAdapter adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        // set up gameView onClick method
        gameView = findViewById(R.id.GameCardView);
        gameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "TODO - have GameView redirect to " +
                        "another activity", Toast.LENGTH_SHORT).show();
                // TODO - set up onClick method to redirect to an in-depth game view activity
            }
        });

        // set up findTickets button
        findTickets = findViewById(R.id.FindTickets);
        findTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "TODO - have FindTickets redirect " +
                        "to a place to buy tickets", Toast.LENGTH_SHORT).show();
                // TODO - set up onClick method to redirect to ticketmaster (an activity to set up
                //  ticketmaster search?)
            }
        });

        // set up whereToWatch button
        whereToWatch = findViewById(R.id.WhereToWatch);
        whereToWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "TODO - have WhereToWatch redirect " +
                        "to a map activity", Toast.LENGTH_SHORT).show();
                // TODO - set up onClick method to redirect to map activity through an alert dialog
                //  (ask if they have tickets - if so then locate venue / if not then locate other
                //  place to watch like a restaurant/bar)
            }
        });
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
        TextView newsSynopsis;
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
                newsSynopsis = itemView.findViewById(R.id.NewsSynopsis);
                newsSource = itemView.findViewById(R.id.NewsSource);

                // link up click listener
                itemView.setOnClickListener(this);
            }

            /**
             * Method that updates the view of the RecyclerView with a given News Story
             * @param newsStory - NewsStory object that is being updated in the RecyclerView
             */
            public void updateView(NewsStory newsStory) {
                newsHeadline.setText(newsStory.getTitle());
                newsImage.setImageResource(newsStory.getImageId());
                newsSynopsis.setText(newsStory.getSynopsis());
                newsSource.setText(newsStory.getUrlLink());
            }

            /**
             * Method that redirects the user to the desired news story when they click on a
             * certain news story card item
             * @param v - View object that contains the news story card item
             */
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCLick: ");
                Toast.makeText(MainActivity.this, "TODO - redirect to News Story URL",
                        Toast.LENGTH_SHORT).show();
                // TODO - Finish onCLick method to redirect to the news story using the URL
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
            NewsStory newsStory = stories.get(position); // get the NewsStory object at position
            holder.updateView(newsStory); // update the view with the info from 'newsStory'
            // TODO - Finish onBindViewHolder to bind data from database to RecyclerView using
            //  CustomViewHolder.updateView()
        }

        /**
         * Gets the number of items in the RecyclerView's data source
         * @return - number of items in the RecyclerView's data source
         */
        @Override
        public int getItemCount() {
            // TODO - Implement getItemCount with database
            return stories.size();
        }
    }

    /**
     * Method that inflates main_menu.xml on to the Main Activity
     * @param menu - menu object to inflate main_menu.xml onto
     * @return - super.onCreateOptionsMenu(menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Method to handle when the user clicks on a menu option
     * @param item - the MenuItem object that was clicked on
     * @return - super.onOptionsItemSelected(item)
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        // switch on the itemId to check which MenuItem was selected
        switch(itemId) {
            case R.id.AboutApp:
                // TODO - Implement About Us functionality (new activity that lists stuff about us)
                Toast.makeText(MainActivity.this, "TODO - redirect to an About Us " +
                        "activity", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}