package com.example.lolwiki.modle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Participants implements Serializable {
    private String puuid;
    private String summonerId;
    private ArrayList<Integer> items;
    private Integer assists;
    private Integer deaths;
    private Integer kills;
    private Integer champLevel;
    private String championName;
    private Integer physicalDamageDealt;
    private Integer magicDamageDealt;
    private Integer goldEarned;
    private Integer goldSpent;
    private String summonerName;
    private Boolean win;

    public Participants() {
    }

    public Participants(String puuid, String summonerId, ArrayList<Integer> items, Integer assists, Integer deaths, Integer kills, Integer champLevel, String championName, Integer physicalDamageDealt, Integer magicDamageDealt, String summonerName, Boolean win,Integer goldEarned,Integer goldSpent) {
        this.puuid = puuid;
        this.summonerId = summonerId;
        this.items = items;
        this.assists = assists;
        this.deaths = deaths;
        this.kills = kills;
        this.champLevel = champLevel;
        this.championName = championName;
        this.physicalDamageDealt = physicalDamageDealt;
        this.magicDamageDealt = magicDamageDealt;
        this.summonerName = summonerName;
        this.win = win;
        this.goldEarned=goldEarned;
        this.goldSpent=goldSpent;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public ArrayList<Integer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Integer> items) {
        this.items = items;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getChampLevel() {
        return champLevel;
    }

    public void setChampLevel(Integer champLevel) {
        this.champLevel = champLevel;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public Integer getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }

    public void setPhysicalDamageDealt(Integer physicalDamageDealt) {
        this.physicalDamageDealt = physicalDamageDealt;
    }

    public Integer getMagicDamageDealt() {
        return magicDamageDealt;
    }

    public void setMagicDamageDealt(Integer magicDamageDealt) {
        this.magicDamageDealt = magicDamageDealt;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public Integer getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(Integer goldEarned) {
        this.goldEarned = goldEarned;
    }

    public Integer getGoldSpent() {
        return goldSpent;
    }

    public void setGoldSpent(Integer goldSpent) {
        this.goldSpent = goldSpent;
    }

    @Override
    public String toString() {
        return "Participants{" +
                "puuid='" + puuid + '\'' +
                ", summonerId='" + summonerId + '\'' +
                ", items=" + items +
                ", assists=" + assists +
                ", deaths=" + deaths +
                ", kills=" + kills +
                ", champLevel=" + champLevel +
                ", championName='" + championName + '\'' +
                ", physicalDamageDealt=" + physicalDamageDealt +
                ", magicDamageDealt=" + magicDamageDealt +
                ", summonerName='" + summonerName + '\'' +
                ", win=" + win +
                '}';
    }
}
