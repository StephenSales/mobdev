package com.example.mobdev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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

        TextView txtExplore = findViewById(R.id.txtExplore);
        TextView txtEvents = findViewById(R.id.txtEvents);
        TextView txtBookmark = findViewById(R.id.txtBookmark);

        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnExplore.setImageResource(R.drawable.icons8_explore_24);
                txtExplore.setTextColor(Color.parseColor("#312B78"));
                txtExplore.setTypeface(null, Typeface.BOLD);
                btnEvents.setImageResource(R.drawable.icons8_calendar_24);
                txtEvents.setTextColor(Color.parseColor("#747688"));
                txtEvents.setTypeface(null, Typeface.NORMAL);
                btnBookmark.setImageResource(R.drawable.baseline_bookmark_24_gray);
                txtBookmark.setTextColor(Color.parseColor("#747688"));
                txtBookmark.setTypeface(null, Typeface.NORMAL);

                Fragment homepage = new homepage();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fcv1, homepage)
                        .commit();
            }
        });

        btnEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnExplore.setImageResource(R.drawable.icons8_explore_24__1_);
                txtExplore.setTextColor(Color.parseColor("#747688"));
                txtExplore.setTypeface(null, Typeface.NORMAL);
                btnEvents.setImageResource(R.drawable.icons8_calendar_24__1_);
                txtEvents.setTextColor(Color.parseColor("#312B78"));
                txtEvents.setTypeface(null, Typeface.BOLD);
                btnBookmark.setImageResource(R.drawable.baseline_bookmark_24_gray);
                txtBookmark.setTextColor(Color.parseColor("#747688"));
                txtBookmark.setTypeface(null, Typeface.NORMAL);

                Fragment events = new events();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fcv1, events)
                        .commit();
            }
        });

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnExplore.setImageResource(R.drawable.icons8_explore_24__1_);
                txtExplore.setTextColor(Color.parseColor("#747688"));
                txtExplore.setTypeface(null, Typeface.NORMAL);
                btnEvents.setImageResource(R.drawable.icons8_calendar_24);
                txtEvents.setTextColor(Color.parseColor("#747688"));
                txtEvents.setTypeface(null, Typeface.NORMAL);
                btnBookmark.setImageResource(R.drawable.baseline_bookmark_24_blue);
                txtBookmark.setTextColor(Color.parseColor("#312B78"));
                txtBookmark.setTypeface(null, Typeface.BOLD);
                Fragment bookmarks = new bookmarks();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fcv1, bookmarks)
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