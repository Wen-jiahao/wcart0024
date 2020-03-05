package io.wjh.wcartadministrationback.dao;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.out.CustomerListOutDTO;
import io.wjh.wcartadministrationback.po.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    Page<CustomerListOutDTO> search(Integer pageNum);

    Page<Customer> searchCustomer();
}