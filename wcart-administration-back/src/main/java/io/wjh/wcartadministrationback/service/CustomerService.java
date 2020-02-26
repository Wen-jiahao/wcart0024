package io.wjh.wcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.out.CustomerListOutDTO;

public interface CustomerService {

    Page<CustomerListOutDTO> search(Integer pageNum);
}
