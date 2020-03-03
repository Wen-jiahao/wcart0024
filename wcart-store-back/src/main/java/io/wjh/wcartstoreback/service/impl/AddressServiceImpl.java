package io.wjh.wcartstoreback.service.impl;

import io.wjh.wcartstoreback.dao.AddressMapper;
import io.wjh.wcartstoreback.po.Address;
import io.wjh.wcartstoreback.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public void delete(Integer addressId) {
        addressMapper.deleteByPrimaryKey(addressId);
    }

    @Override
    public void update(Address address) {
        addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public void create(Address address) {
        addressMapper.insertSelective(address);
    }
}
