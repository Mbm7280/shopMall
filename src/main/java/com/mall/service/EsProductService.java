package com.mall.service;


import com.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/*
 * @Author maiBangMin
 * @Description [商品搜索管理业务层接口]
 * @Date 11:21 上午 2020/11/29
 * @Version 1.0
 **/
public interface EsProductService {

    /*
     * @Author maiBangMin
     * @Description [从数据库中导入所有商品到ES]
     * @Date 11:21 上午
     * @Version 1.0
     **/
    int importAll();

    /*
     * @Author maiBangMin
     * @Description [根据id删除商品]
     * @Date 11:21 上午
     * @Version 1.0
     **/
    void delete(Long id);

    /*
     * @Author maiBangMin
     * @Description [根据id创建商品]
     * @Date 11:21 上午
     * @Version 1.0
     **/
    EsProduct create(Long id);

    /*
     * @Author maiBangMin
     * @Description [批量删除商品]
     * @Date 11:21 上午
     * @Version 1.0
     **/
    void delete(List<Long> ids);

    /*
     * @Author maiBangMin
     * @Description [根据关键字搜索名称或者副标题]
     * @Date 11:21 上午
     * @Version 1.0
     **/
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);

}
