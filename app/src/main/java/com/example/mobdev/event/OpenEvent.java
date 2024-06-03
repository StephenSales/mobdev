package com.example.mobdev.event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mobdev.R;
import com.example.mobdev.profile.Profile;
import com.google.android.material.imageview.ShapeableImageView;

public class OpenEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_event);


        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnJoin = findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OpenEvent.this, EventConfirm.class);
                startActivity(intent1);
            }
        });

        ShapeableImageView organizerDP = findViewById(R.id.organizerDP);
        organizerDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OpenEvent.this, Profile.class);
                startActivity(intent1);
            }
        });

        TextView txtOrganizer = findViewById(R.id.txtOrganizer);
        txtOrganizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OpenEvent.this, Profile.class);
                startActivity(intent1);
            }
        });
    }
}