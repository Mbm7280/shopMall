package com.mall.service.impl;


import com.mall.dao.EsProductDao;
import com.mall.nosql.elasticsearch.document.EsProduct;
import com.mall.nosql.elasticsearch.repository.EsProductRepository;
import com.mall.service.EsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * @Author maiBangMin
 * @Description [商品搜索管理业务层实现类]
 * @Date 11:23 上午 2020/11/29
 * @Version 1.0
 **/
@Service
public class EsProductServiceImpl  implements EsProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);

    @Autowired
    private EsProductDao productDao;
    @Autowired
    private EsProductRepository productRepository;

    /*
     * @Author maiBangMin
     * @Description 从数据库中导入所有商品到ES
     * @Date 11:28 上午 2020/11/29
     * @Param []
     * @return int
     **/
    @Override
    public int importAll() {
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    /*
     * @Author maiBangMin
     * @Description 根据id删除商品
     * @Date 11:29 上午 2020/11/29
     * @Param [id]
     * @return void
     **/
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    /*
     * @Author maiBangMin
     * @Description 根据id创建商品
     * @Date 11:29 上午 2020/11/29
     * @Param [id]
     * @return com.mall.nosql.elasticsearch.document.EsProduct
     **/
    @Override
    public EsProduct create(Long id) {
        EsProduct result = null;
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            result = productRepository.save(esProduct);
        }
        return result;
    }

    /*
     * @Author maiBangMin
     * @Description 批量删除商品
     * @Date 11:29 上午 2020/11/29
     * @Param [ids]
     * @return void
     **/
    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    /*
     * @Author maiBangMin
     * @Description 根据关键字搜索名称或者副标题
     * @Date 11:32 上午 2020/11/29
     * @Param [keyword, pageNum, pageSize]
     * @return org.springframework.data.domain.Page<com.mall.nosql.elasticsearch.document.EsProduct>
     **/
    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
