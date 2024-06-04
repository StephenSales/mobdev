package com.example.mobdev.home.my_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev.Storage;
import com.example.mobdev.R;
import com.example.mobdev.jdbc.FollowingDAO;
import com.example.mobdev.jdbc.UserDAO;

public class MyProfile extends AppCompatActivity {

    private TextView txtUsername;
    private TextView txtFullName;
    private TextView txtEmail;
    private TextView txtNumberFollower;
    private TextView txtNumberFollowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        txtUsername = findViewById(R.id.txtUsername);
        txtFullName = findViewById(R.id.visitFullName);
        txtEmail = findViewById(R.id.visitEmail);
        txtNumberFollower = findViewById(R.id.txtMyFollower);
        txtNumberFollowing = findViewById(R.id.txtMyFollowing);

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fetchUserData();
    }

    private void fetchUserData() {
            // Assuming you have a method to get the logged-in user's ID
            long userId = Storage.getLoggedInUserId(); // Replace this with your actual method
            UserDAO.getUser(userId, user -> {
                this.runOnUiThread(() -> {
                    txtUsername.setText(user.getUsername());
                    txtFullName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
                    txtEmail.setText(user.getEmail());
                });
            }, throwables -> {
                this.runOnUiThread(() -> {
                    Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
                });
            });

            FollowingDAO.getFollowers(Storage.getLoggedInUserId(), followerIds -> {
                this.runOnUiThread(() -> {
                    int followers = followerIds.size();
                    txtNumberFollower.setText(String.valueOf(followers));
                });
            }, e -> {
                this.runOnUiThread(() -> {
                    Toast.makeText(MyProfile.this, "Error1:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            });
            FollowingDAO.getFollowings(Storage.getLoggedInUserId(),followingIds ->{
                this.runOnUiThread(() -> {
                    int following = followingIds.size();
                    txtNumberFollowing.setText(String.valueOf(following));
                });
            },e -> {
                this.runOnUiThread(() -> {
                    Toast.makeText(MyProfile.this, "Error2:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            });



    }
}