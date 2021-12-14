package com.yjxxt.crs.controller;

import com.yjxxt.crs.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @RequestMapping("index")
    public String index() {

        return "index";
    }
}
