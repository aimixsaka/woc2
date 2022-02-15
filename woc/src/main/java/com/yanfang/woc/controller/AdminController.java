package com.yanfang.woc.controller;


import com.yanfang.woc.annotations.Admin;
import com.yanfang.woc.annotations.Login;
import com.yanfang.woc.exceptionhandlers.Result;
import com.yanfang.woc.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员，具有查看总人数的权限
 */
@Controller
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    Services services;

    // 获取当前用户总数
    @GetMapping("/getAmount")
    @Login
    @Admin
    public Result getAmountOfAccounts()
    {
        return new Result(true, null, null, services.getAmountOfAccount());
    }

}
