package io.wjh.wcartadministrationback.service;

import io.wjh.wcartadministrationback.dto.in.ProductCreateDTO;
import io.wjh.wcartadministrationback.dto.in.ProductUpdateDTO;
import org.springframework.stereotype.Service;


public interface ProductService {

    Integer create(ProductCreateDTO productCreateDTO);

    //修改数据
    void update(ProductUpdateDTO productUpdateDTO);

}
