package com.bdqn.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdqn.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdqn.sys.vo.UserVo;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-05
 */
public interface UserService extends IService<User> {
//根据用户名查询用户信息
    User findUserByName(String userName) throws Exception;
    //分页查询用户列表
    IPage<User> findUserListByPage(IPage<User>page,UserVo userVo);
//根据用户ID查询该用户拥有的角色列表
    Set<Integer> findUserRoleByUserId(int id) throws Exception;
//给用户分配角色
    boolean saveUserRole(int userId, String roleIds) throws Exception;


}
