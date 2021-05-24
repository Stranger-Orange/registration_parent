package com.orange.registration.hosp.service;

import com.orange.registration.model.hosp.Schedule;
import com.orange.registration.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author Orange
 * @create 2021-05-24 22:16
 */
public interface ScheduleService {

    //上传排班
    void save(Map<String, Object> paramMap);

    //查询排班
    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    //删除排班
    void remove(String hoscode, String hosScheduleId);
}
