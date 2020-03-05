package io.wjh.wcartstoreback.service;

import com.github.pagehelper.Page;
import io.wjh.wcartstoreback.dto.in.OrderCheckoutInDTO;
import io.wjh.wcartstoreback.dto.out.OrderShowOutDTO;
import io.wjh.wcartstoreback.po.Order;

public interface OrderService {
    Long checkout(OrderCheckoutInDTO orderCheckoutInDTO,Integer customerId);

    Page<Order> getByCustomerId(Integer customerId, Integer pageNum);

    OrderShowOutDTO getById(Long orderId);
}
