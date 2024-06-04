package com.example.mobdev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.classes.Event;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventViewHolder> {


    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(orientation.equals(Orientation.HORIZONTAL) ? R.layout.activity_event_card_vertical : R.layout.activity_event_card_horizontal, parent, false);
        return new EventViewHolder(view, context);
    }

    private  Orientation orientation = Orientation.VERTICAL;
    private List<Event> events = new ArrayList<>();

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

    public EventsAdapter(@NonNull List<Event> events, Orientation orientation) {
        this.events = events;
        this.orientation = orientation;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event item = events.get(position);

//        holder.txtEventDate.setImageResource(.getImageResId());

        holder.event = item;
        holder.update();
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

}
