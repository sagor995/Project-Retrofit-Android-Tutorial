package me.appsdevsa.reotrofit_project_android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import me.appsdevsa.reotrofit_project_android.R;
import me.appsdevsa.reotrofit_project_android.api.RetrofitClient;
import me.appsdevsa.reotrofit_project_android.models.LoginResponse;
import me.appsdevsa.reotrofit_project_android.storage.SharedPreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText emailLogin, passwordLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);


        findViewById(R.id.loginBtn).setOnClickListener(this);
        findViewById(R.id.registerTextview).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerTextview:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.loginBtn:
                userLogin();
                break;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(SharedPreferenceManager.getInstance(this).isLoggedIn()){
            Intent i = new Intent(this, ProfileActivity.class);
            //Open a new activity by clearing or closing all the previous.
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }



    private void userLogin() {
        String emailText = emailLogin.getText().toString();
        String passwordText = passwordLogin.getText().toString();

        if(emailText.isEmpty()){
            emailLogin.setError("Email is required");
            emailLogin.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            emailLogin.setError("Enter a valid email address");
            emailLogin.requestFocus();
            return;
        }

        if(passwordText.isEmpty()){
            passwordLogin.setError("Password required");
            passwordLogin.requestFocus();
            return;
        }

        if(passwordText.length() < 6){
            passwordLogin.setError("Password length should be atleast 6 chars long.");
            passwordLogin.requestFocus();
            return;
        }

        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .loginUser(emailText, passwordText);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(!loginResponse.isError()){
                    //Save user profile
                    SharedPreferenceManager.getInstance(LoginActivity.this)
                            .saveUser(loginResponse.getUser());

                    Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                    //Open a new activity by clearing or closing all the previous.
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                }else {
                    Toast.makeText(LoginActivity.this, ""+loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }



}