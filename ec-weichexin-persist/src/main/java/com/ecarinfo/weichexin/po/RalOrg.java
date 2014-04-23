package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class RalOrg implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer orgId;//组织id
	private String name;//名称
	private Integer parentId;//组织父id
	private Integer level;//级别(排序)

    public Integer getOrgId () {
        return orgId;
    }

    public void setOrgId (Integer orgId) {
        this.orgId = orgId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
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
}