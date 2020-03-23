package io.wjh.wcartadministrationback.dao;

import io.wjh.wcartadministrationback.po.ProductOperation;

public interface ProductOperationMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(ProductOperation record);

    int insertSelective(ProductOperation record);

    ProductOperation selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(ProductOperation record);

    int updateByPrimaryKey(ProductOperation record);
}