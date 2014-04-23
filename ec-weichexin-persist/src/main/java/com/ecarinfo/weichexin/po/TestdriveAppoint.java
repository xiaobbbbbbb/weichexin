package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class TestdriveAppoint implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;//ID
	private Long orgId;
	private String openId;
	private Integer modelId;
	private String name;
	private String telNo;
	private String comment;
	private Date cancelTime;
	private String cancelDesc;
	private Date requestTime;
	private Date appointTime;
	private Date confirmTime;
	private Integer confirmUserId;
	private String confirmDesc;
	private Integer status;//状态 0初始化 ，1已确认，2微信端取消，3确认不来

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }

    public String getOpenId () {
        return openId;
    }

    public void setOpenId (String openId) {
        this.openId = openId;
    }

    public Integer getModelId () {
        return modelId;
    }

    public void setModelId (Integer modelId) {
        this.modelId = modelId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getTelNo () {
        return telNo;
    }

    public void setTelNo (String telNo) {
        this.telNo = telNo;
    }

    public String getComment () {
        return comment;
    }

    public void setComment (String comment) {
        this.comment = comment;
    }

    public Date getCancelTime () {
        return cancelTime;
    }

    public void setCancelTime (Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelDesc () {
        return cancelDesc;
    }

    public void setCancelDesc (String cancelDesc) {
        this.cancelDesc = cancelDesc;
    }

    public Date getRequestTime () {
        return requestTime;
    }

    public void setRequestTime (Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getAppointTime () {
        return appointTime;
    }

    public void setAppointTime (Date appointTime) {
        this.appointTime = appointTime;
    }

    public Date getConfirmTime () {
        return confirmTime;
    }

    public void setConfirmTime (Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getConfirmUserId () {
        return confirmUserId;
    }

    public void setConfirmUserId (Integer confirmUserId) {
        this.confirmUserId = confirmUserId;
    }

    public String getConfirmDesc () {
        return confirmDesc;
    }

    public void setConfirmDesc (String confirmDesc) {
        this.confirmDesc = confirmDesc;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }
}