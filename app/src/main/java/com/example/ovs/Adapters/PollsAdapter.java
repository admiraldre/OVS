package com.example.ovs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ovs.Collections.Collections;
import com.example.ovs.Models.PollsModel;
import com.example.ovs.R;

import java.util.List;

public class PollsAdapter extends RecyclerView.Adapter<PollsAdapter.ViewHolder> {

    Context context;
    View view;

    int previousPosition = -1;

    public PollsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       view = LayoutInflater.from(context).inflate(R.layout.rv_polls_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<PollsModel> pollsLIst = Collections.getPolls();
        PollsModel polls = pollsLIst.get(position);
        holder.seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        holder.pollTv.setTag(position);

        if(polls.isPolled()){
            holder.pollTv.setVisibility(View.GONE);
            holder.pollsRv.setVisibility(View.VISIBLE);
            holder.seekBar.setProgress(polls.getProgress());
            holder.pollTitleTv.setText(polls.getTitleTv());
            holder.pollPercentageTv.setText(polls.getPercentageTv());
        }
        else {
            holder.pollTv.setText(polls.getTitleTv());
            holder.pollTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPosition = (Integer)view.getTag();
                    if(previousPosition == -1){
                        previousPosition = currentPosition;
                        holder.pollTv.setVisibility(View.GONE);
                        holder.pollsRv.setVisibility(View.VISIBLE);
                        holder.seekBar.setProgress(polls.getProgress());
                        holder.pollTitleTv.setText(polls.getTitleTv());
                        holder.pollPercentageTv.setText(polls.getPercentageTv());
                    }
                    else {
                        return;
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return Collections.getPolls().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout pollsRv;
        SeekBar seekBar;
        TextView pollTitleTv,pollPercentageTv, pollTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pollsRv = itemView.findViewById(R.id.pollsRv);
            seekBar = itemView.findViewById(R.id.seekBar);
            pollTitleTv = itemView.findViewById(R.id.pollTitleTv);
            pollPercentageTv = itemView.findViewById(R.id.pollPercentageTv);
            pollTv = itemView.findViewById(R.id.pollTv);

        }
    }
}
