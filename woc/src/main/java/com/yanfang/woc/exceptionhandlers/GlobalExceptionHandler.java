package com.yanfang.woc.exceptionhandlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类，处理之前定义的两种异常
 * @see com.yanfang.woc.exceptionhandlers.DefinitionException
 * @see com.yanfang.woc.exceptionhandlers.ErrorEnum
 */
@ControllerAdvice // 代表开启全局异常处理
@ResponseBody // 将结果转换为json格式
public class GlobalExceptionHandler
{
    @ExceptionHandler(value = DefinitionException.class) // 代表对DefinitionException类型的异常进行处理
    public Result selfDefinedExceptionHandler(DefinitionException e)
    {
        return Result.defineError(e);
    }

    @ExceptionHandler(value = Exception.class)
    public Result enumExceptionHandler()
    {
        return Result.enumError(ErrorEnum.OTHER_ERROR);
    }

}
