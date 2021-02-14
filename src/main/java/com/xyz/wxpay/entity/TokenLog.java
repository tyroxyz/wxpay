package com.xyz.wxpay.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 金币明细表
 * </p>
 *
 * @author xyz
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("token_log")
public class TokenLog extends Model<TokenLog> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 金币账户ID
     */
    @TableField("token_id")
    private Long tokenId;

    /**
     * 用户ID
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 用户名
     */
    @TableField("account_name")
    private String accountName;

    /**
     * 用户姓名
     */
    @TableField("account_full_name")
    private String accountFullName;

    /**
     * 平台订单号  新增token_log是生成
     */
    @TableField("order_id")
    private String orderId;

    /**
     * 第三方订单号 如果是微信，支付宝 第三方商城 等需要填写
     */
    @TableField("third_order_id")
    private Long thirdOrderId;

    /**
     * 变动类型 -1减 ，1加
     */
    @TableField("change_type")
    private Integer changeType;

    /**
     * 变更前数量
     */
    @TableField("before_count")
    private Integer beforeCount;

    /**
     * 本次变更数量 + 加 -减
     */
    @TableField("change_count")
    private Integer changeCount;

    /**
     * 变更后数量
     */
    @TableField("after_count")
    private Integer afterCount;

    /**
     * 变更类型 100:积分兑换  110:微信充值 120:支付宝充值 180:帖子被打赏 181:回帖被打赏 182:作品被打赏   200:项目报名 210:课程报名 280:打赏帖子 281:打赏回帖 282:打赏作品  300:购买第三方产品
     */
    @TableField("type")
    private Integer type;

    /**
     * 兑换金币 消耗积分或人名币数量 
     */
    @TableField("cost_count")
    private Integer costCount;

    /**
     * 业务 类型 消费购买仅在部分type下有效
     */
    @TableField("relation_type")
    private Integer relationType;

    /**
     * 业务 名 考试名 或项目名
     */
    @TableField("relation_name")
    private String relationName;

    /**
     * 业务 icoc
     */
    @TableField("relation_icon_url")
    private String relationIconUrl;

    /**
     * 业务ID 消费购买
     */
    @TableField("relation_id")
    private Long relationId;

    /**
     * 订单 提示
     */
    @TableField("msg")
    private String msg;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    @TableField("company_id")
    private Long companyId;

    @TableField("site_id")
    private Long siteId;

    @TableField("org_id")
    private Long orgId;

    @TableField("created_at")
    private LocalDateTime createdAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
