package com.orange.registration.common.exception;

import com.orange.registration.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Orange
 * @create 2021-05-16 22:19
 */
public class RegistrationException  extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public RegistrationException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public RegistrationException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "RegistrationException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}