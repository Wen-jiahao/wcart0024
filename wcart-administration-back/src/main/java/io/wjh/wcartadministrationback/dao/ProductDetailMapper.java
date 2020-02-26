package io.wjh.wcartadministrationback.dao;

import io.wjh.wcartadministrationback.po.ProductDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDetailMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(ProductDetail record);

    int insertSelective(ProductDetail record);

    ProductDetail selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(ProductDetail record);

    int updateByPrimaryKeyWithBLOBs(ProductDetail record);

    int updateByPrimaryKey(ProductDetail record);

    void batchDelete(@Param("productIds") List<Integer> productIds);
}