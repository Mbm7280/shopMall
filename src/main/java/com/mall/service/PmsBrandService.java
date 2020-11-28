package com.mall.service;


import com.mall.mbg.model.PmsBrand;

import java.util.List;

/*
 * @Author maiBangMin
 * @Description [商品管理业务层接口]
 * @Date 4:30 下午 2020/11/28
 * @Version 1.0
 **/
public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);

}
