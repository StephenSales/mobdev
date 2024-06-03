package com.example.mobdev.profile;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.mobdev.home.my_profile.MyProfile;
import com.example.mobdev.jdbc.FollowingDAO;
import com.example.mobdev.jdbc.UserDAO;
import com.google.android.material.imageview.ShapeableImageView;

import com.example.mobdev.R;

public class Profile extends AppCompatActivity {

    boolean isFollowing;
    TextView txtUsername;
    TextView txtFullName;
    TextView txtEmail;
    TextView txtNumberFollower;
    TextView txtNumberFollowing;
    int followers, following;

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

        fetchUserData();

        if(isFollowing){
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
                if(!isFollowing){
                    FollowingDAO.addFollowing(Storage.getLoggedInUserId(),Storage.getCurrentViewedUserId(), () -> {
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
                } else{
                    FollowingDAO.removeFollowing(Storage.getLoggedInUserId(),Storage.getCurrentViewedUserId(), () -> {
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
    }


    private void fetchUserData() {
            // Assuming you have a method to get the logged-in user's ID
            UserDAO.getUser(Storage.currentlyViewedEvent.getOrganizerId(), user -> {
                runOnUiThread(() -> {
                    Storage.currentViewedUserId = user.getId();
                    txtUsername.setText(user.getUsername());
                    txtFullName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
                    txtEmail.setText(user.getEmail());
                });
            }, throwables -> {
//                Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
            });

            FollowingDAO.getFollowings(Storage.getLoggedInUserId(),followingIds->{
                runOnUiThread(() -> {
                    for(int i = 0; i < followingIds.size(); i++){
                        if(Storage.getCurrentViewedUserId() == followingIds.get(i)){
                            isFollowing = true;
                        }
                    }
                });
            }, e -> {
                runOnUiThread(() -> {
                    Toast.makeText(Profile.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            });

            FollowingDAO.getFollowers(Storage.getLoggedInUserId(), followerIds -> {
                runOnUiThread(() -> {
                    followers = followerIds.size();
                    txtNumberFollower.setText(followers);
                });
            }, e -> {
                runOnUiThread(() -> {
                    Toast.makeText(Profile.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            });
            FollowingDAO.getFollowings(Storage.getLoggedInUserId(),followingIds ->{
                runOnUiThread(() -> {
                    following = followingIds.size();
                    txtNumberFollowing.setText(following);
                });
            },e -> {
                runOnUiThread(() -> {
                    Toast.makeText(Profile.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            });

    }
}