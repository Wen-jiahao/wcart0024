package io.wjh.wcartstoreback.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.wjh.wcartstoreback.dao.CustomerMapper;
import io.wjh.wcartstoreback.dto.in.CustomerRegisterInDTO;
import io.wjh.wcartstoreback.enumeration.CustomerStatus;
import io.wjh.wcartstoreback.po.Customer;
import io.wjh.wcartstoreback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public Integer register(CustomerRegisterInDTO customerRegisterInDTO) {
        Customer customer = new Customer();
        customer.setUsername(customerRegisterInDTO.getUsername());
        customer.setRealName(customerRegisterInDTO.getRealName());
        customer.setEmail(customerRegisterInDTO.getEmail());
        customer.setEmailVerified(false);
        customer.setMobile(customerRegisterInDTO.getMobile());
        customer.setMobileVerified(false);
        customer.setNewsSubscribed(customerRegisterInDTO.getNewsSubscribed());
        customer.setCreateTime(new Date());
        customer.setStatus((byte) CustomerStatus.Enable.ordinal());
        customer.setRewordPoints(0);

        String password = customerRegisterInDTO.getPassword();
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        customer.setEncryptedPassword(bcryptHashString);

        customerMapper.insertSelective(customer);
        Integer customerId = customer.getCustomerId();
        return customerId;
    }

    @Override
    public Customer getByUsername(String username) {
        return customerMapper.getByUsername(username);
    }

    @Override
    public Customer getById(Integer customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public void update(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public Customer getByEmail(String email) {
        return customerMapper.selectByEmail(email);
    }
}
