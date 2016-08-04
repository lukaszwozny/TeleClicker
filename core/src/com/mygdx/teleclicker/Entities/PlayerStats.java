package com.mygdx.teleclicker.Entities;

/**
 * Created by Senpai on 01/08/2016.
 */
public class PlayerStats {
    private int id;
    private float points;
    private float pointsPerSec;
    private float pointsPerClick;

    private int numberOfClicks;

    private int numberOfPointsPerClickPBuys;
    private int numberOfPointsPerSecBuys;

    @Override
    public String toString() {
        return "PlayerStats{" +
                "id=" + id +
                ", points=" + points +
                ", pointsPerSec=" + pointsPerSec +
                ", pointsPerClick=" + pointsPerClick +
                ", numberOfClicks=" + numberOfClicks +
                ", numberOfPointsPerClickPBuys=" + numberOfPointsPerClickPBuys +
                ", numberOfPointsPerSecBuys=" + numberOfPointsPerSecBuys +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public float getPointsPerSec() {
        return pointsPerSec;
    }

    public void setPointsPerSec(float pointsPerSec) {
        this.pointsPerSec = pointsPerSec;
    }

    public float getPointsPerClick() {
        return pointsPerClick;
    }

    public void setPointsPerClick(float pointsPerClick) {
        this.pointsPerClick = pointsPerClick;
    }

    public int getNumberOfClicks() {
        return numberOfClicks;
    }

    public void setNumberOfClicks(int numberOfClicks) {
        this.numberOfClicks = numberOfClicks;
    }

    public int getNumberOfPointsPerClickPBuys() {
        return numberOfPointsPerClickPBuys;
    }

    public void setNumberOfPointsPerClickPBuys(int numberOfPointsPerClickPBuys) {
        this.numberOfPointsPerClickPBuys = numberOfPointsPerClickPBuys;
    }

    public int getNumberOfPointsPerSecBuys() {
        return numberOfPointsPerSecBuys;
    }

    public void setNumberOfPointsPerSecBuys(int numberOfPointsPerSecBuys) {
        this.numberOfPointsPerSecBuys = numberOfPointsPerSecBuys;
    }
}
