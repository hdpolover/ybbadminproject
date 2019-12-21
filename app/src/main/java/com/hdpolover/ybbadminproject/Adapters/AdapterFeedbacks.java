package com.hdpolover.ybbadminproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hdpolover.ybbadminproject.Models.ModelFeedback;
import com.hdpolover.ybbadminproject.R;
import com.hdpolover.ybbadminproject.TimeAgo;
import com.hdpolover.ybbadminproject.TimeConverter;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.List;

public class AdapterFeedbacks extends RecyclerView.Adapter<AdapterFeedbacks.MyHolder> {

    Context context;
    List<ModelFeedback> modelFeedbacks;

    TimeConverter tc;
    TimeAgo timeAgo;

    public AdapterFeedbacks(Context context, List<ModelFeedback> modelFeedbacks) {
        this.context = context;
        this.modelFeedbacks = modelFeedbacks;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_feedback, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        tc = new TimeConverter();

        ModelFeedback mf = modelFeedbacks.get(position);

        String fEmail = mf.getEmail();
        String fContent = mf.getFeedback();
        String fTime = mf.getTimestamp();

        holder.fEmailTv.setText(fEmail);
        holder.fContentTv.setText("\"" + fContent + "\"");
        //holder.fTimeTv.setText(tc.convertTime(fTime));
        timeAgo = new TimeAgo();
        holder.fTimeTv.setText(timeAgo.getTimeAgo(Long.parseLong(fTime)));

        PrettyTime prettyTime = new PrettyTime();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelFeedbacks.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView fEmailTv, fContentTv, fTimeTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            fEmailTv = itemView.findViewById(R.id.fEmailTv);
            fContentTv = itemView.findViewById(R.id.fContentTv);
            fTimeTv = itemView.findViewById(R.id.fTimeTv);
        }
    }

}
