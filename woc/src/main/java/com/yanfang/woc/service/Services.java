package com.yanfang.woc.service;

import com.yanfang.woc.entity.Account;
import com.yanfang.woc.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Services
{
    @Autowired
    private AccountMapper accountMapper;

    // 获取用户总数
    public Integer getAmountOfAccount()
    {
        return accountMapper.getAmountOfAccounts();
    }

    // 根据用户名删除用户
    public void deleteAccountByName(String userName)
    {
        accountMapper.deleteAccountByName(userName);
    }

    // 注册用户
    public void registerNewAccount(Account account)
    {
        accountMapper.registerNewAccount(account.getUsername(), account.getPassword(), account.getEmail());
    }


    // 登录用户
    public boolean loginAccount(Account account3)
    {
        String name = account3.getUsername();
        String pwd = account3.getPassword();
        if (pwd.equals(accountMapper.checkPwd(name)))
        {
            Integer role = accountMapper.loginAccount(name, account3.getEmail(), account3.getPassword());
            if (role >= 0)
            {
                return true;
            }
            else return false;
        }
        else return false;
    }

    // 查看特定用户
    public Account checkAccount(String username)
    {
        return accountMapper.checkAccount(username);
    }

    // 查看用户权限
    public Integer checkRole(String uname)
    {
        return accountMapper.checkRole(uname);
    }

}
