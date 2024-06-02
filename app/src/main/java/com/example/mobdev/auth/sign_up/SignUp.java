package com.example.mobdev.auth.sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev.MainActivity;
import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.home.Home;
import com.example.mobdev.jdbc.UserDAO;

import java.sql.SQLException;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText inputUsername = findViewById(R.id.inputUsername);
        EditText inputFname = findViewById(R.id.inputFname);
        EditText inputLname = findViewById(R.id.inputLname);
        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputPass = findViewById(R.id.inputPass);
        EditText confirmPass = findViewById(R.id.confirmPass);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView txtSignin = findViewById(R.id.txtSignin);
        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputPass.getText().toString().equals(confirmPass.getText().toString())) {
                    UserDAO.createUser(inputUsername.getText().toString(), inputPass.getText().toString(), inputFname.getText().toString(), inputLname.getText().toString(), inputEmail.getText().toString(), "", () -> {
                        Looper.prepare();
                        Toast.makeText(getBaseContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }, exception -> {
                        Looper.prepare();
                        Toast.makeText(getBaseContext(), "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                } else {
                    Toast.makeText(getBaseContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}