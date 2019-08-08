 package com.raiayy.towerofhanoi;


public class Movement {

    private int record;
    private int towerOrigin;
    private int towerdestination;

    public Movement(int record, int towerOrigin, int towerDestination) {
        this.record = record;
        this.towerOrigin = towerOrigin;
        this.towerdestination = towerDestination;
    }

    public int getrecord() {
        return record;
    }

    public void setrecord(int record) {
        this.record = record;
    }

    public int gettowerdestination() {
        return towerdestination;
    }

    public void settowerdestination(int towerdestination) {
        this.towerdestination = towerdestination;
    }

    public int getTowerOrigin() {
        return towerOrigin;
    }

    public void setTowerOrigin(int towerOrigin) {
        this.towerOrigin = towerOrigin;
    }
}