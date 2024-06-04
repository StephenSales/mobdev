package com.example.mobdev.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev.classes.Participant;

import java.util.List;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantViewHolder> {
    public ParticipantAdapter(List<Participant> participants) {

    }


    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
