package io.wjh.wcartstoreback.service.impl;

import io.wjh.wcartstoreback.dao.ReturnHistoryMapper;
import io.wjh.wcartstoreback.po.ReturnHistory;
import io.wjh.wcartstoreback.service.ReturnHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnHistoryServiceImpl implements ReturnHistoryService {
    @Autowired
    private ReturnHistoryMapper returnHistoryMapper;

    @Override
    public List<ReturnHistory> getByReturnId(Integer returnId) {
        return returnHistoryMapper.getByReturnId(returnId);
    }
}
