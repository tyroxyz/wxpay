package com.xyz.wxpay.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz.wxpay.entity.PayOrder;
import com.xyz.wxpay.mapper.PayOrderMapper;
import com.xyz.wxpay.service.PayOrderService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xyz
 * @since 2021-01-28
 */
@Service
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {

    @Override
    public void updateByOrderNo(String outTradeNo, Integer payState, Date date) {
        QueryWrapper<PayOrder> qw = new QueryWrapper<>();
        qw.eq("order_no", outTradeNo);
        PayOrder payOrder = baseMapper.selectOne(qw);
        payOrder.setState(payState);
        payOrder.setPaidAt(date);
        baseMapper.updateById(payOrder);
    }
}
