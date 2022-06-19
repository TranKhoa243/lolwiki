package com.example.lolwiki.modle;

import java.util.ArrayList;

public class Category {
    private String nameCategory;
    private ArrayList<Participants> listParticipants;

    public Category(String nameCategory, ArrayList<Participants> listParticipants) {
        this.nameCategory = nameCategory;
        this.listParticipants = listParticipants;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public ArrayList<Participants> getListParticipants() {
        return listParticipants;
    }

    public void setListParticipants(ArrayList<Participants> listParticipants) {
        this.listParticipants = listParticipants;
    }
}
