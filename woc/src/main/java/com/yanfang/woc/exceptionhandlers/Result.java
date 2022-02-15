package com.yanfang.woc.exceptionhandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>
{
    // 是否成功
    boolean success;

    // 错误信息
    String errMsg;

    // 状态码
    Integer errCode;

    // 响应的内容
    T data;

    // 自定义异常地返回，可在controller层调用
    public static Result defineError(DefinitionException e)
    {
        return new Result(false, e.getErrMsg(), e.getErrCode(), null);
    }

    // 枚举类型异常返回
    public static Result enumError(ErrorEnum e)
    {
        return new Result(false, e.getErrorMsg(), e.getErrorCode(), null);
    }

}
