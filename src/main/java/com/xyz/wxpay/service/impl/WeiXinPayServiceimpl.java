package com.xyz.wxpay.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.xyz.wxpay.config.MyConfig;
import com.xyz.wxpay.entity.ThirdPayConfig;
import com.xyz.wxpay.enums.ExchangeTypeEnum;
import com.xyz.wxpay.pojo.qo.WechatPayQO;
import com.xyz.wxpay.service.ThirdPayConfigService;
import com.xyz.wxpay.service.WeiXinPayService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WeiXinPayServiceimpl
 * @author: zjl
 * @date: 2021/1/21  9:29
 */
@Service
public class WeiXinPayServiceimpl implements WeiXinPayService {

    @Autowired
    private ThirdPayConfigService thirdPayConfigService;

    @Override
    public Map<String, String> create(WechatPayQO qo) {
        try {
            ThirdPayConfig thirdPayConfig = thirdPayConfigService.getConfigByAppId(qo.getAppID());
            MyConfig config = new MyConfig(qo.getAppID(), thirdPayConfig.getMchId(), thirdPayConfig.getApiKey());
            WXPay wxPay = new WXPay(config);
            Map<String, String> map = new HashMap<>(16);
            map.put("notify_url", qo.getNotifyUrl()); // 异步通知地址
            map.put("body", qo.getMsg());    //商品描述
            map.put("out_trade_no", qo.getOrderNo());      //商户订单号
            map.put("total_fee", String.valueOf((int) (qo.getCostCount()))); //标价金额,单位为分
            map.put("spbill_create_ip", "127.0.0.1");    //终端IP
            map.put("trade_type", ExchangeTypeEnum.tradeTypeMap.get(qo.getType()));    //交易类型，JSAPI -JSAPI支付,NATIVE -Native支付,APP -APP支付

            if (qo.getType() == ExchangeTypeEnum.jsapi.intValue()) {
                String openid = qo.getOpenid();
                if (StringUtils.isEmpty(openid)) {
                    System.out.println("缺少参数openid");
                    throw new RuntimeException("缺少参数openid");
                }
                map.put("openid", openid);
            } else if (qo.getType() == ExchangeTypeEnum.h5api.intValue()) {
                //h5跳转设置wapurl供跳转
                map.put("scene_info", "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"" +
                        qo.getWapUrl() + "\",\"wap_name\": \"" + qo.getMsg() + "\"}}");
            } else {
                //二维码设置过期时间15分钟
                Long expireTime = 15*60*1000L;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date afterDate = new Date(new Date().getTime() + expireTime);
                map.put("time_expire", sdf.format(afterDate));
            }
            Map<String, String> response = wxPay.unifiedOrder(map);
            if (!response.isEmpty() && response.get("result_code").equals("SUCCESS")) {
                if (qo.getType() == ExchangeTypeEnum.h5api.intValue() || qo.getType() == ExchangeTypeEnum.nativeapi.intValue()) { //扫码支付和h5不需要二次签名
                    response.put("out_trade_no", qo.getOrderNo());
                    if (null != response.get("mweb_url")) { //h5回调页面 回调链接需要urlencode处理 官网文档：https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=15_4
                        response.put("mweb_url", response.get("mweb_url") + "&redirect_url=" + java.net.URLEncoder.encode(qo.getWapUrl(), "GBK"));
                    }
                    return response;
                }
                //下单成功后 二次签名
                response.put("out_trade_no", qo.getOrderNo());
                //二次签名map
                Map<String, String> dualSignature = new HashMap<>();
                dualSignature.put("appId", qo.getAppID());

                String packag = "prepay_id=" + response.get("prepay_id");
                String timeStamp = String.valueOf(DateUtil.currentSeconds());
                dualSignature.put("package", packag);
                dualSignature.put("signType", "MD5");
                dualSignature.put("nonceStr", response.get("nonce_str"));
                dualSignature.put("timeStamp", timeStamp);

                //签名
                String sign = WXPayUtil.generateSignature(dualSignature, thirdPayConfig.getApiKey());
                response.put("sign", sign);
                response.put("firstSign", response.get("sign"));
                response.put("timeStamp", timeStamp);
                return response;
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
