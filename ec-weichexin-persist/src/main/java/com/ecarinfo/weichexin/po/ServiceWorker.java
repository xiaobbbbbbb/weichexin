package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class ServiceWorker implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private Integer checkUserId;
	private Integer cuserId;
	private String name;
	private Date checkTime;
	private Date ctime;
	private Integer status;//状态1,有效，0,无效
	private String orgCode;
	private Long orgId;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getCheckUserId () {
        return checkUserId;
    }

    public void setCheckUserId (Integer checkUserId) {
        this.checkUserId = checkUserId;
    }

    public Integer getCuserId () {
        return cuserId;
    }

    public void setCuserId (Integer cuserId) {
        this.cuserId = cuserId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Date getCheckTime () {
        return checkTime;
    }

    public void setCheckTime (Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
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