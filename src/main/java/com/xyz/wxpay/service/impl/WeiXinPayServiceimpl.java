package com.xyz.wxpay.service.impl;

import com.github.wxpay.sdk.MyConfig;
import com.github.wxpay.sdk.WXPay;
import com.xyz.wxpay.enums.ExchangeTypeEnum;
import com.xyz.wxpay.pojo.qo.PayQo;
import com.xyz.wxpay.service.WeiXinPayService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WeiXinPayServiceimpl
 * @author: zjl
 * @date: 2021/1/21  9:29
 */
@Service
public class WeiXinPayServiceimpl implements WeiXinPayService {
    @Override
    public Map<String, String> createNative(PayQo qo) {
        try {
            MyConfig config = new MyConfig(qo.getAppID(), qo.getMchId(), qo.getKey());
            WXPay wxPay = new WXPay(config);
            Map<String, String> map = new HashMap<>(16);
            map.put("notify_url", qo.getNotifyUrl()); // 异步通知地址
            map.put("body", qo.getMsg());    //商品描述
            map.put("out_trade_no", qo.getOrderNo());      //商户订单号
            map.put("total_fee", String.valueOf((int)(qo.getCostCount()))); //标价金额,单位为分
            map.put("spbill_create_ip", "127.0.0.1");    //终端IP
            map.put("trade_type", ExchangeTypeEnum.tradeTypeMap.get(ExchangeTypeEnum.nativeapi));    //交易类型，JSAPI -JSAPI支付,NATIVE -Native支付,APP -APP支付

            Map<String, String> response = wxPay.unifiedOrder(map);
            if (response == null || response.size() == 0) {
                throw new RuntimeException("下单失败");
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
