package com.orange.registration.user.api;

import com.alibaba.fastjson.JSONObject;
import com.orange.registration.common.helper.JwtHelper;
import com.orange.registration.common.result.Result;
import com.orange.registration.model.user.UserInfo;
import com.orange.registration.user.service.UserInfoService;
import com.orange.registration.user.utils.ConstantWxPropertiesUtil;
import com.orange.registration.user.utils.HttpClientUtils;
import com.sun.deploy.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("callback")
    public String callback(String code, String state){
        //获取临时票据code
        System.out.println("code"+code);
        //使用code和appid以及appscrect换取access_token
        StringBuffer baseAccessTokenUrl = new StringBuffer()
                .append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=%s")
                .append("&secret=%s")
                .append("&code=%s")
                .append("&grant_type=authorization_code");
        String accessTokenUrl = String.format(baseAccessTokenUrl.toString(),
                ConstantWxPropertiesUtil.WX_OPEN_APP_ID,
                ConstantWxPropertiesUtil.WX_OPEN_APP_SECRET,
                code);
        //使用HttpClient请求这个地址
        try {
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            System.out.println("accessTokenInfo" + accessTokenInfo);
            //从返回字符串中获取openid和access_token
            JSONObject jsonObject = JSONObject.parseObject(accessTokenInfo);
            String access_token = jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");
            //根据openid判断数据库中是否存在扫码人信息
            UserInfo userInfo = userInfoService.selectWxInfoOpenId(openid);
            if (userInfo == null) {
                //根据openid和access_token获取微信用户的基本信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);
                String resultInfo = HttpClientUtils.get(userInfoUrl);
                System.out.println("resultInfo" + resultInfo);
                JSONObject resultUserInfoJson = JSONObject.parseObject(resultInfo);
                //解析用户信息
                String nickname = resultUserInfoJson.getString("nickname");
                String headimgurl = resultUserInfoJson.getString("headimgurl");
                //获取扫码人信息并添加到数据库中
                userInfo = new UserInfo();
                userInfo.setNickName(nickname);
                userInfo.setOpenid(openid);
                userInfo.setStatus(1);
                userInfoService.save(userInfo);
            }
            //返回name和token字符串
            Map<String, Object> map = new HashMap<>();
            String name = userInfo.getName();
            if(StringUtils.isEmpty(name)) {
                name = userInfo.getNickName();
            }
            if(StringUtils.isEmpty(name)) {
                name = userInfo.getPhone();
            }
            map.put("name", name);
            //判断userInfo中是否有手机号，前端判断：如果openid不为空，则绑定手机号；如果openid为空，则不需要绑定手机号
            if(StringUtils.isEmpty(userInfo.getPhone())) {
                map.put("openid", userInfo.getOpenid());
            } else {
                map.put("openid", "");
            }
            //使用Jwt生成token
            String token = JwtHelper.createToken(userInfo.getId(), name);
            map.put("token", token);
            return "redirect:" + ConstantWxPropertiesUtil.REGISTRATION_BASE_URL + "/weixin/callback?token="+map.get("token")+"&openid="+map.get("openid")+ "&name="+URLEncoder.encode((String) map.get("name"), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取微信登录参数
     */
    @GetMapping("getLoginParam")
    @ResponseBody
    public Result genQrConnect(){
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("appid", ConstantWxPropertiesUtil.WX_OPEN_APP_ID);
            map.put("scope", "snsapi_login");
            String redirectUri = URLEncoder.encode(ConstantWxPropertiesUtil.WX_OPEN_REDIRECT_URL, "UTF-8");
            map.put("redirectUri", redirectUri);
            map.put("state", System.currentTimeMillis()+"");
            return Result.ok(map);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
