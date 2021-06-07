package com.orange.registration.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.registration.model.user.Patient;

import java.util.List;

/**
 * @author Orange
 * @create 2021-06-07 19:55
 */
public interface PatientService extends IService<Patient> {
    /**
     * 获取就诊人列表
     * @param userId
     * @return
     */
    List<Patient> findAllUserId(Long userId);

    /**
     * 根据id获取就诊人信息
     * @param id
     * @return
     */
    Patient getPatientId(Long id);
}
