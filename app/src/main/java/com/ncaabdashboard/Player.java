package com.ncaabdashboard;

/**
 * Class to represent a Player for the NCAABDashboard app
 * @author - Joshua Seward
 * @version -1.0.0
 * @since - 14 December 2021
 */
public class Player {
    private String firstName;
    private String lastName;
    private int number;
    private String position;
    /**
     * EVC for a Player object (used in 'Player Details by Team' API call)
     * @param firstName - player's first name
     * @param lastName - player's last name
     * @param number - player's number
     * @param position - player's position
     */
    public Player(String firstName, String lastName, int number, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.position = position;
    }

    /**************************** Getter and Setter Methods ****************************/
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }
    /***********************************************************************************/
}
