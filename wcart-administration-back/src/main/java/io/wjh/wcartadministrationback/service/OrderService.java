package io.wjh.wcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.OrderSearchInDTO;
import io.wjh.wcartadministrationback.dto.out.OrderListOutDTD;
import io.wjh.wcartadministrationback.dto.out.OrderShowOutDTO;
import io.wjh.wcartadministrationback.po.Order;

import java.util.List;

public interface OrderService {
    Page<OrderListOutDTD> search(OrderSearchInDTO orderSearchInDTO,Integer pageNum);


    OrderShowOutDTO getById(Long orderId);

    //修改订单的状态
    void update(Order order);
}
