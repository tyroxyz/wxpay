package com.xyz.wxpay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xyz
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pay_order")
public class PayOrder extends Model<PayOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 平台订单号  新增token_log是生成
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 第三方订单号 如果是微信，支付宝 第三方商城 等需要填写
     */
    @TableField("third_order_id")
    private Long thirdOrderId;

    /**
     * 支付终端 100:微信公众号  110：内嵌h5调用 120:微信pc端扫码 130:支付宝pc端扫码
     */
    @TableField("type")
    private Integer type;

    /**
     * 消耗人名币数量 
     */
    @TableField("cost_count")
    private Integer costCount;

    /**
     * 支付状态（10：待支付 20：支付成功 30：支付失败 40：支付取消）
     */
    @TableField("state")
    private Integer state;

    /**
     * 订单创建时间
     */
    @TableField("created_at")
    private Date createdAt;

    /**
     * 订单支付时间
     */
    @TableField("paid_at")
    private Date paidAt;

    @TableField("company_id")
    private Long companyId;

    @TableField("site_id")
    private Long siteId;

    /**
     * 订单 提示
     */
    @TableField("msg")
    private String msg;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
