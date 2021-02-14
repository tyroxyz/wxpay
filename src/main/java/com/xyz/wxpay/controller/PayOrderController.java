package com.xyz.wxpay.controller;

import com.xyz.wxpay.entity.PayOrder;
import com.xyz.wxpay.enums.ExchangeStateEnum;
import com.xyz.wxpay.enums.ExchangeTypeEnum;
import com.xyz.wxpay.pojo.qo.OrderQO;
import com.xyz.wxpay.pojo.vo.OrderVO;
import com.xyz.wxpay.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * @ClassName: OrderController
 * @author: zjl
 * @date: 2021/1/20  14:53
 */


@RestController
@RequestMapping("/order")
public class PayOrderController {

    @Autowired
    private PayOrderService payOrderService;

    @GetMapping("/get")
    public String get(){
        return "123";
    }


    @PostMapping("/save")
    public OrderVO saveOrder(@RequestBody OrderQO qo) {
        PayOrder order = new PayOrder();
        order.setAccountId(qo.getAccountId());
        order.setCostCount(qo.getCostCount());
        order.setCreatedAt(new Date());
        order.setOrderNo(getOutTradeNo());//平台订单号
        order.setMsg(qo.getMsg());
        order.setType(ExchangeTypeEnum.nativeapi);
        order.setState(ExchangeStateEnum.payWait);//未支付
        try {
            boolean b  = payOrderService.save(order);
        } catch (Exception e) {
            System.out.println("Exception:添加订单异常");
            e.printStackTrace();
        }
        OrderVO vo = new OrderVO(qo.getCostCount(), order.getOrderNo());
        return vo;
    }

    /*
     * 生成16位随机订单号
     * @return key
     */


    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        Random r = new Random();
        key = key + r.nextInt();
        key = key.replaceAll("-", "").substring(0, 16);
        return key;
    }
}
