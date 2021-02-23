package com.xyz.wxpay.pojo.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: WechatQueryQO
 * @author: zjl
 * @date: 2021/2/23  11:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("微信支付查询入参")
public class WechatQueryQO {

    @ApiModelProperty("微信公众账号")
    private String appID;

    @ApiModelProperty("平台订单号")
    private String orderNo;
}
