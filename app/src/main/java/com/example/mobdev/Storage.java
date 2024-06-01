package com.example.mobdev;


import com.example.mobdev.classes.User;

public class Storage {

    public static User loggedInUser;



    public static Long getLoggedInUserId() {
        return loggedInUser.getId();
    }

}
