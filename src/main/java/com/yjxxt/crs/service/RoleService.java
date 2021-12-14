package com.yjxxt.crs.service;

import com.yjxxt.crs.base.BaseService;
import com.yjxxt.crs.bean.Role;
import com.yjxxt.crs.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService extends BaseService<Role,Integer> {

    @Resource
    private RoleMapper roleMapper;

}
