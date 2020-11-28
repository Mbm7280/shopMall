package com.mall.controller;


import com.mall.common.api.CommonPage;
import com.mall.common.api.CommonResult;
import com.mall.mbg.model.PmsBrand;
import com.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @Author maiBangMin
 * @Description [品牌管理控制层]
 * @Date 4:34 下午 2020/11/28
 * @Version 1.0
 **/
@Controller
@RequestMapping("/brand")
@Api(tags = "PmsBrandController", description = "商品品牌管理")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    /*
     * @Author maiBangMin
     * @Description 查询所有的商品
     * @Date 4:37 下午 2020/11/28
     * @Param []
     * @return com.mall.common.api.CommonResult<java.util.List<com.mall.mbg.model.PmsBrand>>
     **/
    @GetMapping("/listAll")
    @ResponseBody
    @ApiOperation("获取所有品牌列表")
    public CommonResult<List<PmsBrand>> listAllBrand(){
        return CommonResult.success(brandService.listAllBrand());
    }

    /*
     * @Author maiBangMin
     * @Description 分页展示
     * @Date 4:42 下午 2020/11/28
     * @Param [pageNum, pageSize]
     * @return com.mall.common.api.CommonResult<com.mall.common.api.CommonPage<com.mall.mbg.model.PmsBrand>>
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询品牌列表")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    /*
     * @Author maiBangMin
     * @Description 查询具体的品牌信息
     * @Date 4:41 下午 2020/11/28
     * @Param [id]
     * @return com.mall.common.api.CommonResult<com.mall.mbg.model.PmsBrand>
     **/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("获取指定id的品牌详情")
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getBrand(id));
    }

    /*
     * @Author maiBangMin
     * @Description 创建品牌
     * @Date 4:38 下午 2020/11/28
     * @Param [pmsBrand]
     * @return com.mall.common.api.CommonResult
     **/
    @PostMapping
    @ResponseBody
    @ApiOperation("添加品牌")
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = brandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("更新指定id品牌信息")
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
        CommonResult commonResult;
        int count = brandService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }

    /*
     * @Author maiBangMin
     * @Description 删除品牌
     * @Date 4:41 下午 2020/11/28
     * @Param [id]
     * @return com.mall.common.api.CommonResult
     **/
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("删除指定id的品牌")
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

}
