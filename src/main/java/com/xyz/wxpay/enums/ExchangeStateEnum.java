package com.xyz.wxpay.enums;

/**
 * @ClassName: ExchangeStateEnum
 * @author: zjl
 * @date: 2021/1/28  19:48
 */
public class ExchangeStateEnum {
    public static Integer payWait = 10;//待支付
    public static Integer paySuccess = 20;//支付成功
    public static Integer payFail = 30;//支付失败
    public static Integer payCancel = 40; //支付取消
}
