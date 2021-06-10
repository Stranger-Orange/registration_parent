package comm.orange.registration.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.registration.model.order.OrderInfo;

/**
 * @author Orange
 * @create 2021-06-10 10:50
 */
public interface OrderService extends IService<OrderInfo> {
    /**
     * 创建订单
     * @param scheduleId
     * @param patientId
     * @return
     */
    Long saveOrder(String scheduleId, Long patientId);
}
