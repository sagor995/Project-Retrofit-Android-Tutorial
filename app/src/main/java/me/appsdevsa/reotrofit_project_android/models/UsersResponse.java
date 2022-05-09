package me.appsdevsa.reotrofit_project_android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {
    private boolean error;
    private List<User> users = null;
    private String message;

    public UsersResponse(boolean error, List<User> users, String message) {
        this.error = error;
        this.users = users;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getMessage() {
        return message;
    }
}
