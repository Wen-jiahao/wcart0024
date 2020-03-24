package io.wjh.wcartstoreback.service.impl;

import io.wjh.wcartstoreback.dao.ProductOperationMapper;
import io.wjh.wcartstoreback.po.ProductOperation;
import io.wjh.wcartstoreback.service.ProductOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ProductOperationServiceImpl implements ProductOperationService {

    @Autowired
    private ProductOperationMapper productOperationMapper;
    @Override
    @Transactional
    public void count(Integer productId) {
        ProductOperation productOperation1 = productOperationMapper.selectByPrimaryKey(productId);
        if (productOperation1==null){
            ProductOperation productOperation = new ProductOperation();
            productOperation.setProductId(productOperation1.getProductId());
            productOperation.setAllCount(1);
            productOperation.setDayCount(1);
            productOperation.setRecentTime(new Date());
            productOperationMapper.insertSelective(productOperation);
        }else {
            ProductOperation productOperation = new ProductOperation();
            productOperation.setProductId(productOperation1.getProductId());
            productOperation.setAllCount(productOperation1.getAllCount()+1);
            productOperation.setDayCount(productOperation1.getDayCount()+1);
            productOperation.setRecentTime(new Date());
            productOperationMapper.updateByPrimaryKeySelective(productOperation);
        }

    }
}
