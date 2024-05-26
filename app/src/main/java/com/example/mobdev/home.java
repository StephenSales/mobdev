package com.example.mobdev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton btnExplore = findViewById(R.id.btnExplore);
        ImageButton btnEvents = findViewById(R.id.btnEvents);
        ImageButton btnCreate = findViewById(R.id.btnCreate);
        ImageButton btnBookmark = findViewById(R.id.btnBookmark);
        ImageButton btnProfile = findViewById(R.id.btnProfile);

        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment homepage = new homepage();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, homepage)
                        .commit();
            }
        });

        btnEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment events = new events();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, events)
                        .commit();
            }
        });
    }
}