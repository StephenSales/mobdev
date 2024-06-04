package com.example.mobdev;


import com.example.mobdev.classes.Event;
import com.example.mobdev.classes.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    public static User loggedInUser;
    public static long currentViewedUserId = 0;

    public static List<Event> upcomingEvents = new ArrayList<>();
    public static List<Event> allEvents = new ArrayList<>();


    public static List<Event> upcomingUserEvents = new ArrayList<>();
    public static List<Event> passedUserEvents = new ArrayList<>();

    public static List<Event> bookmarkedEvents = new ArrayList<>();

    public static Event currentlyViewedEvent;




    public static Long getLoggedInUserId() {
        return loggedInUser.getId();
    }

    public static long getCurrentViewedUserId(){
        return currentViewedUserId;
    }

}
