package com.xyz.wxpay.service;

import com.xyz.wxpay.entity.PayOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.context.annotation.Primary;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xyz
 * @since 2021-01-28
 */
@Primary
public interface PayOrderService extends IService<PayOrder> {

    void updateByOrderNo(String outTradeNo, Integer payState, Date date);
}
