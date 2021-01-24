package com.bdqn.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.sys.entity.Permission;
import com.bdqn.sys.service.PermissionService;
import com.bdqn.bus.common.utils.DataGridViewResult;
import com.bdqn.bus.common.utils.JSONResult;
import com.bdqn.bus.common.utils.SystemConstant;
import com.bdqn.sys.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author KazuGin
 * @since 2019-12-10
 */
@RestController
@RequestMapping("/sys/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    /****************************权限管理***********************************/

    @RequestMapping("/permissionlist")
    public DataGridViewResult findPermissionlist(PermissionVo permissionVo){
        //创建分页对象
        IPage<Permission> page = new Page<Permission>(permissionVo.getPage(),permissionVo.getLimit());
        //创建条件构造器
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
        //只查询权限，不查询权限
        queryWrapper.eq("type",SystemConstant.TYPE_PERMISSION);//只查询权限
        //权限名称查询
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        //权限编码
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getPercode()),"percode",permissionVo.getPercode());
        //编号
        queryWrapper.eq(permissionVo.getId()!=null,"id",permissionVo.getId()).or().eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        //排序
        queryWrapper.orderByAsc("ordernum");
        //分页查询
        permissionService.page(page,queryWrapper);
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

//添加权限
    @RequestMapping("/addPermission")
    public JSONResult addPermission(Permission permission){

            //设置添加类型
            permission.setType(SystemConstant.TYPE_PERMISSION);
            //设置派排序码
            //permission.setOrdernum(permissionService.getMaxOrderNum()+1);
            //调用新增的方法
            if(permissionService.save(permission)){
                //新增成功
                return SystemConstant.ADD_SUCCESS;
            }
        return SystemConstant.ADD_ERROR;
    }
    //修改权限
    @RequestMapping("/updatePermission")
    public JSONResult updatePermission(Permission permission){
        try {
            //调用修改的方法
            if(permissionService.updateById(permission)){
                //修改成功
                return SystemConstant.UPDATE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.UPDATE_ERROR;
    }


    /**
     * 删除权限
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        //删除成功
        if(permissionService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        //删除失败
        return SystemConstant.DELETE_ERROR;
    }

}