package com.yanfang.woc.controller;

import com.yanfang.woc.annotations.Login;
import com.yanfang.woc.annotations.SuperAdmin;
import com.yanfang.woc.entity.Account;
import com.yanfang.woc.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 超管，能删除账户和查看所有账户的特定信息
 */
@Controller
@RequestMapping("/superAdmin")
public class SuperAdminController
{
    @Autowired
    Services services;

    /**
     * 超管能根据名称随意删除普通用户和管理员
     * @param username 账户名
     */
    @PostMapping("/deleteAccount")
    @Login
    @SuperAdmin
    public void deleteAccountByName(String username)
    {
        services.deleteAccountByName(username);
    }

    /**
     * 超管能根据名称查看任意用户和管理员的全部信息
     * @param username 账户名
     */
    @GetMapping("/checkAccount")
    @Login
    @SuperAdmin
    public Account checkAccount(String username)
    {
        return services.checkAccount(username);
    }
}
