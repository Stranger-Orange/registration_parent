package com.orange.registration.hosp.service;

import com.orange.registration.model.hosp.Department;
import com.orange.registration.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author Orange
 * @create 2021-05-24 20:49
 */
public interface DepartmentService {
    /**
     * 上传科室接口
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 查询科室接口
     * @param page
     * @param limit
     * @param departmentQueryVo
     * @return
     */
    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

    /**
     * 删除科室接口
     * @param hoscode
     * @param depcode
     */
    void remove(String hoscode, String depcode);
}
