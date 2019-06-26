package com.kardum.chuckjokes.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kardum.chuckjokes.MainActivity;
import com.kardum.chuckjokes.R;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_READ_CONTACTS = 0;

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private EditText mPasswordView, mUserName;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // declaring obejct of EditText control
        mUserName = (EditText) findViewById(R.id.txtUserName);
        mPasswordView = (EditText) findViewById(R.id.txtPassword);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName = mUserName.getText().toString();
                String Pwd = mPasswordView.getText().toString();
                if(UserName.equalsIgnoreCase("user") && Pwd.equals("user")){
                    Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(MainIntent);
                    Toast toast = Toast.makeText(LoginActivity.this,"Login successful.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else
                {
                    Toast.makeText(LoginActivity.this,"Sorry,User Name or Password is incorrect.", Toast.LENGTH_LONG).show();
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }
}