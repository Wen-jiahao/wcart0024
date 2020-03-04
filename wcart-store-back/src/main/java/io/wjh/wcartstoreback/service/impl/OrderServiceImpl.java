package io.wjh.wcartstoreback.service.impl;

import com.alibaba.fastjson.JSON;
import io.wjh.wcartstoreback.dao.OrderDetailMapper;
import io.wjh.wcartstoreback.dao.OrderMapper;
import io.wjh.wcartstoreback.dto.in.OrderCheckoutInDTO;
import io.wjh.wcartstoreback.dto.in.OrderProductInDTO;
import io.wjh.wcartstoreback.dto.out.ProductShowOutDTO;
import io.wjh.wcartstoreback.enumeration.OrderStatus;
import io.wjh.wcartstoreback.po.Address;
import io.wjh.wcartstoreback.po.Order;
import io.wjh.wcartstoreback.po.OrderDetail;
import io.wjh.wcartstoreback.po.Product;
import io.wjh.wcartstoreback.service.AddressService;
import io.wjh.wcartstoreback.service.OrderService;
import io.wjh.wcartstoreback.service.ProductService;
import io.wjh.wcartstoreback.vo.orderProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private AddressService addressService;

    @Override
    @Transactional
    public Long checkout(OrderCheckoutInDTO orderCheckoutInDTO,Integer customerId) {
        List<OrderProductInDTO> orderProductsindto = orderCheckoutInDTO.getOrderProducts();
        List<orderProductVo> orderProductVOS  = orderProductsindto.stream().map(OrderProductInDTO -> {
            Product orderProduct = productService.getById(OrderProductInDTO.getProductId());
            orderProductVo orderProductVo = new orderProductVo();
            orderProductVo.setProductId(orderProduct.getProductId());
            orderProductVo.setProductCode(orderProduct.getProductCode());
            orderProductVo.setProductName(orderProduct.getProductName());
            //商品数量
            Integer quantity = OrderProductInDTO.getQuantity();
            orderProductVo.setQuantity(quantity);
            //商品价格
            Double price = orderProduct.getPrice();
            orderProductVo.setUnitprice(price);

            //总金额doble
            orderProductVo.setTotalPrice(price * quantity);
            //商品积分
            Integer rewordPoints = orderProduct.getRewordPoints();
            orderProductVo.setUnitRewordPoints(rewordPoints);

            //总积分
            orderProductVo.setTotalRewordPoints(rewordPoints * quantity);
            return orderProductVo;

        }).collect(Collectors.toList());

        //所要商品的价格
        double allTotalPrice  = orderProductVOS.stream().mapToDouble(p -> p.getTotalPrice()).sum();
        //所有商品的积分
        int allTotalRewordPoints  = orderProductVOS.stream().mapToInt(p -> p.getTotalRewordPoints()).sum();
        //创建订单
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setStatus((byte) OrderStatus.TOPROCESS.ordinal());
        order.setCreateTime(new Date());
        order.setRewordPoints(allTotalRewordPoints);
        order.setTotalPrice(allTotalPrice);
        order.setUpdateTime(new Date());
        //添加订单
        orderMapper.insertSelective(order);
        //添加订单详情
        Long orderId = order.getOrderId();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setComment(orderCheckoutInDTO.getComment());
        Address invoiceAddress  = addressService.getById(orderCheckoutInDTO.getInvoiceAddressId());
        orderDetail.setShipAddress(invoiceAddress .getContent());
        Address shipAddress = addressService.getById(orderCheckoutInDTO.getShipAddressId());
        orderDetail.setInvoiceAddress(shipAddress.getContent());
        orderDetail.setInvoicePrice(allTotalPrice);
        orderDetail.setPayMethod(orderCheckoutInDTO.getShipMethod());
        orderDetail.setShipPrice(5.0);
        orderDetail.setShipMethod(orderCheckoutInDTO.getShipMethod());
        //商品订单json数据JSON.toJSONString(orderProductVOS)
        orderDetail.setOrderProducts(JSON.toJSONString(orderProductVOS));
        orderDetailMapper.insertSelective(orderDetail);
        return orderId;
    }
}
