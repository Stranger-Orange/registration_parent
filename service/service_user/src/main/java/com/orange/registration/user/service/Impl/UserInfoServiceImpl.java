package com.orange.registration.user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.registration.common.exception.RegistrationException;
import com.orange.registration.common.result.ResultCodeEnum;
import com.orange.registration.model.user.UserInfo;
import com.orange.registration.user.mapper.UserInfoMapper;
import com.orange.registration.user.service.UserInfoService;
import com.orange.registration.vo.user.LoginVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Orange
 * @create 2021-05-29 11:58
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    /**
     * 用户手机号登录接口
     * @param loginVo
     * @return
     */
    @Override
    public Map<String, Object> loginUser(LoginVo loginVo) {
        //从loginVo获取输入的手机号和验证码
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();
        //判断手机号和验证码是否为空
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            throw new RegistrationException(ResultCodeEnum.PARAM_ERROR);
        }
        //TODO 判断手机验证码和输入验证码是否一致

        //判断是否是第一次注册：根据手机号查询数据库进行判断
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        UserInfo userInfo = baseMapper.selectOne(wrapper);
        if (userInfo == null) {
            //第一次使用，添加信息到数据库
            userInfo = new UserInfo();
            userInfo.setName("");
            userInfo.setPhone(phone);
            userInfo.setStatus(1);
            baseMapper.insert(userInfo);
        }
        //校验是否被禁用
        if(userInfo.getStatus() == 0) {
            throw new RegistrationException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }
        //直接登录

        //返回登录信息

        //返回用户名

        //返回token
        Map<String, Object> map = new HashMap<>();
        String name = userInfo.getName();
        if(StringUtils.isEmpty(name)) {
            name = userInfo.getNickName();
        }
        if(StringUtils.isEmpty(name)) {
            name = userInfo.getPhone();
        }
        map.put("name", name);
        //TODO token生成
        map.put("token", "");
        return map;
    }
}
