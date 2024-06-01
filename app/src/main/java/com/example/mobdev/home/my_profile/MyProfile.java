package com.example.mobdev.home.my_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev.R;
import com.example.mobdev.classes.User;
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
            long userId = getLoggedInUserId(); // Replace this with your actual method
            User user = userDAO.getUser(userId);

            if (user != null) {
                txtUsername.setText(user.getName());
                txtFullName.setText(user.getName());
                txtEmail.setText(user.getEmail());
            } else {
                 Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions (e.g., SQLException)
        }
    }

    private long getLoggedInUserId() {
        // Implement your method to get the logged-in user's ID here
        // This can be based on your authentication mechanism
        // For example, if using Firebase Authentication, you can get the user's ID using Firebase Auth.getCurrentUser().getUid()
        return 1; // Replace this with your actual implementation
    }
}