package com.yjxxt.crs.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crs.base.BaseService;
import com.yjxxt.crs.bean.Car;
import com.yjxxt.crs.mapper.CarMapper;
import com.yjxxt.crs.query.CarQuery;
import com.yjxxt.crs.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class CarService extends BaseService<Car,Integer> {

    @Resource
    private CarMapper carMapper;

    /**
     * 车辆数据删除
     * @param ids
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSaleChance (Integer[] ids) {
        // 判断要删除的id是否为空
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择需要删除的数据！");
        // 删除数据
        AssertUtil.isTrue(carMapper.deleteBatch(ids) < 0, "车辆数据删除失败！");
    }

    /**
     * 多条件分页查询营销机会 (BaseService 中有对应的方法)
     * @param query
     * @return
     */
    public Map<String, Object> querySaleChanceByParams (CarQuery query,Integer userId) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(), query.getLimit());
        PageInfo<Car> pageInfo = new PageInfo<>(carMapper.selectByParamsAndId(query,userId));
        map.put("code",0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }



}
