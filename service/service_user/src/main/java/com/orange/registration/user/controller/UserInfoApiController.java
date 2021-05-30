package com.orange.registration.user.controller;

import com.orange.registration.common.result.Result;
import com.orange.registration.user.service.UserInfoService;
import com.orange.registration.vo.user.LoginVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Orange
 * @create 2021-05-29 11:57
 */
@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户手机号登录接口
     * @param loginVo
     * @param request
     * @return
     */
    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        Map<String, Object> info = userInfoService.loginUser(loginVo);
        return Result.ok(info);
    }


}