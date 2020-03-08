package io.wjh.wcartadministrationback.dao;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.ReturnSearchInDTO;
import io.wjh.wcartadministrationback.po.Return;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnMapper {
    int deleteByPrimaryKey(Integer returnId);

    int insert(Return record);

    int insertSelective(Return record);

    Return selectByPrimaryKey(Integer returnId);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);

    Page<Return> search(ReturnSearchInDTO returnSearchInDTO);
}