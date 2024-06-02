package com.example.mobdev.home.homepage;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev.R;
import com.example.mobdev.event.OpenEvent;

public class EventViewHolder extends RecyclerView.ViewHolder {

    public TextView txtEventName;
    public TextView txtEventLocation;
    public TextView txtEventDate;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        this.txtEventName = itemView.findViewById(R.id.txtEventName);
        this.txtEventLocation = itemView.findViewById(R.id.txtEventLocation);
        this.txtEventDate = itemView.findViewById(R.id.txtEventDate);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), OpenEvent.class);
                startActivity(itemView.getContext(), intent, new Bundle());
            }
        });
    }
}
