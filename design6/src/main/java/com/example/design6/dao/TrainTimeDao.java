package com.example.design6.dao;

import com.example.design6.pojo.TrainTime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title TrainTimeDao
 * @description
 * @date 2019/12/18 1:25
 * @modified by:
 */
@Repository
public interface TrainTimeDao {
    public List<TrainTime> getAllTrainTime();

    public List<TrainTime> getTrainTimeByRoute(@Param("routeId") int routeId);
}
