package com.example.ovs.activities;

public class Polls {
    private String pollName;

    public Polls(){
    }
    public Polls(String pollName) {
        this.pollName = pollName;
    }

    public String getPollName() {
        return pollName;
    }
   @Override public String toString(){
        return pollName;
    }
}
