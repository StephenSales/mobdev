package com.example.mobdev.home.my_profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev.MainActivity;
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
    private TextView txtAboutMe;
    private EditText editAboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        txtUsername = findViewById(R.id.txtUsername);
        txtFullName = findViewById(R.id.visitFullName);
        txtEmail = findViewById(R.id.visitEmail);
        txtNumberFollower = findViewById(R.id.txtMyFollower);
        txtNumberFollowing = findViewById(R.id.txtMyFollowing);
        txtAboutMe = findViewById(R.id.txtAboutMe);
        editAboutMe = findViewById(R.id.editAboutMe);

        ImageButton btnFinalize = findViewById(R.id.btnFinalize);
        ImageButton btnBack = findViewById(R.id.btnBack);
        Button btnLogout = findViewById(R.id.btnLogout);
        ImageButton btnEditProfile = findViewById(R.id.btnEditAboutMe);

        txtAboutMe.setText(Storage.loggedInUser.getAboutMe());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDAO.updateUser(Storage.loggedInUser.getId(),txtUsername.getText().toString(), Storage.loggedInUser.getPassword(), Storage.loggedInUser.getFirstName(), Storage.loggedInUser.getLastName(), Storage.loggedInUser.getEmail(), editAboutMe.getText().toString(),() ->{
                    runOnUiThread(()->{
                    });
                },e -> {
                    runOnUiThread(() -> {
                        Toast.makeText(MyProfile.this, "Error1:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                });
                btnFinalize.setVisibility(View.GONE);
                btnEditProfile.setVisibility(View.VISIBLE);
                txtAboutMe.setVisibility(View.VISIBLE);
                editAboutMe.setVisibility(View.GONE);
                txtAboutMe.setText(Storage.loggedInUser.getAboutMe());
            }
        });
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDAO.getUser(Storage.loggedInUser.getId(),user ->{
                    runOnUiThread(()->{
                        editAboutMe.setText(txtAboutMe.getText());
                    });
                },e -> {
                    runOnUiThread(() -> {
                        Toast.makeText(MyProfile.this, "Error1:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                });
                btnFinalize.setVisibility(View.VISIBLE);
                btnEditProfile.setVisibility(View.GONE);
                txtAboutMe.setVisibility(View.GONE);
                editAboutMe.setVisibility(View.VISIBLE);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fetchUserData();


        RecyclerView viewUserUpcomingEvents = findViewById(R.id.viewMyProfileEvents);
        viewUserUpcomingEvents.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        viewUserUpcomingEvents.setAdapter(new EventsAdapter(Storage.allOrganizedEvents, EventsAdapter.Orientation.HORIZONTAL));


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