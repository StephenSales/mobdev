package com.example.mobdev.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mobdev.R;
import com.example.mobdev.home.bookmark.Bookmarks;
import com.example.mobdev.home.events.Events;
import com.example.mobdev.home.homepage.Homepage;
import com.example.mobdev.home.my_profile.MyProfile;

public class Home extends AppCompatActivity {

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
                Fragment homepage = new Homepage();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, homepage)
                        .commit();
            }
        });

        btnEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment events = new Events();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, events)
                        .commit();
            }
        });

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment bookmarks = new Bookmarks();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, bookmarks)
                        .commit();
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Home.this, MyProfile.class);
                startActivity(intent1);
            }
        });
    }
}