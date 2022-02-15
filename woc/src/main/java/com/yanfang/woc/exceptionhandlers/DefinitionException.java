package com.yanfang.woc.exceptionhandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefinitionException extends RuntimeException
{
    // 获取错误信息
    protected String errMsg;

    // 获取状态码
    protected Integer errCode;
}
