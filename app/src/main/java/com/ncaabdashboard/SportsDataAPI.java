package com.ncaabdashboard;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Purpose: This class handles all API interactions through the Sports Data IO service.
 * @author - Dustin Cassell
 * @version 1.0.1
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
        TeamsDataParser dataParser = new TeamsDataParser();
        dataParser.execute(url);
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
     * @param teamName String of the specific team key code such as 'GNZG' for Gonzaga University.
     */
    public void fetchScheduleByTeam(String teamName) {
        String url = constructFetchScheduleByTeamsURL(teamName);
        Log.d(TAG, "Fetching Schedule by Team name: " + url);

    }

    /**
     * Purpose: Builds the necessary string to fetch the Game Schedule data from the API.
     * @param teamName String of the specific team key code such as 'GNZG' for Gonzaga University.
     * @return Completed fetch URL with team name and API key.
     */
    private String constructFetchScheduleByTeamsURL(String teamName) {
        return BASE_URL + "/v3/cbb/scores/json/TeamSchedule/2021/" + teamName + "?key=" + API_KEY;
    }

    /**
     * Purpose: Opens a URL request to the API using the created URL String from fetchTeamsData().
     *          The JSON response is then downloaded and stored into a list of Strings of type
     *          Team.
     */
    class TeamsDataParser extends AsyncTask<String, void, List<Team>> {

        /**
         * Purpose: Performs every step of the API query in the background on a separate thread.
         *          Multi threaded operations are natively handled by android and semaphores and
         *          Mutexes are not needed here.
         * @param strings Array of Strings that will only be filled with a single String for the URL.
         * @return JSON objects full of all of the data queried from the API.
         */
        @Override
        protected List<Team> doInBackground(String... strings) {
            String url = strings[0];
            List<Team> teams = new ArrayList<>();
            try {
                URL urlObject = new URL(url);
                HttpsURLConnection urlConnection = (HttpsURLConnection) urlObject.openConnection();
                // download JSON response
                String jsonResults = "";
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                while (data != -1) { // read entire JSON response.
                    jsonResults += (char) data;
                    data = reader.read();
                }
                Log.d(TAG, "Background Teams data Parse results: " + jsonResults);

                // Parse JSON data
                JSONObject jsonObject = new JSONObject(jsonResults);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    /**
     * Purpose: Opens a URL request to the API using the created URL String from fetchScheduleByTeam().
     *          The JSON response is then downloaded and stored into a list of Strings of type
     *          TeamSchedule.
     */
    class ScheduleParser extends AsyncTask<String, void, List<TeamSchedule>> {

        /**
         * Purpose: Performs every step of the API query in the background on a separate thread.
         *          Multi threaded operations are natively handled by android and semaphores and
         *          Mutexes are not needed here.
         * @param strings Array of Strings that will only be filled with a single String for the URL.
         * @return JSON objects full of all of the data queried from the API.
         */
        @Override
        protected List<TeamSchedule> doInBackground(String... strings) {
            //TODO: get this to parse JSON data.
            return null;
        }
    }


}
