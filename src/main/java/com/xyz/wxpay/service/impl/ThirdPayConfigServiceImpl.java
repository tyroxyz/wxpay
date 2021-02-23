package com.xyz.wxpay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz.wxpay.entity.ThirdPayConfig;
import com.xyz.wxpay.mapper.ThirdPayConfigMapper;
import com.xyz.wxpay.service.ThirdPayConfigService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ThirdPayConfigServiceImpl
 * @author: zjl
 * @date: 2021/2/23  9:21
 */
@Service
public class ThirdPayConfigServiceImpl  extends ServiceImpl<ThirdPayConfigMapper, ThirdPayConfig> implements ThirdPayConfigService {
    @Override
    public ThirdPayConfig getConfigByAppId(String appID) {
        QueryWrapper<ThirdPayConfig> qw = new QueryWrapper<>();
        qw.eq("app_id", appID).eq("deleted", false);
        return baseMapper.selectOne(qw);
    }
}
