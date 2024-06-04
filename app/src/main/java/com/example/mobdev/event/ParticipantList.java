package com.example.mobdev.event;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mobdev.R;

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
        return builder.create();
    }
}
