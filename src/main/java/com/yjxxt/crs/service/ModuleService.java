package com.yjxxt.crs.service;

import com.yjxxt.crs.base.BaseService;
import com.yjxxt.crs.mapper.ModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ModuleService extends BaseService<Module,Integer> {


    @Resource
    private ModuleMapper moduleMapper;

}
