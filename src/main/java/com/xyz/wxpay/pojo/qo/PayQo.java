package com.xyz.wxpay.pojo.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PayQo
 * @author: zjl
 * @date: 2021/1/21  9:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayQo {
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
    /**
     * 平台订单号  新增token_log是生成
     */
    private String orderNo;
    /**
     * 微信公众账号
     */
    public String appID;
    /**
     * 商户号
     */
    public String mchId;
    /**
     * API密钥
     */
    public String key;
    /**
     * 回调url
     */
    public String notifyUrl;
}
