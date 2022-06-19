package com.example.lolwiki.modle;

import java.io.Serializable;

public class PlayerKey implements Serializable {
    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private Integer profileIconId;
    private String revisionDate;
    private String summonerLevel;
    private String region;
    private  PlayerDataSolo playerDataSolo;

    public PlayerKey(String id, String accountId, String puuid, String name, Integer profileIconId, String revisionDate, String summonerLevel,String region,PlayerDataSolo playerDataSolo) {
        this.id = id;
        this.accountId = accountId;
        this.puuid = puuid;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
        this.region=region;
        this.playerDataSolo = playerDataSolo;
    }

    public PlayerKey() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(String summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public PlayerDataSolo getPlayerDataSolo() {
        return playerDataSolo;
    }

    public void setPlayerDataSolo(PlayerDataSolo playerDataSolo) {
        this.playerDataSolo = playerDataSolo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "PlayerKey{" +
                "id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", puuid='" + puuid + '\'' +
                ", name='" + name + '\'' +
                ", profileIconId=" + profileIconId +
                ", revisionDate='" + revisionDate + '\'' +
                ", summonerLevel='" + summonerLevel + '\'' +
                ", region='" + region + '\'' +
                ", playerDataSolo=" + playerDataSolo +
                '}';
    }
}
