package com.yjxxt.crs.controller;

import com.yjxxt.crs.base.BaseController;
import com.yjxxt.crs.bean.User;
import com.yjxxt.crs.service.PermissionService;
import com.yjxxt.crs.service.UserService;
import com.yjxxt.crs.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController extends BaseController {

    @Autowired(required = false)
    private UserService userService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping("index")
    public String index() {


        return "index";
    }

    @RequestMapping("welcome")
    public String welcome() {

        return "welcome";
    }


    /**
     * 后端管理主页面
     *
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request) {
        // 通过工具类，从cookie中获取userId
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 调用对应Service层的方法，通过userId主键查询用户对象
        User user = userService.selectByPrimaryKey(userId);
        // 将用户对象设置到request作用域中
        request.setAttribute("user", user);
        return "main";
    }

}

