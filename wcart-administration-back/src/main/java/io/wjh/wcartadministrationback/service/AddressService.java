package io.wjh.wcartadministrationback.service;

import io.wjh.wcartadministrationback.po.Address;

import java.util.List;

public interface AddressService {
    Address getById(Integer addressId);

    List<Address> getListByCustomerId(Integer customerId);
}
