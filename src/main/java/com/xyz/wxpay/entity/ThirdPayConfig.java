package com.xyz.wxpay.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName: ThirdPayConfig
 * @author: zjl
 * @date: 2021/2/23  9:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("third_pay_config")
public class ThirdPayConfig {
    private Long id;

    @ApiModelProperty(value = "应用ID")
    @TableField("app_id")
    private String appId;

    @ApiModelProperty(value = "商户号")
    @TableField("mch_id")
    private String mchId;

    @ApiModelProperty(value = "私钥")
    @TableField("api_key")
    private String apiKey;

    @ApiModelProperty(value = "1:删除 0:未删除")
    private Boolean deleted;

    @TableField("created_at")
    private Date createdAt;

    @TableField("updated_at")
    private Date updatedAt;
}
