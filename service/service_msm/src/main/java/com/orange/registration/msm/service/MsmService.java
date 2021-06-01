package com.orange.registration.msm.service;

/**
 * @author Orange
 * @create 2021-06-01 19:41
 */
public interface MsmService {
    /**
     * 发送手机验证码
     * @param phone
     * @param code
     * @return
     */
    boolean send(String phone, String code);
}
