package com.yjxxt.crs.service;

import com.yjxxt.crs.base.BaseService;
import com.yjxxt.crs.bean.Permission;
import com.yjxxt.crs.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PermissionService extends BaseService<Permission,Integer> {

    @Resource
    private PermissionMapper permissionMapper;


}
