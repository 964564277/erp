package com.bdqn.sys.service;

import com.bdqn.sys.entity.Dept;
import com.bdqn.sys.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-06
 */
public interface PermissionService extends IService<Permission> {

List<Integer>findRolePermissionByRoleId(int roleId);
}
