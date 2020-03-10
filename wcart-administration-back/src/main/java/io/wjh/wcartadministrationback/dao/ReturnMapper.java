package io.wjh.wcartadministrationback.dao;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.ReturnSearchInDTO;
import io.wjh.wcartadministrationback.po.Return;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReturnMapper {
    int deleteByPrimaryKey(Integer returnId);

    int insert(Return record);

    int insertSelective(Return record);

    Return selectByPrimaryKey(Integer returnId);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);

    Page<Return> search(@Param("customerId") Integer customerId,
                        @Param("customerName")String customerName,
                        @Param("orderId")Long orderId,
                        @Param("productCode")String productCode,
                        @Param("productName")String productName,
                        @Param("returnId")Integer returnId,
                        @Param("status") String status,@Param("startTime") Date startTime,
                        @Param("endTime")Date endTime);

    /* Page<Return> search(Integer customerId, String customerName, Long orderId, String productCode, String productName, Integer returnId, String status);
*/
    /*Page<Return> search(ReturnSearchInDTO returnSearchInDTO);*/
}