package io.wjh.wcartstoreback.service;

import io.wjh.wcartstoreback.po.Address;

import java.util.List;

public interface AddressService {
    void delete(Integer addressId);

    void update(Address address);

    void create(Address address);

    Address getById(Integer invoiceAddressId);

    List<Address> getByCustomerId(Integer customerId);
}
