package io.wjh.wcartstoreback.service;

import com.github.pagehelper.Page;
import io.wjh.wcartstoreback.dto.out.ProductListOutDTO;
import io.wjh.wcartstoreback.dto.out.ProductShowOutDTO;

public interface ProductService {
    Page<ProductListOutDTO> search(Integer pageNum);

    ProductShowOutDTO getById(Integer productId);
}
