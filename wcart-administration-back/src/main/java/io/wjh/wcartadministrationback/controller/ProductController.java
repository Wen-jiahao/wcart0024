package io.wjh.wcartadministrationback.controller;

import io.wjh.wcartadministrationback.dto.in.*;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import io.wjh.wcartadministrationback.dto.out.ProductListOutDTO;
import io.wjh.wcartadministrationback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //商品列表
    @GetMapping("/search")
    public PageOutDTO<ProductListOutDTO> search(@RequestBody ProductSearchInDTO productSearchInDTO){

        return null;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody ProductCreateDTO productCreateDTO){

        return productService.create(productCreateDTO);
    }
    @PostMapping("/update")
    public void update(@RequestBody ProductUpdateDTO productUpdateDTO){
        productService.update(productUpdateDTO);
    }
    @PostMapping("/delete")
    public void delete(@RequestBody Integer administratorId){

    }

}
