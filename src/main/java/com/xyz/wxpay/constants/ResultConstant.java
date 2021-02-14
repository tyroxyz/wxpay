package com.xyz.wxpay.constants;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ResultConstant
 * @author: zjl
 * @date: 2021/1/28  14:35
 */
@Data
public class ResultConstant {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    @ApiModelProperty(value = "返回字符数据")
    private Map<String, String> dataString = new HashMap<String, String>();

    //把构造方法私有
    private ResultConstant() {}

    //成功静态方法
    public static ResultConstant ok() {
        ResultConstant r = new ResultConstant();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static ResultConstant error() {
        ResultConstant r = new ResultConstant();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public ResultConstant success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResultConstant message(String message){
        this.setMessage(message);
        return this;
    }

    public ResultConstant code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResultConstant data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ResultConstant data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public ResultConstant dataString(Map<String, String> map){
        this.setDataString(map);
        return this;
    }
}
