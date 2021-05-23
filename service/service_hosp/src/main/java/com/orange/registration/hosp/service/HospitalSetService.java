package com.orange.registration.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.registration.model.hosp.HospitalSet;

/**
 * @author Orange
 * @create 2021-05-16 21:28
 */
public interface HospitalSetService extends IService<HospitalSet> {
    //根据传过来的医院编码，查询数据库，查询签名
    String getSignKey(String hoscode);
}
