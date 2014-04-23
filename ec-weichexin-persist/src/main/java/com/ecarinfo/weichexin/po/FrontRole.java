package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class FrontRole implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private String name;//角色名称
	private Date addTime;//添加时间
	private String mark;//描述、备注
	private Integer status;//状态
	private String orgCode;
	private Long orgId;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Date getAddTime () {
        return addTime;
    }

    public void setAddTime (Date addTime) {
        this.addTime = addTime;
    }

    public String getMark () {
        return mark;
    }

    public void setMark (String mark) {
        this.mark = mark;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
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