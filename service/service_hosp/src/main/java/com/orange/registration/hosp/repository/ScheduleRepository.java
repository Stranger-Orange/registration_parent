package com.orange.registration.hosp.repository;

import com.orange.registration.model.hosp.Department;
import com.orange.registration.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Orange
 * @create 2021-05-24 22:15
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    //根据医院编号和排班编号进行查询
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);

}
