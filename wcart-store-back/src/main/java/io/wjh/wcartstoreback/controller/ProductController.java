package io.wjh.wcartstoreback.controller;

import com.github.pagehelper.Page;
import io.wjh.wcartstoreback.dto.in.ProductSearchInDTO;
import io.wjh.wcartstoreback.dto.out.PageOutDTO;
import io.wjh.wcartstoreback.dto.out.ProductListOutDTO;
import io.wjh.wcartstoreback.dto.out.ProductShowOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @GetMapping("/search")
    public PageOutDTO<ProductListOutDTO> search(@RequestBody ProductSearchInDTO productSearchInDTO, @RequestParam(defaultValue = "1",required = false) Integer pageNum){
        Page<ProductListOutDTO> productListOutDTOS=
        return null;
    }
    @GetMapping("/getById")
    public ProductShowOutDTO getById(@RequestParam Integer productId){
        return null;
    }
}
