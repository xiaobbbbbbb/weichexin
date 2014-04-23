package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class RalRole implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer roleId;//角色id
	private String name;//角色名称
	private String message;// 角色描述

    public Integer getRoleId () {
        return roleId;
    }

    public void setRoleId (Integer roleId) {
        this.roleId = roleId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }
}