package com.hdpolover.ybbadminproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hdpolover.ybbadminproject.Adapters.AdapterFeedbacks;
import com.hdpolover.ybbadminproject.Models.ModelFeedback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FeedbacksActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NestedScrollView nestedScrollView;

    AdapterFeedbacks adapterFeedbacks;
    List<ModelFeedback> modelFeedbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacks);

        nestedScrollView = findViewById(R.id.nScrollView);
        recyclerView = findViewById(R.id.feedbacksRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        modelFeedbacks = new ArrayList<>();
        adapterFeedbacks = new AdapterFeedbacks(getApplicationContext(), modelFeedbacks);
        recyclerView.setAdapter(adapterFeedbacks);

        loadFeedbacks();
    }

    private void loadFeedbacks(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Feedbacks");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                modelFeedbacks.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ModelFeedback feedback = snapshot.getValue(ModelFeedback.class);
                    modelFeedbacks.add(feedback);
                }
                adapterFeedbacks.notifyDataSetChanged();
                Log.e("size", modelFeedbacks.size() + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
