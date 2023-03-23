package com.example.ovs.Models;

import com.example.ovs.activities.AdminDashboard;

public class PollsModel {

    String titleTv;
    String percentageTv;
    int progress;
    boolean isPolled;

    public PollsModel(String titleTv, String percentageTv, int progress, boolean isPolled) {
        this.titleTv = titleTv;
        this.percentageTv = percentageTv;
        this.progress = progress;
        this.isPolled = isPolled;
    }

    public PollsModel(AdminDashboard adminDashboard) {
    }

    public String getTitleTv() {
        return titleTv;
    }

    public void setTitleTv(String titleTv) {
        this.titleTv = titleTv;
    }

    public String getPercentageTv() {
        return percentageTv;
    }

    public void setPercentageTv(String percentageTv) {
        this.percentageTv = percentageTv;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isPolled() {
        return isPolled;
    }

    public void setPolled(boolean polled) {
        isPolled = polled;
    }

    @Override
    public String toString() {
        return "PollsModel{" +
                "titleTv='" + titleTv + '\'' +
                ", percentageTv='" + percentageTv + '\'' +
                ", progress=" + progress +
                ", isPolled=" + isPolled +
                '}';
    }
}
