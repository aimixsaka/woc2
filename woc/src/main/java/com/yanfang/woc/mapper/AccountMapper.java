package com.yanfang.woc.mapper;

import com.yanfang.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 衍方
 *
 **/

@Mapper
@Repository
public interface AccountMapper
{

    // 获取用户总数
    Integer getAmountOfAccounts();

    // 根据用户名删除用户
    void deleteAccountByName(@Param("userName") String userName);

    // 注册用户
    void registerNewAccount(@Param("userName") String userName, @Param("userPwd") String userPwd, @Param("userEmail") String userEmail);

    // 登录用户
    Integer loginAccount(@Param("userName") String name, @Param("Email") String email, @Param("Password") String password);

    // 登录的核验方法
    String checkPwd(@Param("userName") String userName);

    // 查看指定用户
    Account checkAccount(@Param("username") String username);

    // 查看用户权限
    Integer checkRole(@Param("uname") String uname);
}
