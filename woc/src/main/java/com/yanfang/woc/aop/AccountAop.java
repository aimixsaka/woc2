package com.yanfang.woc.aop;

import com.yanfang.woc.annotations.Admin;
import com.yanfang.woc.annotations.Login;
import com.yanfang.woc.annotations.SuperAdmin;
import com.yanfang.woc.annotations.User;
import com.yanfang.woc.entity.Account;
import com.yanfang.woc.exceptionhandlers.DefinitionException;
import com.yanfang.woc.service.Services;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * aop类。根目录下所有类中的所有方法都是切入点。
 * 根据注解类型实现不同权限的方法访问
 * SuperAdmin可以访问自己及Admin和User的方法
 * Admin可以访问自己及User的方法
 */
@Component
@Aspect
public class AccountAop
{
    @Autowired
    Services services;
    @Pointcut(value = "execution(* com.yanfang.woc.controller..*.*(..))")
    public void executeService(){}

    @Around("executeService()")
    public Object access(ProceedingJoinPoint joinPoint)
    {
        Account account = getAccount();
        MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
        // 获取请求的方法
        Method method = joinPointObject.getMethod();
        // 判断是否需要登录或是否是User的方法
        if (hasAnnotationOnMethod(method, Login.class) || hasAnnotationOnMethod(method, User.class))
        {
            if (account == null)
            {
                return new DefinitionException("请先登录或注册", 401);
            }
        }
        // 判断是否有Admin及以上权限
        else if (hasAnnotationOnMethod(method, Admin.class))
        {
            if (services.checkRole(account.getUsername()) < 1)
            {
                return new DefinitionException("权限不足", 403);
            }
        }
        // 判断是否有SuperAdmin权限
        else if (hasAnnotationOnMethod(method, SuperAdmin.class))
        {
            if (services.checkRole(account.getUsername()) < 2)
            {
                return new DefinitionException("权限不足", 403);
            }
        }

        Object obj = null;
        try
        {
            obj = joinPoint.proceed();
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }
        return obj;
    }


/**
 * 从session中获取当前用户
 * @return Account
 */
private Account getAccount(){
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
    HttpServletRequest request = servletRequestAttributes.getRequest();
    HttpSession session = request.getSession();
    Account account = (Account) session.getAttribute("account");
    return account;
}

/**
 * 判断某方法上是否含有某注解
 * @param method 方法
 * @param annotationClazz 注解类型
 * @return boolean 是否有注解
 */
private boolean hasAnnotationOnMethod(Method method, Class annotationClazz)
{
    //使用反射获取注解信息
    Annotation a = method.getAnnotation(annotationClazz);
    if (a == null)
    {
        return false;
    }
    return true;
}
}
