package io.wjh.wcartstoreback.service;

import io.wjh.wcartstoreback.dto.in.OrderCheckoutInDTO;

public interface OrderService {
    Long checkout(OrderCheckoutInDTO orderCheckoutInDTO,Integer customerId);
}
