package com.bdqn.bus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 惜年画
 * @since 2021-01-15
 */
@TableName("bus_provider")
public class Provider implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 供应商编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商名称
     */
    private String providername;

    /**
     * 供应商邮编
     */
    private String zip;

    /**
     * 供应商地址
     */
    private String address;

    /**
     * 供应商公司联系电话
     */
    private String telephone;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系人手机
     */
    private String phone;

    /**
     * 开户银行
     */
    private String bank;

    /**
     * 银行账号
     */
    private String account;

    /**
     * 供应商邮箱
     */
    private String email;

    /**
     * 供应商传真
     */
    private String fax;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "Provider{" +
        "id=" + id +
        ", providername=" + providername +
        ", zip=" + zip +
        ", address=" + address +
        ", telephone=" + telephone +
        ", linkman=" + linkman +
        ", phone=" + phone +
        ", bank=" + bank +
        ", account=" + account +
        ", email=" + email +
        ", fax=" + fax +
        "}";
    }
}
