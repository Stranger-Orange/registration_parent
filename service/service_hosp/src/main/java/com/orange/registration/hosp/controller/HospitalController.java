package com.orange.registration.hosp.controller;

import com.orange.registration.common.result.Result;
import com.orange.registration.hosp.service.HospitalService;
import com.orange.registration.model.hosp.Hospital;
import com.orange.registration.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Orange
 * @create 2021-05-26 21:17
 */
@RestController
@RequestMapping("/admin/hosp/hospital")
//@CrossOrigin
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    /**
     * 医院列表（条件查询带分页）
     * @param page
     * @param limit
     * @param hospitalQueryVo
     * @return
     */
    @GetMapping("list/{page}/{limit}")
    public Result listHosp(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pageModel = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        List<Hospital> content = pageModel.getContent();
        long totalElements = pageModel.getTotalElements();
        return  Result.ok(pageModel);
    }

    /**
     * 更新医院上线状态
     * @param id
     * @param status
     * @return
     */
    @ApiOperation(value = "更新医院上线状态")
    @GetMapping("updateHospStatus/{id}/{status}")
    public Result updateHospStatus(
            @ApiParam(name = "id", value = "医院id", required = true)
            @PathVariable("id") String id,
            @ApiParam(name = "status", value = "状态（0：未上线 1：已上线）", required = true)
            @PathVariable("status") Integer status){
        hospitalService.updateStatus(id, status);
        return Result.ok();
    }

    /**
     * 获取医院详情
     * @param id
     * @return
     */
    @ApiOperation(value = "获取医院详情")
    @GetMapping("showHospDetail/{id}")
    public Result showHospDetail(
            @ApiParam(name = "id", value = "医院id", required = true)
            @PathVariable String id) {
        Map<String, Object> map = hospitalService.getHospById(id);
        return Result.ok(map);
    }

}
