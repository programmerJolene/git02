package com.yjxxt.crs;

import com.alibaba.fastjson.JSON;
import com.yjxxt.crs.base.ResultInfo;
import com.yjxxt.crs.exceptions.NoLoginException;
import com.yjxxt.crs.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    /*
     * 方法返回值
     *
     * 视图
     * json
     *
     * 判断：
     * 如果方法上配置了@ResponseBody注解，则表明是返回json
     * */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // 需先判断是否是未登录异常，未登录，直接转跳到登录页面
        if (ex instanceof NoLoginException) {
            // 如果捕获的是未登录异常，则重定向到登录页面
            ModelAndView mv = new ModelAndView("redirect:/index");
            return mv;
        }

        // 设置默认异常处理
        ModelAndView mv = new ModelAndView();
        // 默认跳转error页面
        mv.setViewName("error");
        mv.addObject("code", 400);
        mv.addObject("msg", "系统异常，稍后再试...");

        // 判断是json还是视图
        if (handler instanceof HandlerMethod) {
            // 类型转换
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的@ResponseBody注解
            ResponseBody responseBody = handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);

            // 存在，返回json  不存在，返回视图
            if (responseBody == null) {
                // 返回视图
                if (ex instanceof ParamsException) {
                    ParamsException pe = (ParamsException) ex;
                    mv.addObject("code", pe.getCode());
                    mv.addObject("msg", pe.getMsg());

                }
                return mv;
            } else {
                // 返回json  resultInfo
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统异常，请重试");

                // 捕获自定义异常
                if (ex instanceof ParamsException) {
                    ParamsException pe = (ParamsException) ex;
                    resultInfo.setMsg(pe.getMsg());
                    resultInfo.setCode(pe.getCode());
                }

                // 设置响应内容和编码格式
                response.setContentType("application/json;charset=utf-8");

                // 得到输出流
                PrintWriter out = null;
                try {
                    out = response.getWriter();

                    // 将对象转化为json格式，通过输出流输出，响应给请求的前台
                    out.write(JSON.toJSONString(resultInfo));
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        out.close();
                    }

                }
                return null;
            }

        }


        return mv;
    }
}
