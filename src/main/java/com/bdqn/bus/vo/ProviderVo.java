package com.bdqn.bus.vo;


import com.bdqn.bus.entity.Provider;

public class ProviderVo extends Provider {
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
}
