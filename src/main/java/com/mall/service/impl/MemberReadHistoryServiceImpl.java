package com.mall.service.impl;


import com.mall.nosql.mongodb.document.MemberReadHistory;
import com.mall.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.mall.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @Author maiBangMin
 * @Description [会员浏览记录管理业务层实现类]
 * @Date 4:26 下午 2020/11/29
 * @Version 1.0
 **/
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    @Autowired
    private MemberReadHistoryRepository memberReadHistoryRepository;

    /*
     * @Author maiBangMin
     * @Description 生成浏览记录
     * @Date 4:28 下午 2020/11/29
     * @Param [memberReadHistory]
     * @return int
     **/
    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        memberReadHistoryRepository.save(memberReadHistory);
        return 1;
    }

    /*
     * @Author maiBangMin
     * @Description 批量删除浏览记录
     * @Date 4:28 下午 2020/11/29
     * @Param [ids]
     * @return int
     **/
    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList = new ArrayList<>();
        for(String id:ids){
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }
        memberReadHistoryRepository.deleteAll(deleteList);
        return ids.size();
    }

    /*
     * @Author maiBangMin
     * @Description 获取用户浏览历史记录
     * @Date 4:28 下午 2020/11/29
     * @Param [memberId]
     * @return java.util.List<com.mall.nosql.mongodb.document.MemberReadHistory>
     **/
    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}
