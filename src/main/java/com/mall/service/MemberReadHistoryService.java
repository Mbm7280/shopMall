package com.mall.service;


import com.mall.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/*
 * @Author maiBangMin
 * @Description [会员浏览记录管理业务层接口]
 * @Date 4:25 下午 2020/11/29
 * @Version 1.0
 **/
public interface MemberReadHistoryService {

    /*
     * @Author maiBangMin
     * @Description [生成浏览记录]
     * @Date 4:26 下午
     * @Version 1.0
     **/
    int create(MemberReadHistory memberReadHistory);

    /*
     * @Author maiBangMin
     * @Description [批量删除浏览记录]
     * @Date 4:26 下午
     * @Version 1.0
     **/
    int delete(List<String> ids);

    /*
     * @Author maiBangMin
     * @Description [获取用户浏览历史记录]
     * @Date 4:26 下午
     * @Version 1.0
     **/
    List<MemberReadHistory> list(Long memberId);

}
