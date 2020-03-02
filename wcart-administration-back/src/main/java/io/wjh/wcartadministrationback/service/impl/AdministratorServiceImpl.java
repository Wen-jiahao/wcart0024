package io.wjh.wcartadministrationback.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.wjh.wcartadministrationback.dao.AdministratorMapper;
import io.wjh.wcartadministrationback.dto.in.AdminisyratorUpdateDTO;
import io.wjh.wcartadministrationback.po.Administrator;
import io.wjh.wcartadministrationback.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Integer create(Administrator administartor) {
        return administratorMapper.insertSelective(administartor);
    }

    @Override
    public void update(AdminisyratorUpdateDTO adminisyratorUpdateDT) {
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(adminisyratorUpdateDT.getAdministratorId());
        administrator.setRealName(adminisyratorUpdateDT.getRealname());
        administrator.setAvatarUrl(adminisyratorUpdateDT.getAvatarUrl());
         administrator.setStatus(adminisyratorUpdateDT.getStatus());
        administrator.setEmail(adminisyratorUpdateDT.getEmail());
        String password = adminisyratorUpdateDT.getPassword();
        if(password!=null && !password.isEmpty()){
            //密码加密
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            administrator.setEncryptedPassword(bcryptHashString);
        }
        //修改密码

        administratorMapper.updateByPrimaryKeySelective(administrator);
    }

    @Override
    public void delete(Integer administratorId) {
        administratorMapper.deleteByPrimaryKey(administratorId);
    }

    @Override
    public void batchDelete(List<Integer> administratorIds) {
        administratorMapper.batchDelete(administratorIds);
    }
}
