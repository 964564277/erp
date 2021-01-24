package com.bdqn.sys.dao;

import com.bdqn.sys.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-06
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    @Delete("DELETE FROM sys_role_permission WHERE pid=#{id}")
        void deleteRolePermissionByPid(Serializable id);
    @Select("select pid from sys_role_permission where rid = #{roleId}")
    List<Integer>findRolePermissionByRoleId(int roleId);
}
