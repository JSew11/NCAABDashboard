package com.ncaabdashboard;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    protected String TAG = "DEBUG";
    protected int PLACEHOLDER_ID = R.drawable.placeholder;
    // static data source for demo
    List<NewsStory> stories;
    // GUI objects
    private EditText searchBar;
    private View gameView;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

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
//        stories = new ArrayList<NewsStory>();
//        stories.add(new NewsStory("NCAA Basketball News", PLACEHOLDER_ID,
//                "Something happened in College basketball", "www.google.com"));
//        stories.add(new NewsStory("More NCAA Basketball News", PLACEHOLDER_ID,
//                "Something else happened in College basketball", "www.google.com"));
        loadNewsStory();

        // set up SearchBar
        searchBar = findViewById(R.id.SearchBar);
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(MainActivity.this, "TODO - have the search bar make a " +
                        "Google search using the given input", Toast.LENGTH_SHORT).show();
                // TODO - Have search bar connect with Google API and provide search results
                //  (use this as the text watcher)
                return false;
            }
        });

        // set up RecyclerView
        recyclerView = findViewById(R.id.NewsStories);
        // set up LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // wire up CustomAdapter
        adapter = new CustomAdapter();
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
    }

    /**
     * Private method to make an api call and create a new news story in our demo
     */
    private void loadNewsStory() {
        stories = new ArrayList<NewsStory>();

        String api_key = "01d0758178cb4734bdd33854b2eea5ca";

        // call method to get latest news by university
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://newsapi.org/v2/everything?" +
                "q=Gonzaga+basketball" +
                "&sortBy=popularity" +
                "&from=2021-11-23";

        //String url = "https://www.google.com";

        JsonObjectRequest stringRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, "API SUCCESS", Toast.LENGTH_LONG).show();

                        // TODO: make this part utilize a database backend to update our news stories
                        try {
                            // parse JSON response to find articles
                            JSONArray articles = response.getJSONArray("articles");

                            // currently only show the top 3 results
                            for (int i = 0; i < 3; i++) {
                                JSONObject firstArticle = articles.getJSONObject(i);

                                String title = firstArticle.getString("title");
                                // TODO: get imageId from url of image
                                // int imageId = firstArticle.get("urlToImage");
                                int imageId = PLACEHOLDER_ID;
                                String synopsis = firstArticle.getString("description");
                                String url = firstArticle.getString("url");

                                stories.add(new NewsStory(title, imageId, synopsis, url));
                                adapter.notifyItemChanged(i+1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finally {
                            Log.d(TAG, "" + stories);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "API FAILED", Toast.LENGTH_LONG).show();

                Log.d("ERROR_API", error.toString());
                Log.d("ERROR_API", "" + error.networkResponse.statusCode);
                Log.d("ERROR_API", "" + error.networkResponse.headers);
            }
        })
                // Class JsonObjectRequest
                // override methods of the new class
        {
            // set http headers

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0");
                headers.put("Authorization", api_key);
                return headers;
            }
        };

        queue.add(stringRequest);
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
            // CustomViewHolder.updateView()
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