package io.wjh.wcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.*;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import io.wjh.wcartadministrationback.dto.out.ProductListOutDTO;
import io.wjh.wcartadministrationback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //商品列表
    @GetMapping("/search")
    public PageOutDTO<ProductListOutDTO> search(ProductSearchInDTO productSearchInDTO,@RequestParam(required = false,defaultValue = "1") Integer pageNum){

        Page<ProductListOutDTO> page= productService.search(pageNum);
        PageOutDTO<ProductListOutDTO> objectPageOutDTO = new PageOutDTO<>();
        objectPageOutDTO.setList(page);
        objectPageOutDTO.setPageNum(page.getPageNum());
        objectPageOutDTO.setPageSize(page.getPageSize());
        objectPageOutDTO.setTotal(page.getTotal());
        return objectPageOutDTO;
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
        productService.delete(administratorId);
    }
    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> productIds){
        productService.batchDelete(productIds);
    }

}
