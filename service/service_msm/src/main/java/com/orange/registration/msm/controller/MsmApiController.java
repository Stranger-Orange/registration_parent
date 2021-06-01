package com.orange.registration.msm.controller;

import com.orange.registration.common.result.Result;
import com.orange.registration.msm.service.MsmService;
import com.orange.registration.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Orange
 * @create 2021-06-01 19:41
 */
@RestController
@RequestMapping("/api/msm")
public class MsmApiController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送手机验证码
     * @param phone
     * @return
     */
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {
        //从Redis中获取验证码(key是手机号 value是验证码) 获得到则返回ok
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //生成验证码
        code = RandomUtil.getSixBitRandom();
        //通过短信服务进行发送
        boolean isSend = msmService.send(phone, code);
        //生成验证码放到Redis中并设置有效时间
        if (isSend) {
            redisTemplate.opsForValue().set(phone, code, 2, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.fail().message("发送短信失败");
        }
    }
}
