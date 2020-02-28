package io.wjh.wcartstoreback.controller;

import com.github.pagehelper.Page;
import io.wjh.wcartstoreback.dto.in.ProductSearchInDTO;
import io.wjh.wcartstoreback.dto.out.PageOutDTO;
import io.wjh.wcartstoreback.dto.out.ProductListOutDTO;
import io.wjh.wcartstoreback.dto.out.ProductShowOutDTO;
import io.wjh.wcartstoreback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/search")
    public PageOutDTO<ProductListOutDTO> search(@RequestBody ProductSearchInDTO productSearchInDTO, @RequestParam(defaultValue = "1",required = false) Integer pageNum){
        Page<ProductListOutDTO> productListOutDTOS=productService.search(pageNum);
        PageOutDTO<ProductListOutDTO> productListOutDTOPageOutDTO = new PageOutDTO<>();
        productListOutDTOPageOutDTO.setList(productListOutDTOS);
        productListOutDTOPageOutDTO.setPageNum(productListOutDTOS.getPageNum());
        productListOutDTOPageOutDTO.setPageSize(productListOutDTOS.getPageSize());
        productListOutDTOPageOutDTO.setTotal(productListOutDTOS.getTotal());
        return null;
    }
    @GetMapping("/getById")
    public ProductShowOutDTO getById(@RequestParam Integer productId){
        return null;
    }
}
