package com.example.mobdev;


import com.example.mobdev.classes.Event;
import com.example.mobdev.classes.User;

import java.util.List;

public class Storage {

    public static User loggedInUser;


    public static List<Event> upcomingEvents;
    public static List<Event> allEvents;


    public static List<Event> upcomingUserEvents;
    public static List<Event> passedUserEvents;




    public static Long getLoggedInUserId() {
        return loggedInUser.getId();
    }

}
