package com.bdqn.sys.service;

import com.bdqn.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-09
 */
public interface RoleService extends IService<Role> {
//保存角色和权限的关系
    boolean saveRolePermission(int rid, String ids);
//根据角色ID查询该角色拥有的权限菜单ID
    Set<Integer> findRolePermissionByRoleId(Integer roleId)throws  Exception;
}
