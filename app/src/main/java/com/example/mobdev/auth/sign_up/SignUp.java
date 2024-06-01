package com.example.mobdev.auth.sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev.R;
import com.example.mobdev.jdbc.UserDAO;

import java.sql.SQLException;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText inputName = findViewById(R.id.inputName);
        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputPass = findViewById(R.id.inputPass);
        EditText confirmPass = findViewById(R.id.confirmPass);
        ImageView btnBack = findViewById(R.id.btnBack);
        String username = String.valueOf(inputName.getText());
        String email = String.valueOf(inputEmail.getText());
        String pass = String.valueOf(inputPass.getText());
        String pass2 = String.valueOf(confirmPass.getText());
        TextView txtSignin = findViewById(R.id.txtSignin);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
                if (pass.equals(pass2)) {
                    try {
                        UserDAO.createUser(username, pass, email);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getBaseContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}