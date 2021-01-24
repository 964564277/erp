package com.bdqn.sys.service.impl;

import com.bdqn.sys.entity.Role;
import com.bdqn.sys.dao.RoleMapper;
import com.bdqn.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
        @Resource
        private RoleMapper roleMapper;


    @Override
    public boolean removeById(Serializable id) {
        //删除2张关系表的数据，分别是角色用户，和角色权限
        roleMapper.deleteRoleUserByRoleId(id);
        roleMapper.deleteRolePermissionByRoleId(id);
        return super.removeById(id);
    }

    @Override
    public boolean saveRolePermission(int rid, String ids) {
        try {
            //先删除原有的权限
            roleMapper.deleteRolePermissionByRoleId(rid);
            //再添加新的权限
        String[] pids=ids.split(",");
            //循环遍历有什么权限，存之
            for(int i = 0; i<pids.length;i++){
                //调用保存角色权限的方法
                roleMapper.insertRolePermission(rid,pids[i]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Set<Integer> findRolePermissionByRoleId(Integer roleId) throws Exception {
        return roleMapper.findRolePermissionByRoleId(roleId);
    }
}
