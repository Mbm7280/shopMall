package com.mall.service;


import com.mall.mbg.model.UmsAdmin;
import com.mall.mbg.model.UmsPermission;

import java.util.List;

/*
 * @Author maiBangMin
 * @Description [后台管理员接口]
 * @Date 9:50 下午 2020/11/28
 * @Version 1.0
 **/
public interface UmsAdminService {

    /*
     * @Author maiBangMin
     * @Description [根据用户名获取后台管理员]
     * @Date 9:50 下午
     * @Version 1.0
     **/
    UmsAdmin getAdminByUsername(String username);

    /*
     * @Author maiBangMin
     * @Description [注册功能]
     * @Date 9:51 下午
     * @Version 1.0
     **/
    UmsAdmin register(UmsAdmin umsAdminParam);

    /*
     * @Author maiBangMin
     * @Description [登录功能]
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     * @Date 9:51 下午
     * @Version 1.0
     **/
    String login(String username, String password);

    /*
     * @Author maiBangMin
     * @Description [获取用户所有权限（包括角色权限和+-权限）]
     * @Date 9:51 下午
     * @Version 1.0
     **/
    List<UmsPermission> getPermissionList(Long adminId);

}
