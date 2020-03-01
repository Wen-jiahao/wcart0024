package io.wjh.wcartadministrationback.service;

import io.wjh.wcartadministrationback.po.Administrator;

public interface AdministratorService {
    Administrator getByUsername(String username);

    Administrator getById(Integer administartorId);
}
