package com.yanfang.woc.annotations;

import java.lang.annotation.*;

/**
 * 超管注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SuperAdmin
{
}
