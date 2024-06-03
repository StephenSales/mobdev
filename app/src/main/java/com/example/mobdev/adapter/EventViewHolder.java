package com.example.mobdev.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.classes.Event;
import com.example.mobdev.event.OpenEvent;
import com.example.mobdev.jdbc.BookmarkDAO;

public class EventViewHolder extends RecyclerView.ViewHolder {

    public Event event;
    public TextView txtEventName;
    public TextView txtEventLocation;
    public TextView txtEventDate;
    public ImageButton btnBookmarkEvent;

    public EventViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        this.txtEventName = itemView.findViewById(R.id.txtEventName);
        this.txtEventLocation = itemView.findViewById(R.id.txtEventLocation);
        this.txtEventDate = itemView.findViewById(R.id.txtEventDate);

        final boolean[] bookmarked = {false};
        this.btnBookmarkEvent = itemView.findViewById(R.id.btnBookmarkEvent);
        btnBookmarkEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bookmarked[0]) {
                    bookmarked[0] = false;
                    btnBookmarkEvent.setImageResource(R.drawable.baseline_bookmark_30_gray);
                } else {
                    bookmarked[0] = true;
                    btnBookmarkEvent.setImageResource(R.drawable.baseline_bookmark_24);
                    BookmarkDAO.addBookmark(Storage.loggedInUser.getId(), Storage.currentlyViewedEvent.getId(),  () -> {
                        ((Activity) context).runOnUiThread(() -> {
                            Toast.makeText(itemView.getContext(), "Event Added to Bookmarks", Toast.LENGTH_SHORT).show();
                        });
                    }, exception -> {
                        ((Activity) context).runOnUiThread(() -> {
                            Toast.makeText(itemView.getContext(), "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    });
                }
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.currentlyViewedEvent = event;
                Intent intent = new Intent(itemView.getContext(), OpenEvent.class);
                startActivity(itemView.getContext(), intent, new Bundle());
            }
        });
    }
}
