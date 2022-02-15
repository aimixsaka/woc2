package com.yanfang.woc.exceptionhandlers;

/**
 * 自定义的错误类型枚举
 */
public enum ErrorEnum
{
    // 数据操作错误定义
    NO_PERMISSION("权限不足", 403),
    NO_AUTH("您还未登录", 401),
    NOT_FOUND("未找到该资源!", 404),
    INTERNAL_SERVER_ERROR("服务器出现问题", 500),
    OTHER_ERROR("未知错误", 40004)
    ;

    // 错误信息
    private String errorMsg;

    // 其他错误码
    private Integer errorCode;

    // 枚举类型的具体构造
    ErrorEnum(String errorMsg, Integer errorCode)
    {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }
    // get方法获取数据
    public String getErrorMsg()
    {
        return errorMsg;
    }

    public Integer getErrorCode()
    {
        return errorCode;
    }
}
