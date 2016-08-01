package com.mygdx.teleclicker.Entities;

/**
 * Created by Senpai on 01/08/2016.
 */
public class PlayerStats {
    private float points;
    private float pointsPerSec;
    private float pointsPerClick;

    private int numberOfClicks;

    private int numberOfPointsPerClickPBuys;
    private int numberOfPointsPerSecBuys;

    public PlayerStats(final Builder builder){
        points = builder.points;
        pointsPerSec = builder.pointsPerSec;
        pointsPerClick = builder.pointsPerClick;
        numberOfClicks = builder.numberOfClicks;
        numberOfPointsPerClickPBuys = builder.numberOfPointsPerClickPBuys;
        numberOfPointsPerSecBuys = builder.numberOfPointsPerSecBuys;
    }

    public static class Builder{
        private float points;
        private float pointsPerSec;
        private float pointsPerClick;

        private int numberOfClicks;

        private int numberOfPointsPerClickPBuys;
        private int numberOfPointsPerSecBuys;

        public Builder points(float points){
            this.points = points;
            return this;
        }

        public Builder pointsPerSec(float pointsPerSec){
            this.pointsPerSec = pointsPerSec;
            return this;
        }

        public Builder pointsPerClick(float pointsPerClick){
            this.pointsPerClick = pointsPerClick;
            return this;
        }

        public Builder numberOfClicks(int numberOfClicks){
            this.numberOfClicks = numberOfClicks;
            return this;
        }

        public Builder numberOfPointsPerClickPBuys(int numberOfPointsPerClickPBuys){
            this.numberOfPointsPerClickPBuys = numberOfPointsPerClickPBuys;
            return this;
        }

        public Builder numberOfPointsPerSecBuys(int numberOfPointsPerSecBuys){
            this.numberOfPointsPerSecBuys = numberOfPointsPerSecBuys;
            return this;
        }

        public PlayerStats build(){
            return new PlayerStats(this);
        }
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "points=" + points +
                ", pointsPerSec=" + pointsPerSec +
                ", pointsPerClick=" + pointsPerClick +
                ", numberOfClicks=" + numberOfClicks +
                ", numberOfPointsPerClickPBuys=" + numberOfPointsPerClickPBuys +
                ", numberOfPointsPerSecBuys=" + numberOfPointsPerSecBuys +
                '}';
    }

    /*
    *
    * Getters and setters
    *
    */

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
