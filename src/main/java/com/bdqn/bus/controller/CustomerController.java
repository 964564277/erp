package com.bdqn.bus.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.bus.common.utils.DataGridViewResult;
import com.bdqn.bus.common.utils.JSONResult;
import com.bdqn.bus.common.utils.SystemConstant;
import com.bdqn.bus.entity.Customer;
import com.bdqn.bus.service.CustomerService;
import com.bdqn.bus.vo.CustomerVo;
import com.bdqn.sys.entity.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
@RequestMapping("/bus/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;
@RequestMapping("/customerlist")
 public DataGridViewResult customerlist(CustomerVo customerVo){
    //调用分页查询的方法
    IPage<Customer>page=new Page<Customer>(customerVo.getPage(),customerVo.getLimit());
   //创建条件查询
    QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
    //客户姓名
    queryWrapper.like(StringUtils.isNotBlank(customerVo.getCustomername()),"customername",customerVo.getCustomername());
    //客户姓名
    queryWrapper.like(StringUtils.isNotBlank(customerVo.getTelephone()),"telephone",customerVo.getTelephone());
    //客户姓名
    queryWrapper.like(StringUtils.isNotBlank(customerVo.getLinkman()),"linkman",customerVo.getLinkman());

    customerService.page(page,queryWrapper);
    //返回数据

    return new DataGridViewResult(page.getTotal(),page.getRecords());

 }
//添加客户
 @RequestMapping("/addCustomer")
    public JSONResult addCustomer(Customer customer){
    if (customerService.save(customer)){
        return SystemConstant.ADD_SUCCESS;//成功添加
    }
    return SystemConstant.ADD_ERROR;
 }
    //修改客户
    @RequestMapping("/updateCustomer")
    public JSONResult updateCustomer(Customer customer){
        //判断是否成功
        if(customerService.updateById(customer)){
            return SystemConstant.UPDATE_SUCCESS;//修改成功
        }
        //修改失败
        return SystemConstant.UPDATE_ERROR;
    }

    //删除客户
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        //判断是否成功
        if(customerService.removeById(id)){
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
        if(customerService.removeByIds(Arrays.asList(idsStr))){
            return SystemConstant.DELETE_SUCCESS;//删除成功
        }
        //删除失败
        return SystemConstant.DELETE_ERROR;
    }
}

