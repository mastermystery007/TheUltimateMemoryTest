package com.doodlz.husain.theultimatememorytest;

/**
 * Created by husai on 26-08-2018.
 */

public class Leader {

    private String userName;
    private String emailId;
    private String points;

    public Leader() {
    }

    public Leader(String userName, String emailId, String points) {
        this.userName = userName;
        this.emailId = emailId;
        this.points = points;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
