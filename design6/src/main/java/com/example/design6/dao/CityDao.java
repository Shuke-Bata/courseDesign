package com.example.design6.dao;

import com.example.design6.pojo.City;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title CityDao
 * @description
 * @date 2019/12/18 0:32
 * @modified by:
 */
@Repository
public interface CityDao {
    public List<City> getAllCityList();
    public City[] getAllCityArray();
}
