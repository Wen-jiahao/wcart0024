package io.wjh.wcartstoreback.service;

import io.wjh.wcartstoreback.po.Order;
import io.wjh.wcartstoreback.po.OrderHistory;

import java.util.List;

public interface OrderHistoryService {
    List<OrderHistory> getById(Long orderId);
}
