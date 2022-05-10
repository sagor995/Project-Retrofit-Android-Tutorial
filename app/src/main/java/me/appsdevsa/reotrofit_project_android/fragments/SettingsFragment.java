package me.appsdevsa.reotrofit_project_android.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.appsdevsa.reotrofit_project_android.R;
import me.appsdevsa.reotrofit_project_android.activities.LoginActivity;
import me.appsdevsa.reotrofit_project_android.activities.ProfileActivity;
import me.appsdevsa.reotrofit_project_android.api.RetrofitClient;
import me.appsdevsa.reotrofit_project_android.models.DefaultResponse;
import me.appsdevsa.reotrofit_project_android.models.LoginResponse;
import me.appsdevsa.reotrofit_project_android.models.User;
import me.appsdevsa.reotrofit_project_android.storage.SharedPreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment implements View.OnClickListener {


    EditText email, cpassword, npassword, name, school;
    String emailText, nameText, schoolText, cpassText, npassText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        email = view.findViewById(R.id.editTextEmail);
        name = view.findViewById(R.id.editTextName);
        school = view.findViewById(R.id.editTextSchool);

        cpassword = view.findViewById(R.id.editTextCurrentPassword);
        npassword = view.findViewById(R.id.editTextNewPassword);

        view.findViewById(R.id.updateProfile).setOnClickListener(this);
        view.findViewById(R.id.changePassword).setOnClickListener(this);
        view.findViewById(R.id.logOut).setOnClickListener(this);
        view.findViewById(R.id.deleteProfile).setOnClickListener(this);
    }

    private void updateProfile() {
        emailText = email.getText().toString().trim();
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

        User user = SharedPreferenceManager.getInstance(getActivity()).getUser();


        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().updateUser(
                user.getId(),
                emailText,
                nameText,
                schoolText
        );

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                if(!response.body().isError()){
                    SharedPreferenceManager.getInstance(getActivity()).saveUser(response.body().getUser());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

    private void changePassword() {

        User user = SharedPreferenceManager.getInstance(getActivity()).getUser();

        cpassText = cpassword.getText().toString().trim();
        npassText = npassword.getText().toString().trim();

        if(cpassText.isEmpty()){
            cpassword.setError("Current password required");
            cpassword.requestFocus();
            return;
        }

        if(npassText.isEmpty()){
            npassword.setError("New password required");
            npassword.requestFocus();
            return;
        }

        if(cpassText.length() < 6){
            cpassword.setError("password length should be at least 6 chars long.");
            cpassword.requestFocus();
            return;
        }

        if(npassText.length() < 6){
            npassword.setError("Password length should be at least 6 chars long.");
            npassword.requestFocus();
            return;
        }

        Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().updatePassword(
                cpassText, npassText, user.getEmail()
        );
        
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                Toast.makeText(getActivity(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.updateProfile:
                updateProfile();
                break;
            case R.id.changePassword:
                changePassword();
                break;
            case R.id.logOut:
                logout();
                Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteProfile:
                Toast.makeText(getActivity(), "Profile Deleted", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void logout() {
        SharedPreferenceManager.getInstance(getActivity()).clear();

        Intent i = new Intent(getActivity(), LoginActivity.class);
        //Open a new activity by clearing or closing all the previous.
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }


}
