package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class FrontRoleResource implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer roleId;
	private Integer resourceId;
	private Integer operations = 0;//操作列表，数字的每一位代表一个操作

    public Integer getRoleId () {
        return roleId;
    }

    public void setRoleId (Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId () {
        return resourceId;
    }

    public void setResourceId (Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getOperations () {
        return operations;
    }

    public void setOperations (Integer operations) {
        this.operations = operations;
    }
}