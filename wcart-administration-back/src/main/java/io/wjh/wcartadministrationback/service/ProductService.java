package io.wjh.wcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.ProductCreateDTO;
import io.wjh.wcartadministrationback.dto.in.ProductSearchInDTO;
import io.wjh.wcartadministrationback.dto.in.ProductUpdateDTO;
import io.wjh.wcartadministrationback.dto.out.ProductListOutDTO;
import io.wjh.wcartadministrationback.dto.out.ProductShowOutDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    Integer create(ProductCreateDTO productCreateDTO);

    //修改数据
    void update(ProductUpdateDTO productUpdateDTO);

    void delete(Integer administratorId);

    void batchDelete(List<Integer> productIds);

    Page<ProductListOutDTO> search(Integer pageNum, ProductSearchInDTO productSearchInDTO);

    ProductShowOutDTO getById(Integer productId);
}
