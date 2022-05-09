package me.appsdevsa.reotrofit_project_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import me.appsdevsa.reotrofit_project_android.R;
import me.appsdevsa.reotrofit_project_android.models.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private Context mContext;
    private List<User> usersList;

    public UsersAdapter(Context mContext, List<User> usersList) {
        this.mContext = mContext;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_users, parent, false);

        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        User user = usersList.get(position);

        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.school.setText(user.getSchool());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{
        TextView name, email, school;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtViewNameR);
            email = itemView.findViewById(R.id.txtViewEmailR);
            school = itemView.findViewById(R.id.txtViewSchoolR);
        }
    }
}
