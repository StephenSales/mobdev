package com.example.mobdev.event;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.jdbc.ParticipantDAO;

import java.sql.SQLException;

public class EventConfirm extends AppCompatActivity {


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_confirm);

        TextView eventName = findViewById(R.id.confirmEventName);
        TextView eventDate = findViewById(R.id.confirmEventDate);
        TextView eventLoc = findViewById(R.id.confirmEventLoc);
        TextView eventPrice = findViewById(R.id.confirmEventPrice);
        TextView eventTotal = findViewById(R.id.confirmEventTotal);
        ImageButton btnBack = findViewById(R.id.btnBack3);

        eventName.setText(Storage.currentlyViewedEvent.getName());
        eventDate.setText(Storage.currentlyViewedEvent.getEventDate().toString());
        eventLoc.setText(Storage.currentlyViewedEvent.getLocation());
        eventPrice.setText(String.format("P%.2f per ticket", Storage.currentlyViewedEvent.getPrice()));
        eventTotal.setText(String.format("P %.2f", Storage.currentlyViewedEvent.getPrice()));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button submitPay = findViewById(R.id.btnSubmitPay);
        submitPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParticipantDAO.addParticipant(Storage.loggedInUser.getId(), Storage.currentlyViewedEvent.getId(),
                        () -> {
                            runOnUiThread(() -> {
                                Toast.makeText(getBaseContext(), "Order Confirmed", Toast.LENGTH_SHORT).show();
                            });
                        }, e -> {
                            runOnUiThread(() -> {
                                Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                        });
                finish();
            }
        });
    }
}