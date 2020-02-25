package com.hdpolover.ybbadminproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Collections;
import java.util.List;

public class UsersTotalActivity extends AppCompatActivity {

    TextView usersCountTv;
    RecyclerView recyclerView;
    EditText searchUserEt;
    Button searchBtn;

    AdapterUsers adapterUsers;
    List<ModelUsers> modelUsersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_total);

        usersCountTv = findViewById(R.id.usersCountTv);
        recyclerView = findViewById(R.id.usersRecyclerView);
        searchUserEt = findViewById(R.id.searchUserEt);
        searchBtn = findViewById(R.id.searchBtn);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        modelUsersList = new ArrayList<>();
        adapterUsers = new AdapterUsers(getApplicationContext(), modelUsersList);
        recyclerView.setAdapter(adapterUsers);

        loadUsers();
        setUsersTotal(usersCountTv);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String searchQuery = searchUserEt.toString().trim();

                searchUsers(searchQuery);
            }
        });
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

    private void searchUsers(final String searchQuery) {
        //path of all posts
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        //get all data from this ref
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelUsersList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    ModelUsers modelUsers = ds.getValue(ModelUsers.class);

                    if (modelUsers.getName().toLowerCase().contains(searchQuery.toLowerCase())
                    || modelUsers.getEmail().toLowerCase().contains(searchQuery.toLowerCase())
                    || modelUsers.getUsername().toLowerCase().contains(searchQuery.toLowerCase())) {
                        modelUsersList.add(modelUsers);
                    }

                    //adapter
                    adapterUsers = new AdapterUsers(getApplicationContext(), modelUsersList);
                    //set adapter recycler view
                    recyclerView.setAdapter(adapterUsers);
                    adapterUsers.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //in case of error
                Toast.makeText(getApplicationContext(), ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
