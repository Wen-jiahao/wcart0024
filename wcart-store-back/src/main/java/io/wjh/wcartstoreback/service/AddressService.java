package io.wjh.wcartstoreback.service;

import io.wjh.wcartstoreback.po.Address;

public interface AddressService {
    void delete(Integer addressId);

    void update(Address address);

    void create(Address address);
}
