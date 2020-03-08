package io.wjh.wcartadministrationback.service.impl;

import io.wjh.wcartadministrationback.dao.ReturnHistoryMapper;
import io.wjh.wcartadministrationback.po.ReturnHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnHistoryService implements io.wjh.wcartadministrationback.service.ReturnHistoryService {

    @Autowired
    private ReturnHistoryMapper returnHistoryMapper;

    @Override
    public List<ReturnHistory> getListByReturnId(Integer returnId) {
        return returnHistoryMapper.getListByReturnId(returnId);
    }

    @Override
    public void create(ReturnHistory returnHistory) {
        returnHistoryMapper.insertSelective(returnHistory);
    }
}
