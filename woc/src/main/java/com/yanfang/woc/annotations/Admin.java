package com.yanfang.woc.annotations;

import java.lang.annotation.*;

/**
 * 管理员注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Admin
{

}

