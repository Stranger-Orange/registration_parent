package com.orange.registration.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.registration.model.user.UserInfo;
import com.orange.registration.vo.user.LoginVo;
import com.orange.registration.vo.user.UserAuthVo;
import com.orange.registration.vo.user.UserInfoQueryVo;

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

    /**
     * 用户认证
     * @param userId
     * @param userAuthVo
     */
    void userAuth(Long userId, UserAuthVo userAuthVo);

    /**
     * 用户列表（条件查询带分页）
     * @param pageParam
     * @param userInfoQueryVo
     * @return
     */
    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);

    /**
     * 用户锁定
     * @param userId
     * @param status
     */
    void lock(Long userId, Integer status);

    /**
     * 用户详情
     * @param userId
     * @return
     */
    Map<String, Object> show(Long userId);

    /**
     * 认证审批
     * @param userId
     * @param authStatus
     */
    void approval(Long userId, Integer authStatus);
}

