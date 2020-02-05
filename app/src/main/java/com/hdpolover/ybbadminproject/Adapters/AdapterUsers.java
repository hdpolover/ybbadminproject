package com.hdpolover.ybbadminproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hdpolover.ybbadminproject.Models.ModelUsers;
import com.hdpolover.ybbadminproject.R;

import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyHolder>{

    Context context;
    List<ModelUsers> modelUsersList;

    public AdapterUsers(Context context, List<ModelUsers> modelUsersList) {
        this.context = context;
        this.modelUsersList = modelUsersList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_user, parent, false);

        return new AdapterUsers.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        ModelUsers modelUsers = modelUsersList.get(position);

        String username = modelUsers.getName();
        String email = modelUsers.getEmail();

        holder.usernameTv.setText(username);
        holder.userEmailTv.setText(email);
    }

    @Override
    public int getItemCount() {
        return modelUsersList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView usernameTv, userEmailTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            usernameTv = itemView.findViewById(R.id.usernameTv);
            userEmailTv = itemView.findViewById(R.id.userEmailTv);
        }
    }
}
