package com.orange.registration.hosp.controller.Api;

import com.orange.registration.common.result.Result;
import com.orange.registration.common.result.ResultCodeEnum;
import com.orange.registration.helper.HttpRequestHelper;
import com.orange.registration.hosp.service.HospitalService;
import com.orange.registration.hosp.service.HospitalSetService;
import com.orange.registration.model.hosp.Hospital;
import com.orange.registration.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Orange
 * @create 2021-05-23 15:22
 */
@RestController
@RequestMapping("api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    //查询医院
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request) {
        //获取传递过来的医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //1、获取医院系统传递过来的签名
        String hospSign = (String) paramMap.get("sign");
        //2、根据传过来的医院编码，查询数据库，查询签名
        String singKey = hospitalSetService.getSignKey(hoscode);
        //3、讲数控库查出的签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(singKey);
        //4、判断签名是否一致
        if (!hospSign.equals(signKeyMd5)){
            throw new RuntimeException(String.valueOf(ResultCodeEnum.SIGN_ERROR));
        }
        //调用service中的方法实现根据医院编号进行查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }

    //上传医院接口
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request){
        //获取传递过来的医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //1、获取医院系统传递过来的签名
        String hospSign = (String) paramMap.get("sign");
        //2、根据传过来的医院编码，查询数据库，查询签名
        String hoscode = (String) paramMap.get("hoscode");
        String singKey = hospitalSetService.getSignKey(hoscode);
        //3、讲数控库查出的签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(singKey);
        //4、判断签名是否一致
        if (!hospSign.equals(signKeyMd5)){
            throw new RuntimeException(String.valueOf(ResultCodeEnum.SIGN_ERROR));
        }
        //图片传输过程中“+”转换为了“ ”，需要转换回来
        String logoData = (String)paramMap.get("logoData");
        logoData = logoData.replaceAll(" ","+");
        paramMap.put("logoData", logoData);
        //调用service的方法
        hospitalService.save(paramMap);
        return Result.ok();
    }
}
