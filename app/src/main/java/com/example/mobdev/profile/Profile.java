package com.example.mobdev.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev.MainActivity;
import com.example.mobdev.Storage;
import com.example.mobdev.adapter.EventsAdapter;
import com.example.mobdev.classes.User;
import com.example.mobdev.home.my_profile.MyProfile;
import com.example.mobdev.jdbc.EventDAO;
import com.example.mobdev.jdbc.FollowingDAO;
import com.example.mobdev.jdbc.UserDAO;
import com.google.android.material.imageview.ShapeableImageView;

import com.example.mobdev.R;

import java.util.Objects;

public class Profile extends AppCompatActivity {

    boolean isFollowing;
    TextView txtUsername;
    TextView txtFullName;
    TextView txtEmail;
    TextView txtNumberFollower;
    TextView txtNumberFollowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        char y;
        isFollowing = false;
        Button btnFollow = findViewById(R.id.btnFollow);

        txtUsername = findViewById(R.id.visitUsername);
        txtEmail = findViewById(R.id.visitEmail);
        txtFullName = findViewById(R.id.visitFullName);

        txtNumberFollower = findViewById(R.id.txtFollower);
        txtNumberFollowing = findViewById(R.id.txtFollowing);

        fetchUserData();

        if (isFollowing) {
            btnFollow.setText("Following");
        } else btnFollow.setText("Follow");

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFollowing) {
                    FollowingDAO.addFollowing(Storage.loggedInUser.getId(), Storage.currentlyViewedUser.getId(), () -> {
                                runOnUiThread(() -> {
                                    isFollowing = true;
                                    btnFollow.setText("Following");
                                });
                            },
                            e -> {
                                runOnUiThread(() -> {
                                    Toast.makeText(Profile.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                            }
                    );
                } else {
                    FollowingDAO.removeFollowing(Storage.loggedInUser.getId(), Storage.currentlyViewedUser.getId(), () -> {
                                runOnUiThread(() -> {
                                    isFollowing = false;
                                    btnFollow.setText("Follow");
                                });
                            },
                            e -> {
                                runOnUiThread(() -> {
                                    Toast.makeText(Profile.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                            }
                    );
                }
            }
        });


        RecyclerView viewUserUpcomingEvents = findViewById(R.id.viewProfileEvents);
        viewUserUpcomingEvents.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        EventDAO.getOrganizedEvents(Storage.currentlyViewedUser.getId(), events -> {
            runOnUiThread(() -> {
                viewUserUpcomingEvents.setAdapter(new EventsAdapter(events, EventsAdapter.Orientation.HORIZONTAL));
            });
        }, e -> {
            runOnUiThread(() -> {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }



    private void fetchUserData() {

        User user = Storage.currentlyViewedUser;
        txtUsername.setText(user.getUsername());
        txtFullName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        txtEmail.setText(user.getEmail());

        FollowingDAO.getFollowings(user.getId(), followingIds -> {
            runOnUiThread(() -> {
                for (int i = 0; i < followingIds.size(); i++) {
                    if (Storage.currentlyViewedEvent.getId() == followingIds.get(i)) {
                        isFollowing = true;
                    }
                }
            });
        }, e -> {
            runOnUiThread(() -> {
                Toast.makeText(Profile.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        FollowingDAO.getFollowers(user.getId(), followerIds -> {
            runOnUiThread(() -> {
                int followers = followerIds.size();
                txtNumberFollower.setText(String.valueOf(followers));
            });
        }, e -> {
            runOnUiThread(() -> {
                Toast.makeText(Profile.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
        FollowingDAO.getFollowings(Storage.currentlyViewedEvent.getId(), followingIds -> {
            runOnUiThread(() -> {
                int following = followingIds.size();
                txtNumberFollowing.setText(String.valueOf(following));
            });
        }, e -> {
            runOnUiThread(() -> {
                Toast.makeText(Profile.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

    }
}