package com.xyz.wxpay.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ExchangeTypeEnum
 * @author: zjl
 * @date: 2021/1/28  10:40
 */
public class ExchangeTypeEnum {
    public static Integer jsapi = 100;//微信公众号
    public static Integer h5api = 110;//内嵌h5调用
    public static Integer nativeapi = 120;//微信pc端扫码
    public static Integer alipay = 130; //支付宝pc端扫码

    public final static Map<Integer, String> typeMap = new HashMap<Integer, String>() {
        {
            put(jsapi, "微信公众号");
            put(h5api, "内嵌h5调用");
            put(nativeapi, "微信pc端扫码");
            put(alipay, "支付宝pc端扫码");
        }
    };

    public final static Map<Integer, String> tradeTypeMap = new HashMap<Integer, String>() {
        {
            put(jsapi, "JSAPI");
            put(h5api, "内嵌h5调用");
            put(nativeapi, "NATIVE");
            put(alipay, "支付宝pc端扫码");
        }
    };


}
