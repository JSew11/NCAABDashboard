package com.ncaabdashboard;

/**
 * Purpose: This is for the parsing of JSON data from the API related to a specific teams
 *          game schedule.
 * @author - Dustin Cassell
 * @version 1.0.0
 * @since - 14 December 2021
 */
public class TeamSchedule {

    private int season;
    private String date;
    private int homeTeamID;
    private int awayTeamID;
    private int homeTeamScore;
    private int awayTeamScore;

    /**
     * Purpose: Default Constructor that fills each field with dummy data rather than from an actual
     *          JSON parse.
     */
    public TeamSchedule() {
        season = 2021;
        date = "2021-00-00T00:00:00";
        homeTeamID = 100;
        awayTeamID = 100;
        homeTeamScore = 0;
        awayTeamScore = 0;
    }

    /**
     * Purpose: Custom Constructor that fills every field with data collected from the JSON parse.
     * @param season Integer of the season desired from the API.
     * @param date String that holds a date and time of the scheduled game -- "2021-00-00T00:00:00".
     * @param homeTeamID Integer 3 digits long that represents the specific TeamID.
     * @param awayTeamID Integer 3 digits long that represents the specific TeamID.
     * @param homeTeamScore Integer for the current score of the home team.
     * @param awayTeamScore Integer for the current score of the away team.
     */
    public TeamSchedule(int season, String date, int homeTeamID, int awayTeamID, int homeTeamScore, int awayTeamScore) {
        this.season = season;
        this.date = date;
        this.homeTeamID = homeTeamID;
        this.awayTeamID = awayTeamID;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }


    /* ************************* GETTERS AND SETTERS ************************* */

    /**
     * Purpose: Generic Getter for the season field.
     * @return Integer value of the season year such as "2021".
     */
    public int getSeason() {
        return season;
    }

    /**
     * Purpose: Generic Setter for the season field.
     * @param season Integer value of the season year such as "2021".
     */
    public void setSeason(int season) {
        this.season = season;
    }

    /**
     * Purpose: Generic Getter for the date field.
     * @return String of the date and time data: "2021-00-00T00:00:00"
     */
    public String getDate() {
        return date;
    }

    /**
     * Purpose: Generic Setter for the date field.
     * @param date String of the date and time data: "2021-00-00T00:00:00"
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Purpose: Generic Getter for the homeTeamId.
     * @return Three digit Integer value of the unique ID value for each team.
     */
    public int getHomeTeamID() {
        return homeTeamID;
    }

    /**
     * Purpose: Generic Setter for the homeTeamID field.
     * @param homeTeamID Three digit Integer value of the unique ID value for each team.
     */
    public void setHomeTeamID(int homeTeamID) {
        this.homeTeamID = homeTeamID;
    }

    /**
     * Purpose: Generic Getter for the awayTeamId.
     * @return Three digit Integer value of the unique ID value for each team.
     */
    public int getAwayTeamID() {
        return awayTeamID;
    }

    /**
     * Purpose: Generic Setter for the awayTeamID field.
     * @param awayTeamID Three digit Integer value of the unique ID value for each team.
     */
    public void setAwayTeamID(int awayTeamID) {
        this.awayTeamID = awayTeamID;
    }

    /**
     * Purpose: Generic Getter for the home team Score field.
     * @return Integer of the current Home team score. Populated every 15 Mins.
     */
    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    /**
     * Purpose: Generic Setter for the home team score field.
     * @param homeTeamScore Integer of the current Home team score.
     */
    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    /**
     * Purpose: Generic Getter for the away team Score field.
     * @return Integer of the current Away team score. Populated every 15 Mins.
     */
    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    /**
     * Purpose: Generic Setter for the away team score field.
     * @param awayTeamScore Integer of the current away team score.
     */
    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
}
