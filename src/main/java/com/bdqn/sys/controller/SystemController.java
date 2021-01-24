package com.bdqn.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sys")
public class SystemController {

    /**
     * 去到后台首页
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(){
        return "system/home/index";
    }

    /**
     * 去到首页工作台页面
     * @return
     */
    @RequestMapping("/toDesktopManager")
    public String toDesktopManager(){
        return "system/home/desktopManager";
    }

    //去日志管理页面
    @RequestMapping("/toLogManager")
    public String toLogManager() {
        return "system/log/logManager";
    }

    //去公告管理页面
    @RequestMapping("/toNoticeManager")
    public String toNoticeManager() {
        return "system/notice/noticeManager";
    }

    //去部门管理页面
    @RequestMapping("/toDeptManager")
    public String toDeptManager() {
        return "system/dept/deptManager";
    }
    //去左边树节点
    @RequestMapping("/toDeptLeft")
    public String toDeptLeft() {
        return "system/dept/left";
    }
    //去右边页面
    @RequestMapping("/toDeptRight")
    public String toDeptRight() {
        return "system/dept/right";
    }


    //去菜单管理页面
    @RequestMapping("/toMenuManager")
    public String toMenuManager() {
        return "system/menu/menuManager";
    }
    //去左边树节点
    @RequestMapping("/toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/left";
    }
    //去右边页面
    @RequestMapping("/toMenuRight")
    public String toMenuRight() {
        return "system/menu/right";
    }


    //去权限管理页面
    @RequestMapping("/toPermissionManager")
    public String toPermissionManager() {
        return "system/permission/permissionManager";
    }
    //去左边树节点
    @RequestMapping("/toPermissionLeft")
    public String toPermissionLeft() {
        return "system/permission/left";
    }
    //去右边页面
    @RequestMapping("/toPermissionRight")
    public String toPermissionRight() {
        return "system/permission/right";
    }


    /**
     * 跳转到用户管理页面
     * @return
     */
    @RequestMapping("/toUserManager")
    public String toUserManager() {
        return "system/user/userManager";
    }
    /**
     * 跳转到用户管理页面-left
     * @return
     */
    @RequestMapping("/toUserLeft")
    public String toUserLeft() {
        return "system/user/left";
    }
    /**
     * 跳转到用户管理页面-right
     * @return
     */
    @RequestMapping("/toUserRight")
    public String toUserRight() {
        return "system/user/right";
    }

    //去角色管理页面
    @RequestMapping("/toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }






}
