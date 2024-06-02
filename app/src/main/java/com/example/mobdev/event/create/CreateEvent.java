package com.example.mobdev.event.create;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mobdev.R;

import java.util.ArrayList;

public class CreateEvent extends AppCompatActivity {

    public String eventName, eventLoc, eventTime, eventDate, eventDesc, eventPrice, eventTheme;
    public ArrayList<String> eventInclusions;

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventTheme() {
        return eventTheme;
    }

    public void setEventTheme(String eventTheme) {
        this.eventTheme = eventTheme;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }

    public ArrayList<String> getEventInclusions() {
        return eventInclusions;
    }

    public void setEventInclusions(ArrayList<String> eventInclusions) {
        this.eventInclusions = eventInclusions;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLoc() {
        return eventLoc;
    }

    public void setEventLoc(String eventLoc) {
        this.eventLoc = eventLoc;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        eventInclusions = new ArrayList<>();
        loadFragment(new CreateEvent1());

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loadFragment(Fragment fragment) {
        // Replace the current fragment with the new one
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fcv1, fragment)
                .commit();
    }
}