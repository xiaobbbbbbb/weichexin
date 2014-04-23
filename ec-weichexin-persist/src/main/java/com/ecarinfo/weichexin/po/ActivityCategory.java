package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class ActivityCategory implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String name;
	private Integer orderNo;
	private Date ctime;//组织编码
	private String orgCode;
	private Integer orgId;//组织id

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

    public Integer getOrderNo () {
        return orderNo;
    }

    public void setOrderNo (Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getOrgId () {
        return orgId;
    }

    public void setOrgId (Integer orgId) {
        this.orgId = orgId;
    }
}