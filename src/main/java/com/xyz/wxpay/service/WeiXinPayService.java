package com.xyz.wxpay.service;

import com.xyz.wxpay.pojo.qo.WechatPayQO;

import java.util.Map;

/**
 * @ClassName: WeiXinPayService
 * @author: zjl
 * @date: 2021/1/21  9:28
 */
public interface WeiXinPayService {
    Map<String, String> create(WechatPayQO qo);
}
