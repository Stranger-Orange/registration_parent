package com.orange.registration.user.api;

import com.orange.registration.common.result.Result;
import com.orange.registration.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信操作接口
 * @author Orange
 * @create 2021-06-01 22:52
 */
@Controller
@RequestMapping("/api/ucenter/wx")
public class WeixinApiController {

}
