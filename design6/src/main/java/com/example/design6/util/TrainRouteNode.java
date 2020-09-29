package com.example.design6.util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title TrainRouteNode
 * @description
 * @date 2019/12/28 0:20
 * @modified by:
 */
public class TrainRouteNode {
    private final SimpleStringProperty trainNumber;
    private final SimpleStringProperty startCity;
    private final SimpleStringProperty endCity;
    private final SimpleStringProperty startTime;
    private final SimpleStringProperty endTime;
    private final SimpleStringProperty costTime;
    private final SimpleIntegerProperty price;


    public TrainRouteNode(String trainNumber, String startCity,
                          String endCity, String startTime,
                          String endTime, String costTime,
                          int price) {
        this.trainNumber = new SimpleStringProperty(trainNumber);
        this.startCity =  new SimpleStringProperty(startCity);;
        this.endCity =  new SimpleStringProperty(endCity);;
        this.startTime = new SimpleStringProperty(startTime); ;
        this.endTime =  new SimpleStringProperty(endTime);;
        this.costTime = new SimpleStringProperty(costTime);
        this.price = new SimpleIntegerProperty(price);
    }

    public String getTrainNumber() {
        return trainNumber.get();
    }

    public SimpleStringProperty trainNumberProperty() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber.set(trainNumber);
    }

    public String getStartCity() {
        return startCity.get();
    }

    public SimpleStringProperty startCityProperty() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity.set(startCity);
    }

    public String getEndCity() {
        return endCity.get();
    }

    public SimpleStringProperty endCityProperty() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity.set(endCity);
    }

    public String getStartTime() {
        return startTime.get();
    }

    public SimpleStringProperty startTimeProperty() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public String getEndTime() {
        return endTime.get();
    }

    public SimpleStringProperty endTimeProperty() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }

    public String getCostTime() {
        return costTime.get();
    }

    public SimpleStringProperty costTimeProperty() {
        return costTime;
    }

    public void setCostTime(String  costTime) {
        this.costTime.set(costTime);
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }
}
