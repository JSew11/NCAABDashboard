package com.ncaabdashboard;

/**
 * Class to represent a college basketball team for the NCAABDashboard app
 * @author - Joshua Seward
 * @version - 1.0.0
 * @since - 14 December 2021
 */
public class Team {
    private String key; // used by API to directly search for team data
    private int apRank;
    private String school; // only using school to save space on team selection screen

    /**
     * EVC for a Team object (used to gather data from SportsDataIO's 'Teams' API)
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

    public String getKey() {
        return key;
    }

    public int getApRank() {
        return apRank;
    }

    public String getSchool() {
        return school;
    }
    /***********************************************************************************/

}
