package com.ncaabdashboard;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Activity to select a Team to view more details on
 * @author - Joshua Seward
 * @version - 1.0.0
 * @since - 14 December 2021
 */
public class FindTeamActivity extends AppCompatActivity {
    protected final String TAG = "FindTeamActivityTag";
    private List<Team> teams;
    private SportsDataAPI dataAPI = new SportsDataAPI(this);
    // GUI objects
    private RecyclerView teamOptions;

    /**
     * onCreate method called when starting the FindTeamActivity
     * @param savedInstanceState - savedInstanceState (not used in this scope)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_team);

        // handle the Intent that started the FindTeamsActivity
        Intent intent = getIntent();
        if(intent != null) {
            String key = intent.getStringExtra("success");
            Log.d(TAG, "onCreate: Intent Received ");
        }

        // get SPI data in JSON form
         dataAPI.fetchTeamsData();

        // gonna work with volley to see if it helps
        //******************************************************
//        String url = "https://api.sportsdata.io/v3/cbb/scores/json/teams?key=4634b7a1dc9c4e468aaad9196c5b8083";
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        Log.d("API", "everything worked");
//
//                        try {
//                            JSONArray jsonArray = new JSONArray(response);
//
//                            Log.d("API", "formatted data into JSON");
//                            Log.d("API", jsonArray.toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("API_ERROR", error.toString());
//                Log.d("API_ERROR", "Error code: " + error.networkResponse.statusCode);
//            }
//        });



//        queue.add(stringRequest);


        //*********************************************************

        // initialize the List to hold Team objects
        teams = new ArrayList<>();
        // add fake data to see if cards work
        teams.add(new Team("GNZG", 5, "Gonzaga"));

        // set up teamOptions RecyclerView
        teamOptions = findViewById(R.id.teamOptions);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        teamOptions.setLayoutManager(layoutManager);
        CustomAdapter adapter = new CustomAdapter();
        teamOptions.setAdapter(adapter);

        // include a back arrow in the FindTeamActivity
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    /**
     * Class for a CustomAdapter for the NCAABDashboard App
     * @author - Joshua Seward
     */
    class CustomAdapter extends RecyclerView.Adapter<FindTeamActivity.CustomAdapter.CustomViewHolder>{
        // GUI objects
        TextView teamAPRank;
        TextView teamName;

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
                teamAPRank = itemView.findViewById(R.id.teamAPRank);
                teamName = itemView.findViewById(R.id.teamName);

                // link up click listener
                itemView.setOnClickListener(this);
            }

            /**
             * Method that updates the view of the RecyclerView with a given Team
             * @param team - Team object that is being updated in the RecyclerView
             */
            public void updateView(Team team) {
                teamAPRank.setText("" + team.getApRank());
                teamName.setText(team.getSchool());
            }

            /**
             * Method that redirects the user to TeamDetailsActivity when they click on a
             * certain team card item
             * @param v - View object that contains the team card item
             */
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCLick: ");
                // TODO - Finish onCLick method to redirect to the team details activity using API
                //  (currently uses data stored in the Team class)
                Toast.makeText(FindTeamActivity.this, "TODO - redirect to Team" +
                        "DetailsActivity", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * Method that inflates the layout of each view in the RecyclerView to be the team
         * card item
         * @param parent - parent ViewGroup
         * @param viewType - type of View
         * @return - CustomViewHolder object with the inflated layout
         */
        @NonNull
        @Override
        public FindTeamActivity.CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // inflate the custom news_story_card_item layout
            View view = LayoutInflater.from(FindTeamActivity.this)
                    .inflate(R.layout.team_card_item, parent, false);
            return new FindTeamActivity.CustomAdapter.CustomViewHolder(view);
        }

        /**
         * Binds each individual team to the RecyclerView
         * @param holder - CustomViewHolder to hold the team
         * @param position - position of the team in the RecyclerView
         */
        @Override
        public void onBindViewHolder(@NonNull FindTeamActivity.CustomAdapter.CustomViewHolder holder, int position) {
            Team team = teams.get(position); // get the Team object at position
            holder.updateView(team); // update the view with the info from 'team'
        }

        /**
         * Gets the number of items in the RecyclerView's data source
         * @return - number of items in the RecyclerView's data source
         */
        @Override
        public int getItemCount() {
            return teams.size();
        }
    }

    /**
     * Method to give functionality to back arrow (no other menu options should be in
     * this Activity)
     * @param item - MenuItem that was pressed
     * @return - boolean value telling whether the MenuItem press was handled
     */
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}