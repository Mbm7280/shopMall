package com.mall.component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * @Author maiBangMin
 * @Description [订单超时取消并解锁库存的定时器]
 * @Date 10:05 上午 2020/11/29
 * @Version 1.0
 **/
@Component
public class OrderTimeOutCancelTask {

    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder() {
        // TODO: 2019/5/3 此处应调用取消订单的方法，具体查看mall项目源码
        // 这样会导致订单不能及时解锁，最大会有10分钟的延迟，题主可以考虑下使用rabbitMQ的死信队列，可以定向解决订单失效的问题
        LOGGER.info("取消订单，并根据sku编号释放锁定库存");
    }

}
