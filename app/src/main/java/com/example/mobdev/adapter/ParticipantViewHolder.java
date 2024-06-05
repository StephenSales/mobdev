package com.example.mobdev.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.classes.Participant;
import com.example.mobdev.event.OpenEvent;
import com.example.mobdev.jdbc.EventDAO;
import com.example.mobdev.jdbc.UserDAO;
import com.example.mobdev.profile.Profile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ParticipantViewHolder extends RecyclerView.ViewHolder {

    Participant participant;
    public TextView txtParticipantFullName;
    public TextView txtParticipantEmail;
    public TextView txtParticipantJoinedDate;
    public Context context;

    public ParticipantViewHolder(@NonNull View itemView, Activity context) {
        super(itemView);

        this.context = context;
        this.txtParticipantFullName = itemView.findViewById(R.id.txtParticipantFullname);
        this.txtParticipantEmail = itemView.findViewById(R.id.txtParticipantEmail);
        this.txtParticipantJoinedDate = itemView.findViewById(R.id.txtParticipantJoinedAt);


        itemView.setOnClickListener(v -> {
            UserDAO.getUser(participant.getUserId(), user -> {
                ((Activity) context).runOnUiThread(() -> {
                    Storage.currentlyViewedUser = user;
                    Intent intent1 = new Intent(context.getBaseContext(), Profile.class);
                    context.startActivity(intent1);
                });
            }, e -> {
                ((Activity) context).runOnUiThread(() -> {
                    Toast.makeText(itemView.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            });
        });
    }


    @SuppressLint("SetTextI18n")
    public void update() {
        txtParticipantFullName.setText(participant.getFirstname() + " " + participant.getLastname());
        txtParticipantEmail.setText(participant.getEmail());
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        txtParticipantJoinedDate.setText("Joined at: " + s.format(participant.getJoinedAt()));
    }
}
