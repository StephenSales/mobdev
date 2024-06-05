package com.example.mobdev.event;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.adapter.CommentAdapter;
import com.example.mobdev.adapter.ParticipantAdapter;
import com.example.mobdev.classes.Rating;
import com.example.mobdev.jdbc.ParticipantDAO;
import com.example.mobdev.jdbc.RatingDAO;

public class OpenComments extends DialogFragment {

    TextView txtMessage;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.comments_layout, null);
        builder.setView(view)
                .setTitle("Comments")
                .setNegativeButton("Close", (dialog, which) -> {
                    // Add negative button action here
                });


        RecyclerView viewComments = view.findViewById(R.id.viewComments);
        viewComments.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        RatingDAO.getAllRatings(Storage.currentlyViewedEvent.getId(), ratings -> {
            this.requireActivity().runOnUiThread(() -> {
                viewComments.setAdapter(new CommentAdapter(ratings));
            });
        }, e -> {
            this.requireActivity().runOnUiThread(() -> {
                Toast.makeText(this.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });


        txtMessage = view.findViewById(R.id.commentContent);
        Button btnPost = view.findViewById(R.id.postComment);
        btnPost.setOnClickListener(v -> {
            RatingDAO.addRating(Storage.loggedInUser.getId(), Storage.currentlyViewedEvent.getId(), 5, txtMessage.getText().toString(), () -> {

            }, e -> {
                this.requireActivity().runOnUiThread(() -> {
                    Toast.makeText(this.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            });
        });

        return builder.create();
    }
}
