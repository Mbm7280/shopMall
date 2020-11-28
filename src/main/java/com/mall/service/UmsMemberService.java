package com.mall.service;


import com.mall.common.api.CommonResult;

/*
 * @Author maiBangMin
 * @Description [会员管理接口]
 * @Date 7:51 下午 2020/11/28
 * @Version 1.0
 **/
public interface UmsMemberService {

    /*
     * @Author maiBangMin
     * @Description [生成验证码]
     * @Date 7:52 下午
     * @Version 1.0
     **/
    CommonResult generateAuthCode(String telephone);

   /*
    * @Author maiBangMin
    * @Description [判断验证码和手机号码是否匹配]
    * @Date 7:52 下午
    * @Version 1.0
    **/
    CommonResult verifyAuthCode(String telephone, String authCode);

}
