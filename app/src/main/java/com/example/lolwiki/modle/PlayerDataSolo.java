package com.example.lolwiki.modle;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerDataSolo implements Serializable {
    private String leagueId;
    private String summonerId;
/*    private String queueType;*/
    private String tier;
    private String rank;
    private Integer leaguePoints;
    private Integer wins;
    private Integer losses;
    private ArrayList<MatchHistory> matchHistory;

    public PlayerDataSolo() {
    }

    public PlayerDataSolo(String leagueId, String summonerId, String tier, String rank, Integer leaguePoints, Integer wins, Integer losses, ArrayList<MatchHistory> matchHistory) {
        this.leagueId = leagueId;
        this.summonerId = summonerId;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.matchHistory=matchHistory;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public ArrayList<MatchHistory> getMatchHistory() {
        return matchHistory;
    }

    public void setMatchHistory(ArrayList<MatchHistory> matchHistory) {
        this.matchHistory = matchHistory;
    }

    @Override
    public String toString() {
        return "PlayerDataSolo{" +
                "leagueId='" + leagueId + '\'' +
                ", summonerId='" + summonerId + '\'' +
                ", tier='" + tier + '\'' +
                ", rank='" + rank + '\'' +
                ", leaguePoints=" + leaguePoints +
                ", wins=" + wins +
                ", losses=" + losses +
                ", matchHistory=" + matchHistory +
                '}';
    }
}
