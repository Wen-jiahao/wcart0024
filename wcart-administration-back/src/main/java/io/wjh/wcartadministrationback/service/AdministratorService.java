package io.wjh.wcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.AdminisyratorUpdateDTO;
import io.wjh.wcartadministrationback.po.Administrator;

import java.util.List;

public interface AdministratorService {
    Administrator getByUsername(String username);

    Administrator getById(Integer administartorId);

    Integer create (Administrator record);

    void update(AdminisyratorUpdateDTO adminisyratorUpdateDT);

    void delete(Integer administratorId);

    void batchDelete(List<Integer> administratorIds);

    void updateProfile(Administrator administrator);

    Page<Administrator> getList(Integer pageNum);

    void updates(Administrator administrator);

    Administrator getByEmail(String email);
}
