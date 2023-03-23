package com.example.ovs.Collections;

import com.example.ovs.Models.PollsModel;

import java.util.ArrayList;
import java.util.List;

public class Collections {
    public static List<PollsModel> getPolls(){
        List<PollsModel> pollsList = new ArrayList<>();
        pollsList.add(new PollsModel("Java", "40%",40, false));
        pollsList.add(new PollsModel("Python", "20%",20, false));
        pollsList.add(new PollsModel("Kotlin", "20%",20, false));
        pollsList.add(new PollsModel("C++", "20%",20, false));
        return pollsList;
    }
}
