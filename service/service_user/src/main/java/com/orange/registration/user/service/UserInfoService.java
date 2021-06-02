package com.orange.registration.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.registration.model.user.UserInfo;
import com.orange.registration.vo.user.LoginVo;

import java.util.Map;

/**
 * @author Orange
 * @create 2021-05-29 11:58
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 用户手机号登录接口
     * @param loginVo
     * @return
     */
    Map<String, Object> loginUser(LoginVo loginVo);

    /**
     * 根据openid判断数据库中是否存在扫码人信息
     * @param openid
     * @return
     */
    UserInfo selectWxInfoOpenId(String openid);
}

