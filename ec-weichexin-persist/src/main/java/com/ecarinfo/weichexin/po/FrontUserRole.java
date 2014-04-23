package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class FrontUserRole implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer userId;
	private Integer roleId;
	private String orgCode;
	private Long orgId;

    public Integer getUserId () {
        return userId;
    }

    public void setUserId (Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId () {
        return roleId;
    }

    public void setRoleId (Integer roleId) {
        this.roleId = roleId;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }
}