package io.wjh.wcartstoreback.controller;

import io.wjh.wcartstoreback.out.QueryResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

   @GetMapping("/get/{page}/{size}")
   public QueryResult findList(@PathVariable("page") int page, @PathVariable("size") int size){
        return null;
   }
}
