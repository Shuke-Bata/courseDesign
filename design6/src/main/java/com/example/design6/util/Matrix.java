package com.example.design6.util;

import com.example.design6.pojo.City;
import com.example.design6.pojo.Route;
import com.example.design6.pojo.TrainTime;

import java.util.List;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Matrix
 * @description
 * @date 2019/12/27 20:17
 * @modified by:
 */
public class Matrix {
    /**
     * 定义的最大数
     */
    public static int MAX_NUMBER = 20000;

    private static int[][] init(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = MAX_NUMBER;
            }
        }
        return matrix;
    }

    public static int[][] createMatrix(List<City> cities, List<Route> routes,
                                       List<TrainTime> trainTimes, SearchType searchType) {
        int[][] matrix = init(cities.size());
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int routeId = getRouteForCity(cities.get(i).getEid(), cities.get(j).getEid(), routes);
                if (routeId >= 0) {
                    if (searchType == SearchType.LESS_MONEY) {
                        matrix[i][j] = getMoneyForRoute(routeId, trainTimes);
                    } else if (searchType == SearchType.FAST) {
                        matrix[i][j] = getTimeForRoute(routeId, trainTimes);
                    } else if (searchType == SearchType.LESS_CHANGE) {
                        matrix[i][j] = getChangeForRoute(routeId, trainTimes);
                    }
                }
            }
        }
        return matrix;
    }

    public static int getRouteForCity(int startCityId, int endCityId, List<Route> routes) {
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).getStartCity() == startCityId && routes.get(i).getEndCity() == endCityId) {
                return routes.get(i).getEid();
            }
        }
        return -1;
    }

    public static int getTimeForRoute(int routeId, List<TrainTime> trainTimes) {
        for (int i = 0; i < trainTimes.size(); i++) {
            if (trainTimes.get(i).getRouteId() == routeId) {
                long time = trainTimes.get(i).getAchieveTime().getTime() -
                        trainTimes.get(i).getDepartureTime().getTime();
                return (int) (time / 60000);
            }
        }
        return MAX_NUMBER;
    }

    public static int getMoneyForRoute(int routeId, List<TrainTime> trainTimes) {
        for (int i = 0; i < trainTimes.size(); i++) {
            if (trainTimes.get(i).getRouteId() == routeId) {
                return trainTimes.get(i).getMoney();
            }
        }
        return MAX_NUMBER;
    }

    public static int getChangeForRoute(int routeId, List<TrainTime> trainTimes) {
        for (int i = 0; i < trainTimes.size(); i++) {
            if (trainTimes.get(i).getRouteId() == routeId) {
                return 1;
            }
        }
        return MAX_NUMBER;
    }
}
