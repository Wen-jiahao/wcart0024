package io.wjh.wcartstoreback.service;

import io.wjh.wcartstoreback.po.ReturnHistory;

import java.util.List;

public interface ReturnHistoryService {
    List<ReturnHistory> getByReturnId(Integer returnId);
}
