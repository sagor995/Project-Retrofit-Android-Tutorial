package me.appsdevsa.reotrofit_project_android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.appsdevsa.reotrofit_project_android.R;
import me.appsdevsa.reotrofit_project_android.storage.SharedPreferenceManager;

public class HomeFragment extends Fragment {
    private TextView nameTextView, emailTextView, schoolTextView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameTextView = view.findViewById(R.id.txtViewName);
        emailTextView = view.findViewById(R.id.txtViewEmail);
        schoolTextView = view.findViewById(R.id.txtViewSchool);

        nameTextView.setText(""+ SharedPreferenceManager.getInstance(getActivity()).getUser().getName());
        emailTextView.setText(""+ SharedPreferenceManager.getInstance(getActivity()).getUser().getEmail());
        schoolTextView.setText(""+ SharedPreferenceManager.getInstance(getActivity()).getUser().getSchool());
    }
}
