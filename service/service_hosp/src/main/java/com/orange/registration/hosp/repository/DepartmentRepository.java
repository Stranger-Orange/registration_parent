package com.orange.registration.hosp.repository;

import com.orange.registration.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Orange
 * @create 2021-05-24 20:48
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
    //上传科室接口
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}
