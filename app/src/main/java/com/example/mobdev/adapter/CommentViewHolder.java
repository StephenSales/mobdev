package com.example.mobdev.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.classes.Rating;
import com.example.mobdev.jdbc.UserDAO;
import com.example.mobdev.profile.Profile;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    public Activity context;
    public Rating rating;

    public TextView txtUsername;
    public TextView txtCreatedAt;
    public RecyclerView recyclerViewStars;
    public TextView txtMessage;


    public CommentViewHolder(@NonNull View itemView, Activity context) {
        super(itemView);
        this.context = context;

        this.txtUsername = itemView.findViewById(R.id.txtCommentUsername);
        this.txtCreatedAt = itemView.findViewById(R.id.txtCommentCreatedAt);
        this.recyclerViewStars = itemView.findViewById(R.id.recycleStars);
        this.txtMessage = itemView.findViewById(R.id.txtCommentMessage);

        itemView.setOnClickListener(v -> {
            UserDAO.getUser(rating.getUserId(), user -> {
                ((Activity) context).runOnUiThread(() -> {
                    Storage.currentlyViewedUser = user;
                    Intent intent1 = new Intent(context.getBaseContext(), Profile.class);
                    context.startActivity(intent1);
                });
            }, e -> {
                ((Activity) context).runOnUiThread(() -> {
                    Toast.makeText(itemView.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            });
        });
    }

    public void update() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        txtMessage.setText(rating.getMessage());
        txtCreatedAt.setText(sdf.format(rating.getRatedAt()).toString());

        recyclerViewStars.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewStars.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(context);
        for (int i = 0; i < rating.getRating(); i++) {
            View starView = inflater.inflate(R.layout.star_active, recyclerViewStars, false);
            recyclerViewStars.addView(starView);
        }
        for (int i = rating.getRating(); i < 5; i++) {
            View starView = inflater.inflate(R.layout.star_inactive, recyclerViewStars, false);
            recyclerViewStars.addView(starView);
        }

        UserDAO.getUser(rating.getUserId(), user -> {
            (context).runOnUiThread(() -> {
                txtUsername.setText(user.getUsername());
            });
        }, e -> {
            context.runOnUiThread(() -> {
                Toast.makeText(itemView.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

    }
}
