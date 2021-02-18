package com.xyz.wxpay.controller;

import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.xyz.wxpay.constants.ResultConstant;
import com.xyz.wxpay.enums.ExchangeStateEnum;
import com.xyz.wxpay.pojo.qo.PayQo;
import com.xyz.wxpay.service.PayOrderService;
import com.xyz.wxpay.service.WeiXinPayService;
import com.xyz.wxpay.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: WeiXinPayController
 * @author: zjl
 * @date: 2021/1/20  13:47
 */


@RestController
@RequestMapping(value = "/pay/wx")
public class WeiXinPayController {

    @Autowired
    private PayOrderService orderService;

    @Autowired
    private WeiXinPayService weiXinPayService;

    //h5支付
    @PostMapping(value = "/create")
    public ResultConstant create(@RequestBody PayQo qo) {
        CommonUtil.parseQo(qo);
        Map<String, String> resultMap = weiXinPayService.create(qo);
        return ResultConstant.ok().dataString(resultMap);
    }

    //二维码支付
    @PostMapping(value = "/create/native")
    public ResultConstant createNative(@RequestBody PayQo qo) {
        Map<String, String> resultMap = weiXinPayService.createNative(qo);
        return ResultConstant.ok().dataString(resultMap);
    }

    //js支付
    @PostMapping(value = "/create/js")
    public ResultConstant createJs(@RequestBody PayQo qo) {
        Map<String, String> resultMap = weiXinPayService.createJs(qo);
        return ResultConstant.ok().dataString(resultMap);
    }


    //h5支付
    @PostMapping(value = "/create/h5")
    public ResultConstant createH5(@RequestBody PayQo qo) {
        Map<String, String> resultMap = weiXinPayService.createH5(qo);
        return ResultConstant.ok().dataString(resultMap);
    }

    /**
     * 查询订单支付状态
     *
     * @param qo 订单号
     * @return 支付状态
     */


    @PostMapping(value = "/queryOrder")
    public String queryOrder(@RequestBody PayQo qo) {
        int x = 0;
        while (true) {
            // 调用查询微信支付订单状态方法
            Map<String, String> map = this.queryPayStatus(qo);
            if (map.isEmpty()) {
                return "支付出错";
            }
            if (map.get("trade_state").equals("SUCCESS")) {
                orderService.updateByOrderNo(qo.getOrderNo(), ExchangeStateEnum.paySuccess, new Date());
                return "支付成功！";
            }
            try {
                // 间隔3秒
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 为了不让程序无休止的运行下去，定义一个 x 变量，当超过 100 次的话我们就退出此程序
            x++;
            if (x >= 100) {
                orderService.updateByOrderNo(qo.getOrderNo(), ExchangeStateEnum.payCancel, new Date());
                return "二维码超时！";
            }
        }
    }

    /**
     * 查询微信支付订单状态
     *
     * @param qo 支付入参
     * @return 支付状态
     */
    private Map<String, String> queryPayStatus(PayQo qo) {
        try {
            // 创建请求参数
            SortedMap<String, String> req = new TreeMap<String, String>();
            req.put("appid", qo.getAppID()); // 公众号ID
            req.put("mch_id", qo.getMchID());   // 商户号
            req.put("out_trade_no", qo.getOrderNo());    // 订单号
            req.put("nonce_str", WXPayUtil.generateNonceStr()); // 随机字符串
            req.put("sign", WXPayUtil.generateSignature(req, qo.getKey(), WXPayConstants.SignType.MD5));

            // 生成要发送的 xml
            String xmlBody = WXPayUtil.generateSignedXml(req, qo.getKey());
            System.err.println(String.format("查询订单支付状态 xml 格式:\n%s", xmlBody));

            // 调用查询订单支付状态 API
            String result = CommonUtil.httpsRequest("https://api.mch.weixin.qq.com/pay/orderquery", "POST", xmlBody);

            // 返回解析后的 map 数据
            Map<String, String> map = WXPayUtil.xmlToMap(result);
            System.out.println(String.format("%s", map));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/wxPayCallBack")
    public String wxPayCallBack(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("回调成功");
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
            Map<String, String> map = WXPayUtil.xmlToMap(result);
            if (map.get("result_code").equalsIgnoreCase("SUCCESS")) {
                //返回成功后修改订单状态
                String outTradeNo = map.get("out_trade_no");
                orderService.updateByOrderNo(outTradeNo, ExchangeStateEnum.paySuccess, new Date());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }
}
