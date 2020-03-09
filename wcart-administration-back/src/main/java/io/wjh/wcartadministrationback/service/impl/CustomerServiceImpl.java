package io.wjh.wcartadministrationback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.wjh.wcartadministrationback.dao.CustomerMapper;
import io.wjh.wcartadministrationback.dto.in.CustomerSearchInDTO;
import io.wjh.wcartadministrationback.dto.in.CustomerSetStatusInDTO;
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
    public Page<Customer> searchCustomer(Integer pageNum, CustomerSearchInDTO customerSearchInDTO) {
        PageHelper.startPage(pageNum,10);
        Page<Customer>  page=customerMapper.searchCustomer(customerSearchInDTO.getUsername(),
                customerSearchInDTO.getRealname(),
                customerSearchInDTO.getMobile(),
                customerSearchInDTO.getEmail(),
                customerSearchInDTO.getStatus());
        return page;
    }

    @Override
    public Customer getById(Integer customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public void disable(CustomerSetStatusInDTO customerSetStatusInDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerSetStatusInDTO.getCustomerId());
        customer.setStatus(customerSetStatusInDTO.getStatus());
        customerMapper.updateByPrimaryKeySelective(customer);
    }
}
