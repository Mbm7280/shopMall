package com.mall.nosql.repository;


import com.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/*
 * @Author maiBangMin
 * @Description [商品ES操作类]
 * @Date 11:18 上午 2020/11/29
 * @Version 1.0
 **/
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {

    /*
     * @Author maiBangMin
     * @Description [搜索查询]
     * @Date 11:20 上午
     * @param name              商品名称
     * @param subTitle          商品标题
     * @param keywords          商品关键字
     * @param page              分页信息
     * @Version 1.0
     **/
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);


}
