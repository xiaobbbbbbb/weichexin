package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class BackgroundUser implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private String loginName;//登陆名称
	private String password;//登陆密码
	private String name;//姓名
	private String email;//emial
	private Integer roleId;//角色，预留的
	private Date addTime;//添加时间
	private Long orgId;//4s店id
	private String orgCode;//4s店编码
	private Integer groupId;//集团ID
	private String groupCode;//集团编码
	private Integer isAway;//是否启用，0为启用
	private Integer delFlag;//是否删除，删除为1
	private String mark;
	private Integer isReserveHandler = 0;//是否预约处理人员

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
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

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public Integer getRoleId () {
        return roleId;
    }

    public void setRoleId (Integer roleId) {
        this.roleId = roleId;
    }

    public Date getAddTime () {
        return addTime;
    }

    public void setAddTime (Date addTime) {
        this.addTime = addTime;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getGroupId () {
        return groupId;
    }

    public void setGroupId (Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupCode () {
        return groupCode;
    }

    public void setGroupCode (String groupCode) {
        this.groupCode = groupCode;
    }

    public Integer getIsAway () {
        return isAway;
    }

    public void setIsAway (Integer isAway) {
        this.isAway = isAway;
    }

    public Integer getDelFlag () {
        return delFlag;
    }

    public void setDelFlag (Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getMark () {
        return mark;
    }

    public void setMark (String mark) {
        this.mark = mark;
    }

    public Integer getIsReserveHandler () {
        return isReserveHandler;
    }

    public void setIsReserveHandler (Integer isReserveHandler) {
        this.isReserveHandler = isReserveHandler;
    }
}