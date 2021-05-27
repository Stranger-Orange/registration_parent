package com.orange.registration.hosp.repository;

import com.orange.registration.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Orange
 * @create 2021-05-23 15:18
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {
    /**
     * 判断是否存在数据
     * @param hoscode
     * @return
     */
    Hospital getHospitalByHoscode(String hoscode);

    /**
     * 根据医院名称获取医院列表
     * @param hosname
     * @return
     */
    List<Hospital> findHospitalByHosnameLike(String hosname);
}
