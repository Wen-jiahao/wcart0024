package io.wjh.wcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.ReturnSearchInDTO;
import io.wjh.wcartadministrationback.dto.in.ReturnUpdateActionInDTO;
import io.wjh.wcartadministrationback.po.Return;

public interface ReturnService {
    Page<Return> search(ReturnSearchInDTO returnSearchInDTO, Integer pageNum);

    Return getById(Integer returnId);

    void updateAction(ReturnUpdateActionInDTO returnUpdateActionInDTO);
}
