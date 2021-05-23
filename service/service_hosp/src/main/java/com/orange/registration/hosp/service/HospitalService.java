package com.orange.registration.hosp.service;

import com.orange.registration.model.hosp.Hospital;

import java.util.Map;

/**
 * @author Orange
 * @create 2021-05-23 15:20
 */
public interface HospitalService {
    //上传医院接口
    void save(Map<String, Object> paramMap);

    //现根据医院编号进行查询
    Hospital getByHoscode(String hoscode);
}
