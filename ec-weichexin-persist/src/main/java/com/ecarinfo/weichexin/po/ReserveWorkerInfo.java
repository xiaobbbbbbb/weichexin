package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class ReserveWorkerInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;//ID
	private Long wcxUserId;
	private Integer workerId;
	private Date ctime;
	private String timePoint;
	private String carNo;
	private String contactTel;
	private String discountNote;
	private Integer practicalWorkerId;
	private String practicalTimePoint;
	private String practicalCarNo;
	private Integer status;//状态0，未处理，1,处理成功，2,处理失败,3已作废
	private Integer handleUserId;
	private Date handleTime;
	private Date reserveDate;//预约哪一天
	private String orgContactTel;
	private String orgCode;
	private Long orgId;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getWcxUserId () {
        return wcxUserId;
    }

    public void setWcxUserId (Long wcxUserId) {
        this.wcxUserId = wcxUserId;
    }

    public Integer getWorkerId () {
        return workerId;
    }

    public void setWorkerId (Integer workerId) {
        this.workerId = workerId;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }

    public String getTimePoint () {
        return timePoint;
    }

    public void setTimePoint (String timePoint) {
        this.timePoint = timePoint;
    }

    public String getCarNo () {
        return carNo;
    }

    public void setCarNo (String carNo) {
        this.carNo = carNo;
    }

    public String getContactTel () {
        return contactTel;
    }

    public void setContactTel (String contactTel) {
        this.contactTel = contactTel;
    }

    public String getDiscountNote () {
        return discountNote;
    }

    public void setDiscountNote (String discountNote) {
        this.discountNote = discountNote;
    }

    public Integer getPracticalWorkerId () {
        return practicalWorkerId;
    }

    public void setPracticalWorkerId (Integer practicalWorkerId) {
        this.practicalWorkerId = practicalWorkerId;
    }

    public String getPracticalTimePoint () {
        return practicalTimePoint;
    }

    public void setPracticalTimePoint (String practicalTimePoint) {
        this.practicalTimePoint = practicalTimePoint;
    }

    public String getPracticalCarNo () {
        return practicalCarNo;
    }

    public void setPracticalCarNo (String practicalCarNo) {
        this.practicalCarNo = practicalCarNo;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public Integer getHandleUserId () {
        return handleUserId;
    }

    public void setHandleUserId (Integer handleUserId) {
        this.handleUserId = handleUserId;
    }

    public Date getHandleTime () {
        return handleTime;
    }

    public void setHandleTime (Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getReserveDate () {
        return reserveDate;
    }

    public void setReserveDate (Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getOrgContactTel () {
        return orgContactTel;
    }

    public void setOrgContactTel (String orgContactTel) {
        this.orgContactTel = orgContactTel;
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