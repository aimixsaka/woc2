package com.yanfang.woc.annotations;

import java.lang.annotation.*;

/**
 * 用户注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface User
{
}
