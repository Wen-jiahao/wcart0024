package io.wjh.wcartstoreback.service.impl;

import io.wjh.wcartstoreback.dao.OrderHistoryMapper;
import io.wjh.wcartstoreback.po.Order;
import io.wjh.wcartstoreback.po.OrderHistory;
import io.wjh.wcartstoreback.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
    @Autowired
    private OrderHistoryMapper orderHistoryMapper;
    @Override
    public List<OrderHistory> getById(Long orderId) {
        return orderHistoryMapper.getByOrderId(orderId);
    }
}
