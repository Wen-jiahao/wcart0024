package io.wjh.wcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.po.Order;

import java.util.List;

public interface OrderService {
    Page<Order> search(Integer pageNum);
}