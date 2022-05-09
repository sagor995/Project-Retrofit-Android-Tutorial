package me.appsdevsa.reotrofit_project_android.storage;

import android.content.Context;
import android.content.SharedPreferences;

import me.appsdevsa.reotrofit_project_android.models.User;

//Creating a singleton instance;
public class SharedPreferenceManager {
    private static final String SHARED_PREF_NAME = "my_shared_prefs1";

    //static instance
    private static SharedPreferenceManager mInstance;
    private Context mContext;

    private SharedPreferenceManager(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized SharedPreferenceManager getInstance(Context mContext){
        if(mInstance == null){
            mInstance  = new SharedPreferenceManager(mContext);
        }

        return mInstance;
    }

    public void saveUser(User user){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", user.getId());
        editor.putString("email", user.getEmail());
        editor.putString("name", user.getName());
        editor.putString("school", user.getSchool());

        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id",-1) != -1;
    }

    public User getUser(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        User user = new User(
          sharedPreferences.getInt("id",-1),
          sharedPreferences.getString("email",null),
          sharedPreferences.getString("name",null),
          sharedPreferences.getString("school", null)
        );

        return user;
    }

    public void clear(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
