package me.appsdevsa.reotrofit_project_android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import me.appsdevsa.reotrofit_project_android.R;
import me.appsdevsa.reotrofit_project_android.fragments.HomeFragment;
import me.appsdevsa.reotrofit_project_android.fragments.SettingsFragment;
import me.appsdevsa.reotrofit_project_android.fragments.UsersFragment;
import me.appsdevsa.reotrofit_project_android.models.User;
import me.appsdevsa.reotrofit_project_android.storage.SharedPreferenceManager;

public class ProfileActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    RelativeLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        container = findViewById(R.id.container);

        User user = SharedPreferenceManager.getInstance(this).getUser();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(this);

        displayFragment(new HomeFragment());
    }

    private void displayFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.menu_home:
                fragment = new HomeFragment();
                break;
            case R.id.menu_users:
                fragment = new UsersFragment();
                break;
            case R.id.menu_settings:
                fragment = new SettingsFragment();
                break;
        }
        if(fragment!=null){
            displayFragment(fragment);
        }

        return false;
    }
}