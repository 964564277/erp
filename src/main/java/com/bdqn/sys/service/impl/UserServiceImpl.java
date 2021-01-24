package com.bdqn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdqn.sys.entity.User;
import com.bdqn.sys.dao.UserMapper;
import com.bdqn.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdqn.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-05
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public User findUserByName(String userName) throws Exception {
        //    创建条件构造器对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        制定查询条件
//        key为数据库表中的列名
        queryWrapper.eq("loginname", userName);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<User> findUserListByPage(IPage<User> page, UserVo userVo) {
        return userMapper.findUserListByPage(page,userVo);
    }

    @Override
    public Set<Integer> findUserRoleByUserId(int id) throws Exception {
        return userMapper.findUserRoleByUserId(id);
    }
//
    @Override
    public boolean saveUserRole(int userId, String roleIds) throws Exception {
        //先删除sys_role_user,表的数据
        try {
            userMapper.deleteUserRoleByUserId(userId);
            //再添加sys_role_user表的数据
            String[] rids = roleIds.split(",");
            //循环添加
            for (int i = 0; i <rids.length ; i++) {
                userMapper.insertUserRole(userId,rids[i]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
