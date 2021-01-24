package com.bdqn.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdqn.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bdqn.sys.vo.UserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-05
 */
public interface UserMapper extends BaseMapper<User> {

    //分页查询用户列表
    IPage<User>findUserListByPage(@Param("page")IPage<User>page, @Param("user")UserVo userVo);

    @Select("select rid FROM sys_role_user WHERE uid = #{uid}")
    Set<Integer> findUserRoleByUserId(int id)throws Exception;
//根据用户编号删除角色数据
@Delete("delete from sys_role_user where uid = #{userid}")
    void deleteUserRoleByUserId(int userId)throws Exception;
    //添加角色权限修改
    @Insert("INSERT into sys_role_user (rid,uid) VALUES(#{rid},#{uid})")
    void insertUserRole(@Param("uid") int userId, @Param("rid") String rid)throws Exception;
}
