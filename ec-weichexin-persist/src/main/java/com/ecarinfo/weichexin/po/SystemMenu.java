package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class SystemMenu implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;//主键
	private Integer type;//菜单类型（1快捷菜单）
	private Long userId;//用户id
	private String resourcesIds;//菜单资源id

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Integer getType () {
        return type;
    }

    public void setType (Integer type) {
        this.type = type;
    }

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    public String getResourcesIds () {
        return resourcesIds;
    }

    public void setResourcesIds (String resourcesIds) {
        this.resourcesIds = resourcesIds;
    }
}