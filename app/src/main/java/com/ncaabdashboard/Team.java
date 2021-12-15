package com.ncaabdashboard;

/**
 * Class to represent a college basketball team for the NCAABDashboard app
 * @author - Joshua Seward
 * @author - Dustin Cassell
 * @version - 1.0.1
 * @since - 14 December 2021
 */
public class Team {
    private String key; // used by API to directly search for team data
    private int apRank;
    private String school; // only using school to save space on team selection screen

    /**
     * DVC for a Team object (used to gather data from SportsDataIO's 'Teams' API)
     * Purpose: Default constructor that presets the data for a team ro misc filler data.
     *          This method might be called for a filler until actual data is found from
     *          an online scraper via the search API.
     */
    public Team() {
        key = "N/A";
        apRank = 0;
        school = "UNKNOWN UNIVERSITY";
    }

    /**
     * Purpose: EVC for a Team object (used to gather data from SportsDataIO's 'Teams' API)
     * @param key - 3-4 letter String key for a team (used by API)
     * @param apRank - AP Ranking of the Team
     * @param school - school name of the Team
     */
    public Team(String key, int apRank, String school) {
        this.key = key;
        this.apRank = apRank;
        this.school = school;
    }

    /**************************** Getter and Setter Methods ****************************/
    /**
     * Purpose: Generic getter for the key field.
     * @return String of 2-4 letters to mark the name of the team for the API such as
     *         'GU'.
     */
    public String getKey() {
        return key;
    }

    /**
     * Purpose: Generic getter for the ApRank field.
     * @return Integer value for the current ranking of 'this' team.
     */
    public int getApRank() {
        return apRank;
    }

    /**
     * Purpose: Generic getter for the school field.
     * @return String of school name for the team, such as 'Gonzaga University'.
     */
    public String getSchool() {
        return school;
    }

    /**
     * Purpose: Generic Setter for the Team's Key field.
     * @param key String of 2-4 letters to mark the name of the team for the API such as
     *           'GU'.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Purpose: Generic Setter for the Team's ApRank field.
     * @param apRank Integer value for the current ranking of 'this' team.
     */
    public void setApRank(int apRank) {
        this.apRank = apRank;
    }

    /**
     * Purpose: Generic Setter for the School field.
     * @param school String of school name for the team, such as 'Gonzaga University'.
     */
    public void setSchool(String school) {
        this.school = school;
    }
    /***********************************************************************************/
}
