package com.bdqn.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.bus.common.utils.DataGridViewResult;
import com.bdqn.bus.common.utils.JSONResult;
import com.bdqn.bus.common.utils.SystemConstant;
import com.bdqn.bus.entity.Provider;
import com.bdqn.bus.service.ProviderService;
import com.bdqn.bus.vo.ProviderVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-15
 */
@RestController
@RequestMapping("/bus/provider")
public class ProviderController {
    @Resource
    private ProviderService providerService;
    @RequestMapping("/providerlist")
    public DataGridViewResult providerlist(ProviderVo providerVo){
        //调用分页查询的方法
        IPage<Provider>page=new Page<Provider>(providerVo.getPage(),providerVo.getLimit());
        //创建条件查询
        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
        //客户姓名
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getProvidername()),"providername",providerVo.getProvidername());
        //客户电话
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getTelephone()),"telephone",providerVo.getTelephone());
        //客户联系人
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getLinkman()),"linkman",providerVo.getLinkman());

        providerService.page(page,queryWrapper);
        //返回数据

        return new DataGridViewResult(page.getTotal(),page.getRecords());

    }
    //添加客户
    @RequestMapping("/addProvider")
    public JSONResult addProvider(Provider provider){
        if (providerService.save(provider)){
            return SystemConstant.ADD_SUCCESS;//成功添加
        }
        return SystemConstant.ADD_ERROR;
    }
    //修改客户
    @RequestMapping("/updateProvider")
    public JSONResult updateProvider(Provider provider){
        //判断是否成功
        if(providerService.updateById(provider)){
            return SystemConstant.UPDATE_SUCCESS;//修改成功
        }
        //修改失败
        return SystemConstant.UPDATE_ERROR;
    }

    //删除客户
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        //判断是否成功
        if(providerService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;//删除成功
        }
        //删除失败
        return SystemConstant.DELETE_ERROR;
    }
    //批量删除客户
    @RequestMapping("/batchDelete")
    public JSONResult batchDelete(String ids){
        //降字符串拆分成数组
        String[] idsStr = ids.split(",");
        //判断是否删除成功
        if(providerService.removeByIds(Arrays.asList(idsStr))){
            return SystemConstant.DELETE_SUCCESS;//删除成功
        }
        //删除失败
        return SystemConstant.DELETE_ERROR;
    }
}

