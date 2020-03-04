package io.wjh.wcartstoreback.controller;

import io.wjh.wcartstoreback.dto.in.OrderCheckoutInDTO;
import io.wjh.wcartstoreback.dto.out.OrderListOutDTO;
import io.wjh.wcartstoreback.dto.out.OrderShowOutDTO;
import io.wjh.wcartstoreback.dto.out.PageOutDTO;
import io.wjh.wcartstoreback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/checkout")
    public Long checkout(@RequestBody OrderCheckoutInDTO orderCheckoutInDTO,@RequestAttribute Integer customerId){
        Long orderId=orderService.checkout(orderCheckoutInDTO,customerId);
        return orderId;
    }

    @GetMapping("/getList")
    public PageOutDTO<OrderListOutDTO> getList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                               @RequestAttribute Integer customerId){
        return null;
    }
    @GetMapping("/getById")
    public OrderShowOutDTO getBuId(@RequestParam Long orderId){

        return null;
    }
}
