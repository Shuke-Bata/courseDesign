package com.example.design6.dao;

import com.example.design6.pojo.Route;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title RouteDao
 * @description
 * @date 2019/12/18 0:32
 * @modified by:
 */
@Repository
public interface RouteDao {
    public List<Route> getAllRoute();
    public List<Route> getRoutesByStartCity(@Param("startCityId")int startCityId);
}
