package io.wjh.wcartadministrationback.controller;

import io.wjh.wcartadministrationback.dto.in.*;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import io.wjh.wcartadministrationback.dto.out.ProductListOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    //商品列表
    @GetMapping("/list")
    public PageOutDTO<ProductListOutDTO> list(@RequestBody ProductSearchInDTO productSearchInDTO){
        return null;
    }

    @PostMapping("/creeate")
    public Integer create(@RequestBody ProductCreateDTO productCreateDTO){
        return null;
    }
    @PostMapping("/update")
    public void update(@RequestBody ProductUpdateDTO productUpdateDTO){

    }
    @PostMapping("/delete")
    public void delete(@RequestBody Integer administratorId){

    }

}
