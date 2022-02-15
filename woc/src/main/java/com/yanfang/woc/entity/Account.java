package com.yanfang.woc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account
{
    private Integer id;
    private Integer role;
    private String username;
    private String password;
    private String email;

}
