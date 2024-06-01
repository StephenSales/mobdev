package com.example.mobdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev.auth.sign_up.SignUp;
import com.example.mobdev.home.Home;
import com.example.mobdev.jdbc.UserDAO;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtSignup = findViewById(R.id.txtSignup);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        EditText inputEmail = findViewById(R.id.signinEmail);
        EditText inputPass = findViewById(R.id.signinPass);
        String email = String.valueOf(inputEmail.getText());
        String pass = String.valueOf(inputPass.getText());

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (UserDAO.getUser(email, pass) != null) {
                        Intent intent1 = new Intent(MainActivity.this, Home.class);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(getBaseContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent2);
            }
        });
    }
}