package com.xyz.wxpay.pojo.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("支付入参")
public class PayQo {

    @ApiModelProperty("消耗人名币数量(单位（分）)")
    private Integer costCount;

    @ApiModelProperty("支付终端 100:微信公众号  110：内嵌h5调用 120:微信pc端扫码 130:支付宝pc端扫码")
    private Integer type;

    @ApiModelProperty("订单描述")
    private String msg;

    private String orderNo;

    @ApiModelProperty("微信公众账号")
    private String appID;

    @ApiModelProperty("商户号")
    private String mchID;

    @ApiModelProperty("API密钥")
    private String key;

    @ApiModelProperty("回调url")
    private String notifyUrl;

    @ApiModelProperty("JSAPI，NATIVE，APP，,H5支付固定传MWEB")
    private String apiType;

    @ApiModelProperty("商户APPSECRET")
    private String secret;

    @ApiModelProperty("用户code")
    private String code;
}
