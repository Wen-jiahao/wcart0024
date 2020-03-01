package io.wjh.wcartadministrationback.service.impl;

import io.wjh.wcartadministrationback.dao.AdministratorMapper;
import io.wjh.wcartadministrationback.po.Administrator;
import io.wjh.wcartadministrationback.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;
    @Override
    public Administrator getByUsername(String username) {
        return administratorMapper.getByUsername(username);
    }


    @Override
    public Administrator getById(Integer administartorId) {
        return administratorMapper.selectByPrimaryKey(administartorId);
    }
}
