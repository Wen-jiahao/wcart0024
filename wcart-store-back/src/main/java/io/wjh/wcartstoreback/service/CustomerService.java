package io.wjh.wcartstoreback.service;

import io.wjh.wcartstoreback.dto.in.CustomerRegisterInDTO;
import io.wjh.wcartstoreback.po.Customer;

public interface CustomerService {
    Integer register(CustomerRegisterInDTO customerRegisterInDTO);

    Customer getByUsername(String username);

    Customer getById(Integer customerId);

    void update(Customer customer);
}
