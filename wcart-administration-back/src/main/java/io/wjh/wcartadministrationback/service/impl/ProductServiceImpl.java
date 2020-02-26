package io.wjh.wcartadministrationback.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.wjh.wcartadministrationback.dao.ProductDetailMapper;
import io.wjh.wcartadministrationback.dao.ProductMapper;
import io.wjh.wcartadministrationback.dto.in.ProductCreateDTO;
import io.wjh.wcartadministrationback.dto.in.ProductUpdateDTO;
import io.wjh.wcartadministrationback.dto.out.ProductListOutDTO;
import io.wjh.wcartadministrationback.dto.out.ProductShowOutDTO;
import io.wjh.wcartadministrationback.po.Product;
import io.wjh.wcartadministrationback.po.ProductDetail;
import io.wjh.wcartadministrationback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    //创建商品（添加商品）

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDetailMapper productDetailMapper;

    //添加商品
    @Override
    @Transactional
    public Integer create(ProductCreateDTO productCreateDTO) {
        Product product = new Product();
        product.setProductCode(productCreateDTO.getProductCode());
        product.setProductName(productCreateDTO.getProductName());
        product.setPrice(productCreateDTO.getPrice());
        product.setDiscount(productCreateDTO.getDiscount());
        product.setQuantity(productCreateDTO.getStockQuantity());
        product.setStatus(productCreateDTO.getStatus());
        product.setMainPicUrl(productCreateDTO.getMainPicUrl());
        product.setRewordPoints(productCreateDTO.getRewordPoints());
        product.setSortOrder(productCreateDTO.getSortOrder());
        String description = productCreateDTO.getDescription();
        String productAbstract = description.substring(0, Math.min(100, description.length()));
        product.setProductAbstract(productAbstract);
        productMapper.insertSelective(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setDescription(productCreateDTO.getDescription());
        productDetail.setProductId(product.getProductId());
        /*private List<String> othertPicUrls;*/
        String otherPicUrl = JSON.toJSONString(productCreateDTO.getOthertPicUrls());
        productDetail.setOtherPicUrls(otherPicUrl);
        productDetailMapper.insertSelective(productDetail);
        return product.getProductId();
    }

    @Transactional
    @Override
    public void update(ProductUpdateDTO productUpdateDTO) {
        Product product = new Product();
        product.setProductId(productUpdateDTO.getProductId());
        product.setProductCode(productUpdateDTO.getProductCode());
        product.setProductName(productUpdateDTO.getProductName());
        product.setPrice(productUpdateDTO.getPrice());
        product.setDiscount(productUpdateDTO.getDiscount());
        product.setQuantity(productUpdateDTO.getStockQuantity());
        product.setStatus(productUpdateDTO.getStatus());
        product.setMainPicUrl(productUpdateDTO.getMainPicUrl());
        product.setRewordPoints(productUpdateDTO.getRewordPoints());
        product.setSortOrder(productUpdateDTO.getSortOrder());
        String description = productUpdateDTO.getDescription();
        String productAbstract = description.substring(0, Math.min(100, description.length()));
        product.setProductAbstract(productAbstract);
        productMapper.updateByPrimaryKeySelective(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setDescription(productUpdateDTO.getDescription());
        productDetail.setProductId(product.getProductId());
        /*private List<String> othertPicUrls;*/
        String otherPicUrl = JSON.toJSONString(productUpdateDTO.getOthertPicUrls());
        productDetail.setOtherPicUrls(otherPicUrl);
        productDetailMapper.updateByPrimaryKeySelective(productDetail);

    }

    @Override
    @Transactional
    public void delete(Integer administratorId) {
        productMapper.deleteByPrimaryKey(administratorId);
        productDetailMapper.deleteByPrimaryKey(administratorId);
    }

    @Override
    public void batchDelete(List<Integer> productIds) {
        productMapper.batchDelete(productIds);
        productDetailMapper.batchDelete(productIds);
    }

    @Override
    public Page<ProductListOutDTO> search(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<ProductListOutDTO> page=productMapper.search();
        return page;
    }

    @Override
    public ProductShowOutDTO getById(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        ProductDetail productDetail = productDetailMapper.selectByPrimaryKey(productId);
        ProductShowOutDTO productShowOutDTO = new ProductShowOutDTO();
        productShowOutDTO.setProductId(productId);
        productShowOutDTO.setProductCode(product.getProductCode());
        productShowOutDTO.setProductName(product.getProductName());
        productShowOutDTO.setPrice(product.getPrice());
        productShowOutDTO.setDiscount(product.getDiscount());
        productShowOutDTO.setStatus(product.getStatus());
        productShowOutDTO.setMainPicUrl(product.getMainPicUrl());
        productShowOutDTO.setRewordPoints(product.getRewordPoints());
        productShowOutDTO.setSortOrder(product.getSortOrder());
        productShowOutDTO.setStockQuantity(product.getQuantity());
        productShowOutDTO.setDescription(productDetail.getDescription());
        String otherPicUrlsJson = productDetail.getOtherPicUrls();
        //图片返回list
        List<String> otherPicUrls = JSON.parseArray(otherPicUrlsJson, String.class);
        productShowOutDTO.setOtherPicUrls(otherPicUrls);
        return productShowOutDTO;
    }
}
