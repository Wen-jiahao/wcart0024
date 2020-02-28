package io.wjh.wcartstoreback.service;

import com.github.pagehelper.Page;
import io.wjh.wcartstoreback.dto.out.ProductListOutDTO;

public interface ProductService {
    Page<ProductListOutDTO> search(Integer pageNum);
}
