package com.example.mobdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev.auth.sign_up.SignUp;
import com.example.mobdev.home.Home;
import com.example.mobdev.jdbc.UserDAO;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;

    public enum StateEnv {
        PRODUCTION, DEVELOPMENT
    }

    public static final StateEnv STATE_ENV = StateEnv.DEVELOPMENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editEmail);
        editTextPassword = findViewById(R.id.editPassword);

        TextView txtSignup = findViewById(R.id.txtSignup);
        Button btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (STATE_ENV.equals(StateEnv.DEVELOPMENT)) {
                    email = "john@example.com";
                    password = "password123";
                }

                UserDAO.authenticateUser(email, password, user -> {
                    Storage.loggedInUser = user;
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }, exception -> {
                    Looper.prepare();
                    System.out.println(exception.getMessage());
                    Toast.makeText(MainActivity.this, "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}
