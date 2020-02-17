package com.hdpolover.ybbadminproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hdpolover.ybbadminproject.Adapters.AdapterFeedbacks;
import com.hdpolover.ybbadminproject.Adapters.AdapterUsers;
import com.hdpolover.ybbadminproject.Models.ModelFeedback;
import com.hdpolover.ybbadminproject.Models.ModelUsers;

import java.util.ArrayList;
import java.util.List;

public class UsersTotalActivity extends AppCompatActivity {

    TextView usersCountTv;
    RecyclerView recyclerView;

    AdapterUsers adapterUsers;
    List<ModelUsers> modelUsersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_total);

        usersCountTv = findViewById(R.id.usersCountTv);
        recyclerView = findViewById(R.id.usersRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        modelUsersList = new ArrayList<>();
        adapterUsers = new AdapterUsers(getApplicationContext(), modelUsersList);
        recyclerView.setAdapter(adapterUsers);

        loadUsers();
        setUsersTotal(usersCountTv);
    }

    private void setUsersTotal(final TextView tv) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tv.setText(dataSnapshot.getChildrenCount() + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadUsers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                modelUsersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ModelUsers modelUsers = snapshot.getValue(ModelUsers.class);
                    modelUsersList.add(modelUsers);
                }
                adapterUsers.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
