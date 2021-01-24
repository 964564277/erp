package com.bdqn.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.sys.entity.Permission;
import com.bdqn.sys.entity.Role;
import com.bdqn.sys.service.PermissionService;
import com.bdqn.sys.service.RoleService;
import com.bdqn.bus.common.utils.DataGridViewResult;
import com.bdqn.bus.common.utils.JSONResult;
import com.bdqn.bus.common.utils.SystemConstant;
import com.bdqn.bus.common.utils.TreeNode;
import com.bdqn.sys.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-09
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @RequestMapping("/rolelist")
    public DataGridViewResult roleList(RoleVo roleVo){
        //创建分页对象
        IPage<Role> page = new Page<Role>(roleVo.getPage(),roleVo.getLimit());
        //创建条件构造器
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        //角色编码
        queryWrapper.like(StringUtils.isNoneBlank(roleVo.getRolecode()),"rolecode",roleVo.getRolecode());
        //角色名称
        queryWrapper.like(StringUtils.isNoneBlank(roleVo.getRolename()),"rolename",roleVo.getRolename());
        //排序
        queryWrapper.orderByAsc("id");
        //调用分页查询的方法
        roleService.page(page,queryWrapper);
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());

    }


    @RequestMapping("/addRole")
    public JSONResult addRole(Role role){
        role.setCreatetime(new Date());//创建时间
        //判断是否成功
        if(roleService.save(role)){
            return SystemConstant.ADD_SUCCESS;//添加成功
        }
        //删除失败
        return SystemConstant.ADD_ERROR;
    }
    //修改公告
    @PostMapping("/updateRole")
    public JSONResult updateRole(Role role){
        //判断是否成功
        if(roleService.updateById(role)){
            return SystemConstant.UPDATE_SUCCESS;//修改成功
        }
        //修改失败
        return SystemConstant.UPDATE_ERROR;
    }

    //删除公告
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        //判断是否成功
        if(roleService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;//删除成功
        }
        //删除失败
        return SystemConstant.DELETE_ERROR;
    }
    /**
     * @Description: 初始化权限菜单树
     * @Param: id
     * @Return: DataGridViewResult
     * @Author: Cloudam
     * @Date: 2020/8/23 20:41
     */
    @RequestMapping("/initPermissionByRoleId")
    public DataGridViewResult initPermissionByRoleId(int roleId) throws Exception {
        //创建条件构造器
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        //用一个集合先查询出所有的权限
        List<Permission> permissions = permissionService.list(queryWrapper);   //所有传不传都无所谓
        //用第二个集合保存已有的权限
        List<Permission> currentpermissions = new ArrayList<>();

        //查询当前角色已经有的权限
        //是根据roleId在关系表中进行查询
        List<Integer> currentPermissionIds = permissionService.findRolePermissionByRoleId(roleId);
        //判断当前角色是否有权限id
        if(currentPermissionIds!=null && currentPermissionIds.size()>0){
            //进行条件查询
            queryWrapper.in("id",currentPermissionIds);

            currentpermissions = permissionService.list(queryWrapper);
        }
        //构建树节点集合
        List<TreeNode> treeNodes = new ArrayList<>();
        //外层循环所有的权限数据
        for (Permission p1 : permissions) {
            //定义变量
            String checkArr = "0" ;  //不选中
            //遍历已有的权限，是否匹配
            for (Permission p2 : currentpermissions) {
                if(p1.getId() == p2.getId()){
                    checkArr = "1";
                    break;
                }
            }
            //组装树节点对象
            Boolean spread = (p1.getOpen() == null || p1.getOpen() == 1) ? true:false;
            //将节点对象添加到节点集合
            treeNodes.add(new TreeNode(p1.getId(),p1.getPid(),p1.getTitle(),spread,checkArr));
        }
        return new DataGridViewResult(treeNodes);
    }

    /**
     *
     * @param rid 当前角色ID
     * @param ids   权限id String?  因为权限有一大堆采用String储存
     * @return
     */

    @RequestMapping("/saveRolePermission")
    public JSONResult saveRolePermission(int rid,String ids){
        try {
            if(roleService.saveRolePermission(rid,ids)){
                return SystemConstant.DISTRIBUTE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.DISTRIBUTE_ERROR;

    }



}

