package com.example.mobdev;


import com.example.mobdev.classes.Event;
import com.example.mobdev.classes.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    public static User loggedInUser;

    public static List<Event> upcomingEvents = new ArrayList<>();
    public static List<Event> allEvents = new ArrayList<>();


    public static List<Event> allUserEvents = new ArrayList<>();
    public static List<Event> upcomingUserEvents = new ArrayList<>();
    public static List<Event> passedUserEvents = new ArrayList<>();
    public static List<Event> bookmarkedEvents = new ArrayList<>();


    public static Event currentlyViewedEvent;
    public static User currentlyViewedUser;


    public static List<Event> allOrganizedEvents = new ArrayList<>();
}
