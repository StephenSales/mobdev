package com.example.mobdev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
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

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment bookmarks = new bookmarks();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, bookmarks)
                        .commit();
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(home.this, myprofile.class);
                startActivity(intent1);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(home.this, createEvent.class);
                startActivity(intent2);
            }
        });
    }
}