package com.yjxxt.crs.aop;

import com.yjxxt.crs.annotations.RequiredPermission;
import com.yjxxt.crs.exceptions.NoLoginException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect
public class PermissionProxy {

    private HttpSession session;

    @Around(value = "@annotation(com.yjxxt.crs.annotations.RequiredPermission)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        if (null==permissions || permissions.size() ==0){
            throw new NoLoginException();
        }
        Object result = null;
        // 定位到方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取方法上面的注解
        RequiredPermission requiredPermission = methodSignature.getMethod().getDeclaredAnnotation(RequiredPermission.class);
        if (!permissions.contains(requiredPermission.code())){
            throw new NoLoginException();
        }
        result = pjp.proceed();
        return result;

    }

}
