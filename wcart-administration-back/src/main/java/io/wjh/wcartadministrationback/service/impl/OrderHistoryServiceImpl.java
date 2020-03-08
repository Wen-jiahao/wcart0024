package io.wjh.wcartadministrationback.service.impl;

import io.wjh.wcartadministrationback.dao.OrderHistoryMapper;
import io.wjh.wcartadministrationback.dto.in.OrderHistoryCreateInDTO;
import io.wjh.wcartadministrationback.po.Order;
import io.wjh.wcartadministrationback.po.OrderHistory;
import io.wjh.wcartadministrationback.service.OrderHistoryService;
import io.wjh.wcartadministrationback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryMapper orderHistoryMapper;

    @Autowired
    private OrderService orderService;

    @Override
    public List<OrderHistory> getListByOrderId(Long orderId) {
        return orderHistoryMapper.selectByOrderId(orderId);
    }

    @Override
    @Transactional
    public Long create(OrderHistoryCreateInDTO orderHistoryCreateInDTO) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(orderHistoryCreateInDTO.getOrderId());
        orderHistory.setTime(new Date());
        orderHistory.setOrderStatus(orderHistoryCreateInDTO.getOrderStatus());
        orderHistory.setComment(orderHistoryCreateInDTO.getComment());
        orderHistory.setCustomerNotified(orderHistoryCreateInDTO.getCustomeNotified());
        orderHistoryMapper.insertSelective(orderHistory);
        //添加历史订单时跟新order的状态和修改时间
        Order order = new Order();
        order.setStatus(orderHistory.getOrderStatus());
        order.setOrderId(orderHistory.getOrderId());
        order.setUpdateTime(new Date());
        orderService.update(order);
        Long orderHistoryId = orderHistory.getOrderHistoryId();

        return orderHistoryId;
    }
}
