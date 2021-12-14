package com.yjxxt.crs.controller;

import com.yjxxt.crs.base.BaseController;
import com.yjxxt.crs.base.ResultInfo;
import com.yjxxt.crs.query.CarQuery;
import com.yjxxt.crs.service.CarService;
import com.yjxxt.crs.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("car")
public class CarController extends BaseController {

    @Autowired(required = false)
    private CarService carService;

    /**
     * 进入营销机会页面
     * @return
     */
    @RequestMapping("rent_car")
    public String index() {

        return "car/rent_car";
    }

    @RequestMapping("scan_car")
    public String scan() {

        return "car/scan_car";
    }

    @RequestMapping("management_car")
    public String management() {

        return "car/management_car";
    }


    /**
     * 选择车辆删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteSaleChance(Integer[] ids) {
        // 删除车辆的数据
        carService.deleteBatch(ids);
        return success("车辆数据删除成功！");
    }


    /***
     *
     * 多条件分页查询车辆
     * @param query
     *  @return
     *
     */

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> querySaleChanceByParams (CarQuery query,HttpServletRequest request) {

        System.out.println("---------------------------------------------"+query);
        // 获取当前登录用户的ID
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);

        return carService.querySaleChanceByParams(query,userId);
    }



}



