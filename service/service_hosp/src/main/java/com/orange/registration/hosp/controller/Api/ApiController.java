package com.orange.registration.hosp.controller.Api;

import com.orange.registration.hosp.service.ScheduleService;
import com.orange.registration.model.hosp.Schedule;
import com.orange.registration.vo.hosp.ScheduleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import com.orange.registration.common.result.Result;
import com.orange.registration.common.result.ResultCodeEnum;
import com.orange.registration.helper.HttpRequestHelper;
import com.orange.registration.hosp.service.DepartmentService;
import com.orange.registration.hosp.service.HospitalService;
import com.orange.registration.hosp.service.HospitalSetService;
import com.orange.registration.model.hosp.Department;
import com.orange.registration.model.hosp.Hospital;
import com.orange.registration.utils.MD5;
import com.orange.registration.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Orange
 * @create 2021-05-23 15:22
 */
@Api(tags = "医院管理API接口")
@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 删除排班
     * @param request
     * @return
     */
    @PostMapping("schedule/remove")
    public Result remove(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号和排班编号
        String hoscode = (String) paramMap.get("hoscode");
        String hosScheduleId = (String) paramMap.get("hosScheduleId");
        //TODO 签名校验
        scheduleService.remove(hoscode,hosScheduleId);
        return Result.ok();
    }

    /**
     * 查询排班
     * @param request
     * @return
     */
    @PostMapping("schedule/list")
    public Result findSchedule(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //医院编号 和 科室编号
        String hoscode = (String)paramMap.get("hoscode");
        String depcode = (String)paramMap.get("depcode");
        //当前页 和 每页记录数
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1 : Integer.parseInt((String)paramMap.get("limit"));
        //TODO 签名校验
        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        //调用service方法
        Page<Schedule> pageModel = scheduleService.findPageSchedule(page, limit, scheduleQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 上传排班
     * @param request
     * @return
     */
    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //TODO 签名校验
        scheduleService.save(paramMap);
        return Result.ok();
    }

    /**
     * 删除科室
     * @param request
     * @return
     */
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //医院编号 和 科室编号
        String hoscode = (String)paramMap.get("hoscode");
        String depcode = (String)paramMap.get("depcode");
        //TODO 签名校验
        departmentService.remove(hoscode,depcode);
        return Result.ok();
    }

    /**
     * 查询科室
     * @param request
     * @return
     */
    @ApiOperation(value = "获取分页列表")
    @PostMapping("department/list")
    public Result findDepartment(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //医院编号
        String hoscode = (String)paramMap.get("hoscode");
        //当前页 和 每页记录数
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1 : Integer.parseInt((String)paramMap.get("limit"));
        //TODO 签名校验
        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        //调用service方法
        Page<Department> pageModel = departmentService.findPageDepartment(page, limit, departmentQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 上传科室
     * @param request
     * @return
     */
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        //获取传递过来的科室信息
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
        //调用service的方法
        departmentService.save(paramMap);
        return Result.ok();

    }

    /**
     * 查询医院
     * @param request
     * @return
     */
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

    /**
     * 上传医院接口
     * @param request
     * @return
     */
    @ApiOperation(value = "上传医院")
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
