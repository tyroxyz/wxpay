package com.xyz.wxpay.service;

import com.xyz.wxpay.pojo.qo.PayQo;

import java.util.Map;

/**
 * @ClassName: WeiXinPayService
 * @author: zjl
 * @date: 2021/1/21  9:28
 */
public interface WeiXinPayService {

    Map<String, String> createNative(PayQo qo);

    Map<String, String> createJs(PayQo qo);

    Map<String, String> createH5(PayQo qo);

    Map<String, String> create(PayQo qo);
}
