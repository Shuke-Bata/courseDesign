package com.example.design6.util;

import com.example.design6.pojo.City;
import com.example.design6.pojo.Route;
import com.example.design6.pojo.TrainTime;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Algorithm
 * @description
 * @date 2019/12/27 21:18
 * @modified by:
 */
public class Algorithm {

    /**
     * 邻接矩阵的迪杰斯特拉算法
     *
     * @param matrix 邻接矩阵
     * @param start  开始点下标
     * @param end    结束点下标
     * @return
     */
    public static Stack<Integer> dijkstra(int[][] matrix, int start, int end) {
        // 顶点数目
        int vertexCount = matrix.length;
        // 标记
        boolean[] isVisited = new boolean[vertexCount];
        //当前节点的父节点
        int[] parent = new int[vertexCount];
        // 当前节点和原始节点的距离
        int[] distance = new int[vertexCount];

        //初始化
        for (int i = 0; i < vertexCount; i++) {
            distance[i] = matrix[start][i];
            parent[i] = start;
        }
        isVisited[start] = true;
        distance[start] = 0;
        parent[start] = -1;

        //两次循环
        for (int i = 0; i < vertexCount; i++) {
            int minCost = Matrix.MAX_NUMBER;
            int minIndex = start;
            //找到最近的节点
            for (int j = 0; j < vertexCount; j++) {
                if (!isVisited[j] && distance[j] < minCost) {
                    minCost = distance[j];
                    minIndex = j;
                }
            }

            if (minCost < Matrix.MAX_NUMBER) {
                isVisited[minIndex] = true;
            } else {
                break;
            }

            //更新最近路径和父节点
            for (int j = 0; j < vertexCount; j++) {
                if (!isVisited[j] && (minCost + matrix[minIndex][j] < distance[j])) {
                    distance[j] = minCost + matrix[minIndex][j];
                    parent[j] = minIndex;
                }
            }
        }

        Stack<Integer> routes = new Stack();
        routes.push(end);
        int p = parent[end];
        while (p != -1) {
            routes.push(p);
            p = parent[p];
        }
        return routes;
    }


    /**
     * 返回结果链表
     *
     * @param cities
     * @param routes
     * @param trainTimes
     * @param start      开始点的下标
     * @param end        结束点的下标
     * @return
     */
    public static ArrayList<TrainRouteNode> getRouteInformation(
            List<City> cities, List<Route> routes, List<TrainTime> trainTimes,
            int start, int end, SearchType searchType) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[][] matrix = Matrix.createMatrix(cities, routes, trainTimes, searchType);
        ArrayList<TrainRouteNode> list = new ArrayList<>();

        Stack<Integer> result = Algorithm.dijkstra(matrix, start, end);

        City startCity = new City();

        int size = result.size();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                startCity = cities.get(result.pop());
            }
            if (!result.empty()) {
                City endCity = cities.get(result.pop());
                int routeId = Matrix.getRouteForCity(startCity.getEid(), endCity.getEid(), routes);
                int trainTimeId = findTrainTime(routeId, trainTimes);

                if (trainTimeId >= 0) {
                    TrainTime trainTime = trainTimes.get(trainTimeId);
                    long costTime = trainTime.getAchieveTime().getTime() - trainTime.getDepartureTime().getTime();
                    TrainRouteNode trainRouteNode =
                            new TrainRouteNode(trainTime.getTrainNumber(), startCity.getCityName(),
                                    endCity.getCityName(), dateFormat.format(trainTime.getDepartureTime())
                                    , dateFormat.format(trainTime.getAchieveTime()),
                                    costTime / 3600000 + "时" + (costTime % 3600000) / 60000 + "分",
                                    trainTime.getMoney());
                    list.add(trainRouteNode);
                }
                startCity = endCity;
            }
        }
        return list;
    }

    /**
     * @param routeId
     * @param trainTimes
     * @return
     */
    public static int findTrainTime(int routeId, List<TrainTime> trainTimes) {
        for (int i = 0; i < trainTimes.size(); i++) {
            if (trainTimes.get(i).getRouteId() == routeId) {
                return i;
            }
        }
        return -1;
    }

}
