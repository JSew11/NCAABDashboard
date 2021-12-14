package com.ncaabdashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Spinner;

import java.util.List;

/**
 * Activity to select a Team to view more details on
 * @author - Joshua Seward
 * @version - 1.0.0
 * @since - 14 December 2021
 */
public class FindTeamActivity extends AppCompatActivity {
    private List<Team> teams;
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

        // set up GUI objects

    }
}