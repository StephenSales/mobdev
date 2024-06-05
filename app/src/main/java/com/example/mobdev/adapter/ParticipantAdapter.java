package com.example.mobdev.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev.R;
import com.example.mobdev.classes.Event;
import com.example.mobdev.classes.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantViewHolder> {
    private List<Participant> participants = new ArrayList<>();


    public ParticipantAdapter(List<Participant> participants) {
        this.participants = participants;
    }


    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_participant_card, parent, false);
        return new ParticipantViewHolder(view, (Activity) parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder holder, int position) {
        holder.participant = participants.get(position);
        holder.update();
    }


    @Override
    public int getItemCount() {
        return participants.size();
    }
}
