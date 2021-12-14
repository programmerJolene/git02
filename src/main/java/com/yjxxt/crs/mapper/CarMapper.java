package com.yjxxt.crs.mapper;

import com.yjxxt.crs.base.BaseMapper;
import com.yjxxt.crs.bean.Car;
import com.yjxxt.crs.query.CarQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarMapper extends BaseMapper<Car,Integer> {


    List<Car> selectByParamsAndId(CarQuery query, Integer userId);

}