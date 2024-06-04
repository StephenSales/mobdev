package com.example.mobdev.event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.classes.User;
import com.example.mobdev.jdbc.ParticipantDAO;
import com.example.mobdev.jdbc.UserDAO;
import com.example.mobdev.profile.Profile;
import com.google.android.material.imageview.ShapeableImageView;

import org.w3c.dom.Text;

import java.sql.SQLException;

public class OpenEvent extends AppCompatActivity {

    private User organizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_event);

        TextView eventName = findViewById(R.id.openEventName);
        TextView eventTimestamp = findViewById(R.id.openEventTimestamp);
        TextView eventLoc = findViewById(R.id.openEventLoc);
        TextView eventOrganizer = findViewById(R.id.openEventOrganizer);
        TextView eventDesc = findViewById(R.id.openEventDesc);

        eventName.setText(Storage.currentlyViewedEvent.getName());
        eventTimestamp.setText(Storage.currentlyViewedEvent.getEventDate().toString());
        eventLoc.setText(Storage.currentlyViewedEvent.getLocation());

        UserDAO.getUser(Storage.currentlyViewedEvent.getOrganizerId(), user -> {
            this.runOnUiThread(() -> {
                organizer = user;
                eventOrganizer.setText(user.getUsername());
            });
        }, error -> {
            this.runOnUiThread(() -> {
                Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
            });
        });
        eventDesc.setText(Storage.currentlyViewedEvent.getDescription());


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
                Storage.currentlyViewedUser = organizer;
                Intent intent1 = new Intent(OpenEvent.this, Profile.class);
                startActivity(intent1);
            }
        });
        eventOrganizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OpenEvent.this, Profile.class);
                startActivity(intent1);
            }
        });

        TextView openParticipants = findViewById(R.id.openParticipants);
        openParticipants.setOnClickListener(v -> {
            ParticipantList dialogFragment = new ParticipantList();
            dialogFragment.show(getSupportFragmentManager(), "Participant List");
        });

        TextView participants = findViewById(R.id.participants);
        try {
            int numParticipants = ParticipantDAO.getEventParticipants(Storage.currentlyViewedEvent.getId());
            participants.setText(numParticipants + " Participants");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}