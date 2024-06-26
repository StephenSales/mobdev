package com.example.mobdev.event.create;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mobdev.R;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CreateEvent extends AppCompatActivity {

    public static String eventName, eventLoc, eventDesc, eventTheme, eventDate, eventTime;
    public static Timestamp eventTimestamp;
    public static double eventPrice;
    public static  ArrayList<String> eventInclusions;


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