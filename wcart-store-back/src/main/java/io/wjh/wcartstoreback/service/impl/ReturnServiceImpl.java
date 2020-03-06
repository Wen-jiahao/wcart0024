package io.wjh.wcartstoreback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.wjh.wcartstoreback.dao.ReturnMapper;
import io.wjh.wcartstoreback.po.Return;
import io.wjh.wcartstoreback.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnMapper returnMapper;

    @Override
    public void create(Return aReturn) {
        returnMapper.insertSelective(aReturn);
    }

    @Override
    public Page<Return> getList(Integer customerId, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<Return> returns = returnMapper.selectByCustomerId(customerId);
        return returns;
    }
}
