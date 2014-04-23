package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class RalResource implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer resourceId;//资源id
	private String name;//资源名称
	private String url;//资源地址
	private String icon;//图标
	private Integer parentId;//父资源id
	private Integer level;
	private Integer isLeaf;
	private String message;//描述

    public Integer getResourceId () {
        return resourceId;
    }

    public void setResourceId (Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getIcon () {
        return icon;
    }

    public void setIcon (String icon) {
        this.icon = icon;
    }

    public Integer getParentId () {
        return parentId;
    }

    public void setParentId (Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel () {
        return level;
    }

    public void setLevel (Integer level) {
        this.level = level;
    }

    public Integer getIsLeaf () {
        return isLeaf;
    }

    public void setIsLeaf (Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }
}