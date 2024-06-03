package com.example.mobdev.event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.classes.Event;
import com.example.mobdev.home.homepage.EventViewHolder;

public class UserPassedEventsAdapter extends RecyclerView.Adapter<EventViewHolder> {

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event_card_vertical, parent, false);
        return new EventViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event item = Storage.passedUserEvents.get(position);

//        holder.txtEventDate.setImageResource(.getImageResId());

        holder.txtEventName.setText(item.getName());
        holder.txtEventLocation.setText(item.getLocation());
        holder.txtEventDate.setText(item.getCreatedAt().toString());
    }

    @Override
    public int getItemCount() {
        return Storage.upcomingEvents.size();
    }

}
