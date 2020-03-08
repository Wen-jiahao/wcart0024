package io.wjh.wcartadministrationback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.wjh.wcartadministrationback.dao.ReturnHistoryMapper;
import io.wjh.wcartadministrationback.dao.ReturnMapper;
import io.wjh.wcartadministrationback.dto.in.ReturnSearchInDTO;
import io.wjh.wcartadministrationback.dto.in.ReturnUpdateActionInDTO;
import io.wjh.wcartadministrationback.po.Return;
import io.wjh.wcartadministrationback.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnServiceIml implements ReturnService {
    @Autowired
    private ReturnMapper returnMapper;

    @Autowired
    private ReturnHistoryMapper returnHistoryMapper;

    @Override
    public Page<Return> search(ReturnSearchInDTO returnSearchInDTO, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<Return> page=returnMapper.search(returnSearchInDTO);
        return page;
    }

    @Override
    public Return getById(Integer returnId) {
        return returnMapper.selectByPrimaryKey(returnId);
    }

    @Override
    public void updateAction(ReturnUpdateActionInDTO returnUpdateActionInDTO) {
        Return aReturn = new Return();
        aReturn.setAction(returnUpdateActionInDTO.getAction());
        aReturn.setReturnId(returnUpdateActionInDTO.getReturnId());
        returnMapper.updateByPrimaryKeySelective(aReturn);
    }
}
