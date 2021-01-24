package com.bdqn.sys.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.sys.entity.User;
import com.bdqn.sys.entity.Log;
import com.bdqn.sys.service.LogService;
import com.bdqn.sys.service.RoleService;
import com.bdqn.sys.service.UserService;
import com.bdqn.bus.common.utils.*;
import com.bdqn.sys.vo.UserVo;
import com.bdqn.sys.vo.LoginUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-05
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Resource
    private RoleService roleService;

    @Resource
    private LogService logService;
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public JSONResult login(String loginname, String pwd, HttpServletRequest request){
        //获取当前登录主体对象
        Subject subject = SecurityUtils.getSubject();
        //创建令牌对象
        UsernamePasswordToken token = new UsernamePasswordToken(loginname,pwd);
        try {
            //登录
            subject.login(token);
            //获取当前登录对象
            LoginUserVo loginUserVo =(LoginUserVo) subject.getPrincipal();
            //保存session
            request.getSession().setAttribute(SystemConstant.LOGINUSER,loginUserVo.getUser());

            //记录日志
            //内容，操作类型，登录人，登录人id，IP地址，操作时间
            Log log = new Log( "用户登录",SystemConstant.LOGIN_ACTION,
                    loginname+"-"+loginUserVo.getUser().getName(),
                    loginUserVo.getUser().getId(),request.getRemoteAddr(),new Date());
            logService.save(log);
            //返回结果


            //登录成功
            return SystemConstant.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();

        }
        //登录失败，用户名或密码错误
        return SystemConstant.LOGIN_ERROR_PASS;
    }

    @RequestMapping("/userlist")
    public DataGridViewResult userlist(UserVo userVo){
        IPage<User> page = new Page<>(userVo.getPage(),userVo.getLimit());
        try {
            IPage<User> userIPage = userService.findUserListByPage(page,userVo);
            return new DataGridViewResult(userIPage.getTotal(),userIPage.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//根据部门编号查询该部门下的员工信息
    @RequestMapping("/loadUserByDeptId")
    public DataGridViewResult loadUserByDeptId(Integer deptId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //只查普通用户
        queryWrapper.eq("type",SystemConstant.NORMAL_USER);
        //根据部门编号查询
        queryWrapper.eq(deptId!=null,"deptid",deptId );
        //调用查询的方法
        List<User>users=userService.list(queryWrapper);
        //返回数据
        return new DataGridViewResult(users);
    }
//验证用户名是否存在
    @RequestMapping("/checkLoginName")
    public String checkLoginName(String loginName){
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname",loginName);
        //获取验证信息
        if (userService.count(queryWrapper)>0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"登录名称已经存在，请重新输入");
        }else {
            map.put(SystemConstant.EXIST,false);
            map.put(SystemConstant.MESSAGE,"登录名称可以使用");
        }
        return JSON.toJSONString(map);
    }

    //添加用户
    @RequestMapping("/addUser")
    public JSONResult addUser(User  user ){
        //设置入职时间
        user.setHiredate(new Date());
        //设置盐
        String salt = UUIDUtil.randomUUID();
        user.setSalt(salt);
        //设置密码
        user.setLoginpwd(PasswordUtil.md5(SystemConstant.DEFAULT_PWD,salt,SystemConstant.HASHITERATIONS));
        //设置用户类型
        user.setType(SystemConstant.NORMAL_USER);
        //调用新增方法
        if (userService.save(user)){
        return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;//添加失败
    }

    //根据用户ID查询用户详细方法
    @RequestMapping("/loadUserById")
    public DataGridViewResult loadUserById(Integer id ) {
        return new DataGridViewResult(userService.getById(id));
    }
    //修改用户
    @RequestMapping("/updateUser")
    public JSONResult updateUser(User  user ){
        //调用修改方法
        if (userService.updateById(user)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;//添加失败
    }

    //删除用户
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        //判断是否成功
        if(userService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;//删除成功
        }
        //删除失败
        return SystemConstant.DELETE_ERROR;
    }
    //重置密码
    @RequestMapping("/resetPwd")
    public JSONResult resetPwd(int id){
        //生成盐值
        String salt = UUIDUtil.randomUUID();
        //创建用户对象
        User user = new User();
        user.setId(id);//用户编号
        user.setSalt(salt);//盐值
        user.setLoginpwd(PasswordUtil.md5(SystemConstant.DEFAULT_PWD,salt,SystemConstant.HASHITERATIONS));//密码
        //判断是否成功
        if(userService.updateById(user)){
            return SystemConstant.RESET_SUCCESS;//修改成功
        }
        //修改失败
        return SystemConstant.RESET_ERROR;
    }
    //初始化角色列表数据
    @RequestMapping("/initRoleByUserId")
    public DataGridViewResult initRoleByUserId(int id ) {
        //查询所有的角色列表
        List<Map<String, Object>> mapList = roleService.listMaps();
        //查询当前用户已经拥有的角色列表
        try {
            Set<Integer>roleIdsList= userService.findUserRoleByUserId(id);
            //循环比较当前拥有的角色，拥有的角色要选中
            for (Map<String, Object>objectMap:mapList) {
                boolean flag=false;
                //取出所有角色的ID
                int roleId=(int)objectMap.get("id");
                //内层循环，循环用户已经有的角色列表
                for (Integer rid:roleIdsList){
                    //比较rid与roleid是否相等，相等意味着该角色是有的，要选中
                    if(rid==roleId){
                        flag=true;//选中
                        break;
                    }
                }
                //将选中的状态放到集合中
                objectMap.put("LAY_CHECKED",flag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回数据
        return new DataGridViewResult(Long.valueOf(mapList.size()),mapList);

    }


    //给用户分配角色
    @RequestMapping("/saveUserRole")
    public JSONResult saveUserRole(int userId,String roleIds){
        try {
            if (userService.saveUserRole(userId,roleIds)){
                return SystemConstant.DISTRIBUTE_SUCCESS;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.DISTRIBUTE_ERROR;

    }
}

