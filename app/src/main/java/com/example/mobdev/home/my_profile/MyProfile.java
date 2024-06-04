package com.example.mobdev.home.my_profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev.Storage;
import com.example.mobdev.R;
import com.example.mobdev.adapter.EventsAdapter;
import com.example.mobdev.classes.User;
import com.example.mobdev.jdbc.EventDAO;
import com.example.mobdev.jdbc.FollowingDAO;
import com.example.mobdev.jdbc.UserDAO;

import java.util.Objects;

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


    RecyclerView viewUserUpcomingEvents = findViewById(R.id.viewMyProfileEvents);
        viewUserUpcomingEvents.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        EventDAO.getEventsByUser(Storage.loggedInUser.getId(), userEventTypeListMap -> {
            runOnUiThread(() -> {

                Objects.requireNonNull(userEventTypeListMap.get(EventDAO.UserEventType.UPCOMING)).addAll(Objects.requireNonNull(userEventTypeListMap.get(EventDAO.UserEventType.PASSED)));
                Storage.allUserEvents = userEventTypeListMap.get(EventDAO.UserEventType.UPCOMING);
                viewUserUpcomingEvents.setAdapter(new EventsAdapter(Storage.allUserEvents, EventsAdapter.Orientation.HORIZONTAL));
            });
        }, e -> {
            runOnUiThread(() -> {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

    }

    private void fetchUserData() {

        User user = Storage.loggedInUser;
        txtUsername.setText(user.getUsername());
        txtFullName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        txtEmail.setText(user.getEmail());

        FollowingDAO.getFollowers(user.getId(), followerIds -> {
            this.runOnUiThread(() -> {
                int followers = followerIds.size();
                txtNumberFollower.setText(String.valueOf(followers));
            });
        }, e -> {
            this.runOnUiThread(() -> {
                Toast.makeText(MyProfile.this, "Error1:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
        FollowingDAO.getFollowings(user.getId(), followingIds -> {
            this.runOnUiThread(() -> {
                int following = followingIds.size();
                txtNumberFollowing.setText(String.valueOf(following));
            });
        }, e -> {
            this.runOnUiThread(() -> {
                Toast.makeText(MyProfile.this, "Error2:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }
}