package com.xyz.wxpay.pojo.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: OrderQO
 * @author: zjl
 * @date: 2021/1/20  16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQO {
    private Long accountId;
    /**
     * 消耗人名币数量(单位（分）)
     */
    private Integer costCount;
    /**
     * 支付终端 100:微信公众号  110：内嵌h5调用 120:微信pc端扫码 130:支付宝pc端扫码
     */
    private Integer type;
    /**
     * 订单描述
     */
    private String msg;

}
