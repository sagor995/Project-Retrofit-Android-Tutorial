package me.appsdevsa.reotrofit_project_android.api;

import me.appsdevsa.reotrofit_project_android.models.DefaultResponse;
import me.appsdevsa.reotrofit_project_android.models.LoginResponse;
import me.appsdevsa.reotrofit_project_android.models.User;
import me.appsdevsa.reotrofit_project_android.models.UsersResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> loginUser(
        @Field("email") String email,
        @Field("password") String password
    );


    @GET("allusers")
    Call<UsersResponse> getUsers();

}
