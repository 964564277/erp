package com.bdqn.bus.service.impl;

import com.bdqn.bus.dao.SysLeavebillMapper;
import com.bdqn.bus.entity.SysLeavebill;
import com.bdqn.bus.service.SysLeavebillService;
import com.bdqn.bus.vo.LeavebillVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KazuGin
 * @since 2020-08-26
 */
@Service
public class SysLeavebillServiceImpl extends ServiceImpl<SysLeavebillMapper, SysLeavebill> implements SysLeavebillService {

    @Resource
    private SysLeavebillMapper sysLeavebillMapper;

    @Override
    public IPage<SysLeavebill> findLeaveBillByPage(IPage<SysLeavebill> page, LeavebillVo leavebillVo) throws Exception {
       return sysLeavebillMapper.findLeaveBillByPage(page,leavebillVo);
    }
}
