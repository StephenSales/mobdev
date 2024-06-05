package com.example.mobdev.event;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.adapter.EventsAdapter;
import com.example.mobdev.adapter.ParticipantAdapter;
import com.example.mobdev.classes.Participant;
import com.example.mobdev.jdbc.ParticipantDAO;

public class ParticipantList extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.participants_layout, null);

        builder.setView(view)
                .setTitle("Participants")
                .setNegativeButton("Close", (dialog, which) -> {
                    // Add negative button action here
                });

        RecyclerView viewParticipants = view.findViewById(R.id.viewParticipants);
        viewParticipants.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        ParticipantDAO.getEventParticipants(Storage.currentlyViewedEvent.getId(), participants -> {
            this.requireActivity().runOnUiThread(() -> {
                viewParticipants.setAdapter(new ParticipantAdapter(participants));
            });
        }, e -> {
            this.requireActivity().runOnUiThread(() -> {
                Toast.makeText(this.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        return builder.create();
    }
}
