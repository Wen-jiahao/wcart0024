package io.wjh.wcartadministrationback.service;

import io.wjh.wcartadministrationback.po.ReturnHistory;

import java.util.List;

public interface ReturnHistoryService {
    List<ReturnHistory> getListByReturnId(Integer returnId);

    void create(ReturnHistory returnHistory);
}
