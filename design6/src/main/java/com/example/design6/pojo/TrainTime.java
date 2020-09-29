package com.example.design6.pojo;

import java.util.Date;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title TrainTime
 * @description 火车时刻表
 * @date 2019/12/18 1:11
 * @modified by:
 */
public class TrainTime {
    /**
     * 时刻id
     */
    private int eid;
    /**
     * 列车编号
     */
    private String trainNumber;
    /**
     * 路线id
     */
    private int routeId;
    /**
     * 出发时间
     */
    private Date departureTime;
    /**
     * 到达时间
     */
    private Date achieveTime;
    /**
     * 花费金钱
     */
    private int money;


    public TrainTime() {
    }

    public TrainTime(String trainNumber, int routeId, Date departureTime, Date achieveTime, int money) {
        this.trainNumber = trainNumber;
        this.routeId = routeId;
        this.departureTime = departureTime;
        this.achieveTime = achieveTime;
        this.money = money;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getEid() {
        return eid;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getAchieveTime() {
        return achieveTime;
    }

    public void setAchieveTime(Date achieveTime) {
        this.achieveTime = achieveTime;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
