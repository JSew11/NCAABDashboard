package com.ncaabdashboard;

import android.util.Log;

/**
 * Purpose: This class handles all API interactions through the Sports Data IO service.
 * @author - Dustin Cassell
 * @since - 14 December 2021
 */
public class SportsDataAPI {

    static final String BASE_URL = "https://api.sportsdata.io";
    static final String API_KEY = "4634b7a1dc9c4e468aaad9196c5b8083"; // Currently Dustin's trial API key
    static final String TAG = "SportsDataAPI tag";

    MainActivity mainActivity;
    FindTeamActivity findTeamActivity;
    NewsStoryActivity newsStoryActivity;

    /**
     * Purpose: Custom Constructor for the MainActivity class and view if an API call needs to be made
     *          in main activity, such as scores, current games...
     * @param mainActivity Reference for the mainActivity class that connects to the calling object.
     */
    public SportsDataAPI(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /**
     * Purpose: Custom Constructor for the findTeamActivity class and view. This allows for API calls to
     *          be made for the teams data in the FindTeamActivity.
     * @param findTeamActivity Reference for the FindTeamActivity class.
     */
    public SportsDataAPI(FindTeamActivity findTeamActivity) {
        this.findTeamActivity = findTeamActivity;
    }

    /**
     * Purpose: Custom Constructor for the NewsStoryActivity class and view.
     * @param newsStoryActivity Reference for the NewsStoryActivity class.
     */
    public SportsDataAPI(NewsStoryActivity newsStoryActivity) {
        this.newsStoryActivity = newsStoryActivity;
    }

    /**
     * Purpose: Fetches the entire list of teams data from the API by construction the required URL
     *          with API key.
     */
    public void fetchTeamsData() {
        String url = constructFetchTeamsDataURL();
        Log.d(TAG, "Fetching Teams data URL: " + url);
    }

    /**
     * Purpose: Builds the necessary string to fetch the teams data from the API.
     * @return String fetch URL with API key.
     */
    private String constructFetchTeamsDataURL() {
        return BASE_URL + "/v3/cbb/scores/json/teams" + "?key=" + API_KEY;
    }

    /**
     * Purpose: Fetches the list of scheduled games for a specific team. The team name must be the
     *          key field from the Team class in a 3-4 char string.
     * @param teamName String of the specific team key code such as GNZG for Gonzaga University.
     */
    public void fetchScheduleByTeam(String teamName) {
        String url = constructFetchScheduleByTeamsURL(teamName);
        Log.d(TAG, "Fetching Schedule by Team name: " + url);
    }

    /**
     * Purpose: Builds the necessary string to fetch the Game Schedule data from the API.
     * @param teamName String of the specific team key code such as GNZG for Gonzaga University.
     * @return Completed fetch URL with team name and API key.
     */
    private String constructFetchScheduleByTeamsURL(String teamName) {
        return BASE_URL + "/v3/cbb/scores/json/TeamSchedule/2021/" + teamName + "?key=" + API_KEY;
    }

}
