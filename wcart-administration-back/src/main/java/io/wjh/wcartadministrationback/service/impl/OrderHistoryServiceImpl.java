package io.wjh.wcartadministrationback.service.impl;

import io.wjh.wcartadministrationback.dao.OrderHistoryMapper;
import io.wjh.wcartadministrationback.dto.in.OrderHistoryCreateInDTO;
import io.wjh.wcartadministrationback.po.OrderHistory;
import io.wjh.wcartadministrationback.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryMapper orderHistoryMapper;

    @Override
    public List<OrderHistory> getListByOrderId(Long orderId) {
        return orderHistoryMapper.selectByOrderId(orderId);
    }

    @Override
    public Long create(OrderHistoryCreateInDTO orderHistoryCreateInDTO) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(orderHistoryCreateInDTO.getOrderId());
        orderHistory.setTime(new Date());
        orderHistory.setOrderStatus(orderHistoryCreateInDTO.getOrderStatus());
        orderHistory.setComment(orderHistoryCreateInDTO.getComment());
        orderHistory.setCustomerNotified(orderHistoryCreateInDTO.getCustomeNotified());
        orderHistoryMapper.insertSelective(orderHistory);
        Long orderHistoryId = orderHistory.getOrderHistoryId();
        return orderHistoryId;
    }
}
