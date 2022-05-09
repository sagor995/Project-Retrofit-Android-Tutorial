package me.appsdevsa.reotrofit_project_android.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.appsdevsa.reotrofit_project_android.R;
import me.appsdevsa.reotrofit_project_android.adapters.UsersAdapter;
import me.appsdevsa.reotrofit_project_android.api.RetrofitClient;
import me.appsdevsa.reotrofit_project_android.models.User;
import me.appsdevsa.reotrofit_project_android.models.UsersResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

    RecyclerView recyclerView;
    private UsersAdapter adapter;
    private List<User> userList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.users_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*Call<UsersResponse> call = RetrofitClient.getInstance().getApi().getUsers();
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(), "1. "+response.body(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "2. "+response.errorBody(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

        Call<UsersResponse> call = RetrofitClient.getInstance().getApi().getUsers();
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                userList = response.body().getUsers();
                adapter = new UsersAdapter(getActivity(), userList);
                recyclerView.setAdapter(adapter);
                //Toast.makeText(getActivity(), response.code()+" ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });


    }
}
