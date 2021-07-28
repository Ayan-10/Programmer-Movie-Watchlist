package io.realworld.android.programmermoviewatchlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    TextView exception;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Init();

        sharedPreferences = getSharedPreferences(TaskConstants.SHARED_PREF_NAME, MODE_PRIVATE);

        String savedUsername = sharedPreferences.getString(TaskConstants.KEY_USERNAME, null);

        if(savedUsername != null){
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if(username == null || password == null || username.equals("") || password.equals("")){
                exception.setVisibility(View.VISIBLE);
            } else {
                exception.setVisibility(View.GONE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(TaskConstants.KEY_USERNAME, username);
                editor.putString(TaskConstants.KEY_PASSWORD, password);
                editor.apply();

                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Init() {

        usernameEditText = findViewById(R.id.ed_username);
        passwordEditText = findViewById(R.id.ed_password);
        loginButton = findViewById(R.id.btn_login);
        exception =findViewById(R.id.exception);
    }
}