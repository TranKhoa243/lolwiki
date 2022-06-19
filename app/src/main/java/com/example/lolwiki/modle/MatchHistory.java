package com.example.lolwiki.modle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class MatchHistory implements Serializable {
    private String matchId;
    private String puuid;
    private ArrayList<Participants> listParticipants;

    public MatchHistory() {
    }

    public MatchHistory(String matchId, String puuid, ArrayList<Participants> listParticipants) {
        this.matchId = matchId;
        this.puuid = puuid;
        this.listParticipants = listParticipants;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public ArrayList<Participants> getListParticipants() {
        return listParticipants;
    }

    public void setListParticipants(ArrayList<Participants> listParticipants) {
        this.listParticipants = listParticipants;
    }

    @Override
    public String toString() {
        return "MatchHistory{" +
                "matchId='" + matchId + '\'' +
                ", puuid='" + puuid + '\'' +
                ", listParticipants=" + listParticipants +
                '}';
    }
}
