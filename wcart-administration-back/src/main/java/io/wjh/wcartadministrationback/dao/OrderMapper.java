package io.wjh.wcartadministrationback.dao;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.out.OrderListOutDTD;
import io.wjh.wcartadministrationback.po.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


    Page<OrderListOutDTD> searchOrderList(@Param("orderId") Integer orderId,
                                          @Param("createTime") Date createTime,
                                          @Param("endTime")Date endTime,
                                          @Param("status")Byte status,
                                          @Param("totalPrice")Double totalPrice,
                                          @Param("customerName")String customerName);
}