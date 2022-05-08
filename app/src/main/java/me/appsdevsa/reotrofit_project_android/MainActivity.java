package me.appsdevsa.reotrofit_project_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email, password, name, school;
    String emailText, passwordText, nameText, schoolText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        school = findViewById(R.id.school);

        findViewById(R.id.signUp).setOnClickListener(this);
        findViewById(R.id.logIn).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logIn:

                break;
            case R.id.signUp:
                userSignUp();
                break;
        }
    }

    private void userSignUp() {
        emailText = email.getText().toString().trim();
        passwordText = password.getText().toString().trim();
        nameText = name.getText().toString().trim();
        schoolText = school.getText().toString().trim();

        if(emailText.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            email.setError("Enter a valid email address");
            email.requestFocus();
            return;
        }

        if(passwordText.isEmpty()){
            password.setError("Password required");
            password.requestFocus();
            return;
        }

        if(passwordText.length() < 6){
            password.setError("Password length should be atleast 6 chars long.");
            password.requestFocus();
            return;
        }

        if(nameText.isEmpty()){
            name.setError("Name required");
            name.requestFocus();
            return;
        }

        if(schoolText.isEmpty()){
            school.setError("School required");
            school.requestFocus();
            return;
        }

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(emailText, passwordText, nameText, schoolText);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String s = response.body().string();
                    Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}