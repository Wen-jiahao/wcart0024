package io.wjh.wcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.CustomerSearchInDTO;
import io.wjh.wcartadministrationback.dto.in.CustomerSetStatusInDTO;
import io.wjh.wcartadministrationback.dto.out.CustomerListOutDTO;
import io.wjh.wcartadministrationback.po.Customer;

public interface CustomerService {

    Page<CustomerListOutDTO> search(Integer pageNum);

    Page<Customer> searchCustomer(Integer pageNum, CustomerSearchInDTO customerSearchInDTO);

    Customer getById(Integer customerId);

    void disable(CustomerSetStatusInDTO customerSetStatusInDTO);
}
