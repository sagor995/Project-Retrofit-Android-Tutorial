package me.appsdevsa.reotrofit_project_android.models;

public class User {

    //we don't need to use serialize annotation if we are using the same name as in the api.
    private int id;
    private String email, name, school;

    public User(int id, String email, String name, String school) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }
}
