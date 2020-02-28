package io.wjh.wcartstoreback.dao;

import com.github.pagehelper.Page;
import io.wjh.wcartstoreback.dto.out.ProductListOutDTO;
import io.wjh.wcartstoreback.po.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Page<ProductListOutDTO> search();
}