package com.ncaabdashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity to view Team Details gathered by the SportsDataIO API
 * @author - Joshua Seward
 * @version - 1.0.0
 * @since - 14 December 2021
 */
public class TeamDetailsActivity extends AppCompatActivity {
    private String TAG = "TeamDetailsActivityTag";
    private String key = "";
    // team data to display (should be passed through Intent)
    private int apRank = 0;
    private String school = "";
    private String mascot = "";
    private String logoPhotoURL;
    private int wins = -1;
    private int losses = -1;
    private String stateName = "";
    private String conference = "";
    private String arenaName = "";
    private List<Player> roster;
    // GUI objects
    private TextView teamRankSchool;
    private ImageView teamLogo;
    private TextView teamInfo;
    private RecyclerView rosterView;

    /**
     * onCreate method called when starting the TeamDetailsActivity
     * @param savedInstanceState - savedInstanceState (not used in this scope)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        // get the key from the Intent that started the Activity
        Intent teamDetailsIntent = getIntent();
        if(teamDetailsIntent != null) {
            key = teamDetailsIntent.getStringExtra("key");
            school = teamDetailsIntent.getStringExtra("school");
            apRank = teamDetailsIntent.getIntExtra("apRank", -1);
        }

        // initialize the roster ArrayList
        roster = new ArrayList<>();
        // sample player data to see if card item works
//        roster.add(new Player("New", "Player", 00, "G"));

        // call API to get Roster data for display

        // link GUI objects
        teamRankSchool = findViewById(R.id.teamRankSchool);
        String rankSchool = "#" + apRank + " " + school;
        teamRankSchool.setText(rankSchool);

        teamLogo = findViewById(R.id.teamLogo);

        teamInfo = findViewById(R.id.teamInfo);
        String advancedTeamInfo = "Wins: " + wins + "\nLosses: " + losses + "\nState: " +
                stateName + "\nConference: " + conference + "\nArena: " + arenaName;
        teamInfo.setText(advancedTeamInfo);


        rosterView = findViewById(R.id.rosterView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rosterView.setLayoutManager(layoutManager);
        CustomAdapter adapter = new CustomAdapter();
        rosterView.setAdapter(adapter);

        // include a back arrow in the TeamDetailsActivity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * Class for a CustomAdapter for the TeamDetailsActivity
     * @author - Joshua Seward
     */
    class CustomAdapter extends RecyclerView.Adapter<TeamDetailsActivity.CustomAdapter.CustomViewHolder>{
        // GUI objects
        TextView playerNumber;
        TextView playerName;
        TextView playerPosition;

        /**
         * Class for a CustomViewHolder for the TeamDetailsActivity
         * @author - Joshua Seward
         */
        class CustomViewHolder extends RecyclerView.ViewHolder {

            /**
             * Constructor for CustomViewHolder to set up GUI objects
             * @param itemView - View object for the item
             */
            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);

                // link up GUI objects
                playerNumber = itemView.findViewById(R.id.playerNumber);
                playerName = itemView.findViewById(R.id.playerName);
                playerPosition = itemView.findViewById(R.id.playerPosition);
            }

            /**
             * Method that updates the view of the RecyclerView with a given Player
             * @param player - Player object that is being updated in the RecyclerView
             */
            public void updateView(Player player) {
                playerNumber.setText("#" + player.getNumber());
                String playerFullName = player.getFirstName() + " " + player.getLastName();
                playerName.setText(playerFullName);
                playerPosition.setText(player.getPosition());
            }
        }

        /**
         * Method that inflates the layout of each view in the RecyclerView to be the roster
         * player card item
         * @param parent - parent ViewGroup
         * @param viewType - type of View
         * @return - CustomViewHolder object with the inflated layout
         */
        @NonNull
        @Override
        public TeamDetailsActivity.CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // inflate the custom roster_player_card_item layout
            View view = LayoutInflater.from(TeamDetailsActivity.this)
                    .inflate(R.layout.roster_player_card_item, parent, false);
            return new TeamDetailsActivity.CustomAdapter.CustomViewHolder(view);
        }

        /**
         * Binds each individual team to the RecyclerView
         * @param holder - CustomViewHolder to hold the team
         * @param position - position of the team in the RecyclerView
         */
        @Override
        public void onBindViewHolder(@NonNull TeamDetailsActivity.CustomAdapter.CustomViewHolder holder, int position) {
            Player player = roster.get(position); // get the Player object at position
            holder.updateView(player); // update the view with the info from 'player'
        }

        /**
         * Gets the number of items in the RecyclerView's data source
         * @return - number of items in the RecyclerView's data source
         */
        @Override
        public int getItemCount() {
            return roster.size();
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