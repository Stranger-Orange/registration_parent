package com.orange.registration.hosp.controller.Api;

import com.orange.registration.common.result.Result;
import com.orange.registration.hosp.service.HospitalService;
import com.orange.registration.model.hosp.Hospital;
import com.orange.registration.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Orange
 * @create 2021-05-27 20:52
 */
@Api(tags = "医院管理接口")
@RestController
@RequestMapping("/api/hosp/hospital")
public class HospApiController {

    @Autowired
    private HospitalService hospitalService;

    @ApiOperation(value = "查询医院列表")
    @GetMapping("findHospList/{page}/{limit}")
    public Result findHospList(@PathVariable Integer page, @PathVariable Integer limit, HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pageModel = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        List<Hospital> content = pageModel.getContent();
        int totalPages = pageModel.getTotalPages();
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "根据医院名称获取医院列表")
    @GetMapping("findByHosName/{hosname}")
    public Result findByHosName(@ApiParam(name = "hosname", value = "医院名称", required = true)
                                @PathVariable String hosname) {
        List<Hospital> list = hospitalService.findByHosname(hosname);
        return Result.ok(list);
    }

}
