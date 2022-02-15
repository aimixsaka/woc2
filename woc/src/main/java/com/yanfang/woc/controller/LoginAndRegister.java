package com.yanfang.woc.controller;

import com.yanfang.woc.entity.Account;
import com.yanfang.woc.exceptionhandlers.DefinitionException;
import com.yanfang.woc.exceptionhandlers.Result;
import com.yanfang.woc.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 登录和注册单独做为一个类
 */
@Controller
@RequestMapping("/loginAndRegister")
public class LoginAndRegister
{
    @Autowired
    Services services;
    @Autowired
    HttpServletRequest request;
    // 登录
    @PostMapping("/login")
    public Result loginAccount(Account account)
    {
        if (services.loginAccount(account))
        {
            HttpSession session = request.getSession();
            // 设置session中的account属性
            session.setAttribute("account", account);
            // 设置session过期时间为3分钟
            session.setMaxInactiveInterval(180);
            return new Result(true, null, null, "登录成功");
        }
        else throw new DefinitionException("登录失败，请检查用户名或密码", null);
    }
    // 注册
    @PostMapping("/register")
    public void register(Account account)
    {
        services.registerNewAccount(account);
    }
}
