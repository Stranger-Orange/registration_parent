package com.orange.registration.hosp.service;

import com.orange.registration.model.hosp.Department;
import com.orange.registration.vo.hosp.DepartmentQueryVo;
import com.orange.registration.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
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

    /**
     * 查询医院所有科室列表
     * @param hoscode
     * @return
     */
    List<DepartmentVo> findDeptTree(String hoscode);

    /**
     * 根据科室编号和医院编号查询科室名称
     * @param hoscode
     * @param depcode
     * @return
     */
    String getDepName(String hoscode, String depcode);

    /**
     * 根据科室编号和医院编号查询科室
     * @param hoscode
     * @param depcode
     * @return
     */
    Department getDepartment(String hoscode, String depcode);
}
