package com.example.design6.pojo;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Route
 * @description 路线类 表示两个城市直接的路线
 * @date 2019/12/18 0:26
 * @modified by:
 */
public class Route {
    /**
     * 路线id
     */
    private int eid;
    /**
     * 起始站id
     */
    private int startCity;
    /**
     * 终点站id
     */
    private int endCity;

    public Route() {
    }

    public Route(int startCity, int endCity) {
        this.startCity = startCity;
        this.endCity = endCity;
    }

    public int getEid() {
        return eid;
    }

    public int getStartCity() {
        return startCity;
    }

    public void setStartCity(int startCity) {
        this.startCity = startCity;
    }

    public int getEndCity() {
        return endCity;
    }

    public void setEndCity(int endCity) {
        this.endCity = endCity;
    }
}
