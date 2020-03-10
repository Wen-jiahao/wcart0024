package io.wjh.wcartadministrationback.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.wjh.wcartadministrationback.dao.OrderDetailMapper;
import io.wjh.wcartadministrationback.dao.OrderMapper;
import io.wjh.wcartadministrationback.dto.in.OrderSearchInDTO;
import io.wjh.wcartadministrationback.dto.out.OrderListOutDTD;
import io.wjh.wcartadministrationback.dto.out.OrderProductShowOutDTO;
import io.wjh.wcartadministrationback.dto.out.OrderShowOutDTO;
import io.wjh.wcartadministrationback.po.Customer;
import io.wjh.wcartadministrationback.po.Order;
import io.wjh.wcartadministrationback.po.OrderDetail;
import io.wjh.wcartadministrationback.service.CustomerService;
import io.wjh.wcartadministrationback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private CustomerService customerService;

    @Override
    public Page<OrderListOutDTD> search(OrderSearchInDTO orderSearchInDTO, Integer pageNum) {
        PageHelper.startPage(pageNum,10);

        Page<OrderListOutDTD> list=orderMapper.searchOrderList(orderSearchInDTO.getOrderId(),
                orderSearchInDTO.getCreateTime()==null?null:new Date(orderSearchInDTO.getCreateTime()),
                orderSearchInDTO.getEndTime()==null?null:new Date(orderSearchInDTO.getEndTime()),
                orderSearchInDTO.getStatus(),
                orderSearchInDTO.getTotalPrice(),
                orderSearchInDTO.getCustomerName());
        return list;
    }

    @Override
    public OrderShowOutDTO getById(Long orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(orderId);

        OrderShowOutDTO orderShowOutDTO = new OrderShowOutDTO();
        orderShowOutDTO.setOrderId(orderId);
        orderShowOutDTO.setCustomerId(order.getCustomerId());
        Customer customer = customerService.getById(order.getCustomerId());
        orderShowOutDTO.setCustomerName(customer.getRealName());
        orderShowOutDTO.setStatus(order.getStatus());
        orderShowOutDTO.setTotalPrice(order.getTotalPrice());
        orderShowOutDTO.setRewordPoints(order.getRewordPoints());
        orderShowOutDTO.setCreateTimestamp(order.getCreateTime().getTime());
        orderShowOutDTO.setUpdateTimestamp(order.getUpdateTime().getTime());

        orderShowOutDTO.setShipMethod(orderDetail.getShipMethod());
        orderShowOutDTO.setShipAddress(orderDetail.getShipAddress());
        orderShowOutDTO.setShipPrice(orderDetail.getShipPrice());
        orderShowOutDTO.setPayMethod(orderDetail.getPayMethod());
        orderShowOutDTO.setInvoiceAddress(orderDetail.getInvoiceAddress());
        orderShowOutDTO.setInvoicePrice(orderDetail.getInvoicePrice());
        orderShowOutDTO.setComment(orderDetail.getComment());
        List<OrderProductShowOutDTO> orderProductShowOutDTOS = JSON.parseArray(orderDetail.getOrderProducts(), OrderProductShowOutDTO.class);
        orderShowOutDTO.setOrderProducts(orderProductShowOutDTOS);
        return orderShowOutDTO;
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
