package me.appsdevsa.reotrofit_project_android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import me.appsdevsa.reotrofit_project_android.R;
import me.appsdevsa.reotrofit_project_android.models.User;
import me.appsdevsa.reotrofit_project_android.storage.SharedPreferenceManager;

public class ProfileActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        textView = findViewById(R.id.textView);

        User user = SharedPreferenceManager.getInstance(this).getUser();

        textView.setText("Welcome Back, "+user.getName());

    }
    @Override
    protected void onStart() {
        super.onStart();

        if(!SharedPreferenceManager.getInstance(this).isLoggedIn()){
            Intent i = new Intent(this, MainActivity.class);
            //Open a new activity by clearing or closing all the previous.
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }


}