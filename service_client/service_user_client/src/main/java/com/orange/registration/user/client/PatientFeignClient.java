package com.orange.registration.user.client;

import com.orange.registration.model.user.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Orange
 * @create 2021-06-10 11:02
 */
@FeignClient(value = "service-user")
@Repository
public interface PatientFeignClient {
    /**
     * 根据就诊人id获取就诊人信息
     * @param id
     * @return
     */
    @GetMapping("/api/user/patient/inner/get/{id}")
    public Patient getPatientOrder(@PathVariable("id") Long id);
}
