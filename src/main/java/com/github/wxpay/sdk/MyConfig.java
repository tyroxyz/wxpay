package com.github.wxpay.sdk;

import java.io.InputStream;

/**
 * @ClassName: MyConfig
 * @author: zjl
 * @date: 2021/1/22  15:05
 */
public class MyConfig extends WXPayConfig {

    private String appID;
    private String mchID;
    private String key;
    @Override
    String getAppID() {
        return appID;
    }

    @Override
    String getMchID() {
        return mchID;
    }

    @Override
    String getKey() {
        return key;
    }

    @Override
    InputStream getCertStream() {
        return null;
    }

    public MyConfig(String appID, String mchID, String key) {
        this.appID = appID;
        this.mchID = mchID;
        this.key = key;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo("api.mch.weixin.qq.com",true);
            }
        };
    }
}
