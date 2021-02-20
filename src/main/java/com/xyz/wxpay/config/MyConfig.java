package com.xyz.wxpay.config;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

/**
 * @ClassName: MyConfig
 * @author: zjl
 * @date: 2021/1/22  15:05
 */
public class MyConfig implements WXPayConfig {

    private String appID;
    private String mchID;
    private String key;


    @Override
    public String getAppID() {
        return appID;
    }

    @Override
    public String getMchID() {
        return mchID;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }

    public MyConfig(String appID, String mchID, String key) {
        this.appID = appID;
        this.mchID = mchID;
        this.key = key;
    }

}
