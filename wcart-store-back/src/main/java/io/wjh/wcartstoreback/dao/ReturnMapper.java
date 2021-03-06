package io.wjh.wcartstoreback.dao;

import com.github.pagehelper.Page;
import io.wjh.wcartstoreback.po.Return;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnMapper {
    int deleteByPrimaryKey(Integer returnId);

    int insert(Return record);

    int insertSelective(Return record);

    Return selectByPrimaryKey(Integer returnId);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);

    Page<Return> selectByCustomerId(Integer customerId);
}