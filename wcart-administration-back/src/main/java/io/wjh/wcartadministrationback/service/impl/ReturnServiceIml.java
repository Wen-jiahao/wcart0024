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

import java.util.Date;


@Service
public class ReturnServiceIml implements ReturnService {
    @Autowired
    private ReturnMapper returnMapper;

    @Autowired
    private ReturnHistoryMapper returnHistoryMapper;

    @Override
    public Page<Return> search(ReturnSearchInDTO returnSearchInDTO, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Integer customerId = returnSearchInDTO.getCustomerId();
        String customerName = returnSearchInDTO.getCustomerName();
        Long orderId = returnSearchInDTO.getOrderId();
        String productCode = returnSearchInDTO.getProductCode();
        String productName = returnSearchInDTO.getProductName();
        Integer returnId = returnSearchInDTO.getReturnId();
        String status = returnSearchInDTO.getStatus();
       Date startTime= returnSearchInDTO.getStartTimestamp()==null? null : new Date(returnSearchInDTO.getStartTimestamp());
        Date endTime= returnSearchInDTO.getEndTimestamp()==null? null : new Date(returnSearchInDTO.getEndTimestamp());
        Page<Return> page=returnMapper.search(customerId,customerName,orderId,productCode,productName,returnId,status,startTime,endTime);
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
