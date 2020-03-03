package io.wjh.wcartstoreback.service;

import io.wjh.wcartstoreback.dto.in.CustomerRegisterInDTO;

public interface CustomerService {
    Integer register(CustomerRegisterInDTO customerRegisterInDTO);
}
