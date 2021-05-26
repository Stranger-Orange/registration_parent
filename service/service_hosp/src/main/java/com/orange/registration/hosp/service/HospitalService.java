package com.orange.registration.hosp.service;

import com.orange.registration.model.hosp.Hospital;
import com.orange.registration.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author Orange
 * @create 2021-05-23 15:20
 */
public interface HospitalService {
    /**
     * 上传医院信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 现根据医院编号进行查询
     * @param hoscode
     * @return
     */
    Hospital getByHoscode(String hoscode);

    /**
     * 医院列表（条件查询带分页）
     * @param page
     * @param limit
     * @param hospitalQueryVo
     * @return
     */
    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);
}
