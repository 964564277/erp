package com.bdqn.bus.dao;

import com.bdqn.bus.entity.SysLeavebill;
import com.bdqn.bus.vo.LeavebillVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author KazuGin
 * @since 2020-08-26
 */
public interface SysLeavebillMapper extends BaseMapper<SysLeavebill> {
    IPage<SysLeavebill> findLeaveBillByPage(@Param("page") IPage<SysLeavebill> page, @Param("leavebill") LeavebillVo leavebillVo) throws Exception;
}
