package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class RalUser implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer userId;//用户id
	private String loginName;//登录名
	private String password;//密码
	private String name;//姓名
	private String phone;//电话
	private String email;//邮件
	private Integer orgId;//组织id
	private Integer depId;//部门id
	private Integer isAway;//账号是否启用，默认为0启用
	private Integer isManager;//是否是管理员
	private Date createDate;//创建日期
	private Integer level;//级别(排序)
	private String message;//备注

    public Integer getUserId () {
        return userId;
    }

    public void setUserId (Integer userId) {
        this.userId = userId;
    }

    public String getLoginName () {
        return loginName;
    }

    public void setLoginName (String loginName) {
        this.loginName = loginName;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public Integer getOrgId () {
        return orgId;
    }

    public void setOrgId (Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getDepId () {
        return depId;
    }

    public void setDepId (Integer depId) {
        this.depId = depId;
    }

    public Integer getIsAway () {
        return isAway;
    }

    public void setIsAway (Integer isAway) {
        this.isAway = isAway;
    }

    public Integer getIsManager () {
        return isManager;
    }

    public void setIsManager (Integer isManager) {
        this.isManager = isManager;
    }

    public Date getCreateDate () {
        return createDate;
    }

    public void setCreateDate (Date createDate) {
        this.createDate = createDate;
    }

    public Integer getLevel () {
        return level;
    }

    public void setLevel (Integer level) {
        this.level = level;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }
}