package com.xyz.wxpay.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz.wxpay.entity.TokenLog;
import com.xyz.wxpay.mapper.TokenLogMapper;
import com.xyz.wxpay.service.TokenLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 金币明细表 服务实现类
 * </p>
 *
 * @author xyz
 * @since 2021-01-28
 */
@Service
public class TokenLogServiceImpl extends ServiceImpl<TokenLogMapper, TokenLog> implements TokenLogService {

}
