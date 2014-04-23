package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class RalRoleResources implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer roleId = 0;//角色id
	private Integer resourcesId = 0;//资源id

    public Integer getRoleId () {
        return roleId;
    }

    public void setRoleId (Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourcesId () {
        return resourcesId;
    }

    public void setResourcesId (Integer resourcesId) {
        this.resourcesId = resourcesId;
    }
}