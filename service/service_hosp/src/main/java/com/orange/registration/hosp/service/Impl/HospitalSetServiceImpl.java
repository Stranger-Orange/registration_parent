package com.orange.registration.hosp.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.registration.hosp.mapper.HospitalSetMapper;
import com.orange.registration.hosp.service.HospitalSetService;
import com.orange.registration.model.hosp.HospitalSet;
import org.springframework.stereotype.Service;

/**
 * @author Orange
 * @create 2021-05-16 21:29
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    //根据传过来的医院编码，查询数据库，查询签名
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode", hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        return hospitalSet.getSignKey();
    }
}
