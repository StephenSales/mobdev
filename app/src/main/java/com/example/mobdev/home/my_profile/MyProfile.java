package com.example.mobdev.home.my_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mobdev.Storage;
import com.example.mobdev.R;
import com.example.mobdev.jdbc.UserDAO;

public class MyProfile extends AppCompatActivity {

    private TextView txtUsername;
    private TextView txtFullName;
    private TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        txtUsername = findViewById(R.id.txtUsername);
        txtFullName = findViewById(R.id.txtFullName);
        txtEmail = findViewById(R.id.txtEmail);

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
        // Create UserDAO instance
        UserDAO userDAO = new UserDAO();

        try {
            // Assuming you have a method to get the logged-in user's ID
            long userId = Storage.getLoggedInUserId(); // Replace this with your actual method
            userDAO.getUser(userId, user -> {
                txtUsername.setText(user.getUsername());
                txtFullName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
                txtEmail.setText(user.getEmail());
            }, throwables -> {
//                Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
            });
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions (e.g., SQLException)
        }
    }
}