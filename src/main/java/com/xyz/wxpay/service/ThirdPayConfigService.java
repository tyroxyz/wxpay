package com.xyz.wxpay.service;

import com.xyz.wxpay.entity.ThirdPayConfig;

/**
 * @ClassName: ThirdPayConfigService
 * @author: zjl
 * @date: 2021/2/23  9:21
 */
public interface ThirdPayConfigService {
    ThirdPayConfig getConfigByAppId(String appID);
}
