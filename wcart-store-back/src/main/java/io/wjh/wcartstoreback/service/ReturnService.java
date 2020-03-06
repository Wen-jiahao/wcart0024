package io.wjh.wcartstoreback.service;

import com.github.pagehelper.Page;
import io.wjh.wcartstoreback.po.Return;

import java.util.List;

public interface ReturnService {
    void create(Return aReturn);

    Page<Return> getList(Integer customerId, Integer pageNum);

    Return getById(Integer returnId);
}
