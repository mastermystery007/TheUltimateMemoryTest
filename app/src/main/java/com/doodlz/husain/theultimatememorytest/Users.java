package com.doodlz.husain.theultimatememorytest;

/**
 * Created by husai on 24-08-2018.
 */

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public  class Users {

    private static  int points;
    private static  String emailID;
    private static  String userName;


    private DatabaseReference userDBR=FirebaseDatabase.getInstance().getReference().child("Users");






    public Users(String firebaseUserId) {}

    public Users(String emailID,String userName){

        this.setEmailID(emailID);

        this.userName=userName;

    }


    public Users(){}

    public static String getEmailID() {
        return emailID;
    }

    public static void setEmailID(String emailID) {
        Users.emailID = emailID;
    }








    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Users.userName = userName;
    }


}
