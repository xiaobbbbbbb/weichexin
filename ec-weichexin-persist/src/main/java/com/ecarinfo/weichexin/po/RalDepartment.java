package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class RalDepartment implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer depId;//部门id
	private String name;//部门名称
	private Integer isLeaf;
	private Integer parentId;//部门父id
	private Integer orders;//排序
	private String message;//描述

    public Integer getDepId () {
        return depId;
    }

    public void setDepId (Integer depId) {
        this.depId = depId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Integer getIsLeaf () {
        return isLeaf;
    }

    public void setIsLeaf (Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Integer getParentId () {
        return parentId;
    }

    public void setParentId (Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrders () {
        return orders;
    }

    public void setOrders (Integer orders) {
        this.orders = orders;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }
}