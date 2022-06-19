package com.example.lolwiki.modle;

public class ChampionMastery {
    private String championName;
    private Integer championPoints;
    private Integer championPointsUntilNextLevel;
    private Integer championLevel;

    public ChampionMastery(String championName, Integer championPoints, Integer championPointsUntilNextLevel, Integer championLevel) {
        this.championName = championName;
        this.championPoints = championPoints;
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
        this.championLevel = championLevel;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public Integer getChampionPoints() {
        return championPoints;
    }

    public void setChampionPoints(Integer championPoints) {
        this.championPoints = championPoints;
    }

    public Integer getChampionPointsUntilNextLevel() {
        return championPointsUntilNextLevel;
    }

    public void setChampionPointsUntilNextLevel(Integer championPointsUntilNextLevel) {
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
    }

    public Integer getChampionLevel() {
        return championLevel;
    }

    public void setChampionLevel(Integer championLevel) {
        this.championLevel = championLevel;
    }

    @Override
    public String toString() {
        return "ChampionMastery{" +
                "championName='" + championName + '\'' +
                ", championPoints=" + championPoints +
                ", championPointsUntilNextLevel=" + championPointsUntilNextLevel +
                ", championLevel=" + championLevel +
                '}';
    }
}
