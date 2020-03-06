package io.wjh.wcartadministrationback.service;

import io.wjh.wcartadministrationback.dto.in.OrderHistoryCreateInDTO;
import io.wjh.wcartadministrationback.po.OrderHistory;

import java.util.List;

public interface OrderHistoryService {
    List<OrderHistory> getListByOrderId(Long orderId);

    Long create(OrderHistoryCreateInDTO orderHistoryCreateInDTO);
}
