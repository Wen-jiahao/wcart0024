package io.wjh.wcartstoreback.service.impl;

import io.wjh.wcartstoreback.dao.ReturnMapper;
import io.wjh.wcartstoreback.po.Return;
import io.wjh.wcartstoreback.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnMapper returnMapper;

    @Override
    public void create(Return aReturn) {
        returnMapper.insertSelective(aReturn);
    }
}
