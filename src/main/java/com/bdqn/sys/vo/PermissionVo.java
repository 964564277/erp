package com.bdqn.sys.vo;

import com.bdqn.sys.entity.Permission;

public class PermissionVo extends Permission {
    private Integer page;//当前页码
    private Integer limit;//每页显示的数量

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public PermissionVo(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

    public PermissionVo() {
    }
}
