package com.xyz.wxpay.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: OrderVO
 * @author: zjl
 * @date: 2021/1/21  9:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {

    /**
     * 消耗人名币数量(单位（分）)
     */
    private Integer costCount;

    /**
     * 16位随机订单号
     */
    private String orderNo;
}
