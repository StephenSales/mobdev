package com.example.mobdev.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev.R;
import com.example.mobdev.classes.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class EventsAdapter extends RecyclerView.Adapter<EventViewHolder> {

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(orientation.equals(Orientation.HORIZONTAL) ? R.layout.activity_event_card_vertical : R.layout.activity_event_card_horizontal, parent, false);
        return new EventViewHolder(view, (Activity) parent.getContext());
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
//
//    private List<Event> filteredEvents;
//    @Override
//    public android.widget.Filter getFilter() {
//        return new Filter() {
//            @Override
//            public boolean isLoggable(LogRecord record) {
//                return false;
//            }
//
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                List<Event> filteredList = new ArrayList<>();
//                if (filterPattern.isEmpty()) {
//                    filteredList.addAll(originalEvents);
//                } else {
//                    for (Event event : originalEvents) {
//                        if (event.getName().toLowerCase().contains(filterPattern)) {
//                            filteredList.add(event);
//                        }
//                    }
//                }
//
//                FilterResults results = new FilterResults();
//                results.values = filteredList;
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                filteredEvents.clear();
//                filteredEvents.addAll((List) results.values);
//                notifyDataSetChanged();
//            }
//        };
//    }
}
