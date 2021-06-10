package comm.orange.registration.order.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.registration.model.order.OrderInfo;
import comm.orange.registration.order.mapper.OrderMapper;
import comm.orange.registration.order.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author Orange
 * @create 2021-06-10 10:51
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {
    /**
     * 创建订单
     * @param scheduleId
     * @param patientId
     * @return
     */
    @Override
    public Long saveOrder(String scheduleId, Long patientId) {
        return null;
    }
}
