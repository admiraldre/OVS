package com.example.ovs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ovs.R;
import com.example.ovs.activities.ViewPollActivity;

public class PollPostsAdapter extends RecyclerView.Adapter<PollPostsAdapter.ViewHolder> {

    Context context;
    View view;

    public PollPostsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.activity_admin_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView pollsRv;
        PollsAdapter pollsAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pollsRv = itemView.findViewById(R.id.pollsRv);
        }

        public void setData(int position){
            pollsRv.setLayoutManager(new LinearLayoutManager(context));
            pollsAdapter = new PollsAdapter(context);
            pollsRv.setAdapter(pollsAdapter);
        }

    }
}
