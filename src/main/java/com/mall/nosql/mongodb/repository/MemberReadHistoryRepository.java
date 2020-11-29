package com.mall.nosql.mongodb.repository;



import com.mall.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 *
 * Created by macro on 2018/8/3.
 */

 /*
  * @Author maiBangMin
  * @Description [会员商品浏览历史Repository]
  * @Date 4:24 下午 2020/11/29
  * @Version 1.0
  **/
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {

    /*
     * @Author maiBangMin
     * @Description [根据会员id按时间倒序获取浏览记录]
     * @Date 4:24 下午 2020/11/29
     * @Version 1.0
     **/
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);

}
