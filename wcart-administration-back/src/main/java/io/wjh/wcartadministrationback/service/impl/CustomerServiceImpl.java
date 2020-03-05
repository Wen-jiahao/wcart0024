package io.wjh.wcartadministrationback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.wjh.wcartadministrationback.dao.CustomerMapper;
import io.wjh.wcartadministrationback.dto.out.CustomerListOutDTO;
import io.wjh.wcartadministrationback.po.Customer;
import io.wjh.wcartadministrationback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Page<CustomerListOutDTO> search(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<CustomerListOutDTO> page=customerMapper.search(pageNum);
        return page;
    }

    @Override
    public Page<Customer> searchCustomer(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<Customer>  page=customerMapper.searchCustomer();
        return page;
    }

    @Override
    public Customer getById(Integer customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }
}
