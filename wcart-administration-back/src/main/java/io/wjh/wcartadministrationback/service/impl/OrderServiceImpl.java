package io.wjh.wcartadministrationback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.wjh.wcartadministrationback.dao.OrderDetailMapper;
import io.wjh.wcartadministrationback.dao.OrderMapper;
import io.wjh.wcartadministrationback.po.Order;
import io.wjh.wcartadministrationback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Page<Order> search(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<Order> list=orderMapper.searchOrderList();
        return list;
    }
}
