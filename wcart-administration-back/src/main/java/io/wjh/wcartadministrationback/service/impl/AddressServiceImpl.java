package io.wjh.wcartadministrationback.service.impl;

import io.wjh.wcartadministrationback.dao.AddressMapper;
import io.wjh.wcartadministrationback.po.Address;
import io.wjh.wcartadministrationback.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Address getById(Integer addressId) {
        return addressMapper.selectByPrimaryKey(addressId);
    }
}
