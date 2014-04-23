package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class FaultStat implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private String orgCode;//机构编码
	private Long carId;//车id
	private String faultCode;//故障码
	private String faultDetail;//故障详细
	private Date ctime;//数据库插入时间
	private String deviceNo;
	private Date clickTime;
	private Long orgId;
	private Long groupId;
	private String groupCode;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }

    public Long getCarId () {
        return carId;
    }

    public void setCarId (Long carId) {
        this.carId = carId;
    }

    public String getFaultCode () {
        return faultCode;
    }

    public void setFaultCode (String faultCode) {
        this.faultCode = faultCode;
    }

    public String getFaultDetail () {
        return faultDetail;
    }

    public void setFaultDetail (String faultDetail) {
        this.faultDetail = faultDetail;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }

    public String getDeviceNo () {
        return deviceNo;
    }

    public void setDeviceNo (String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Date getClickTime () {
        return clickTime;
    }

    public void setClickTime (Date clickTime) {
        this.clickTime = clickTime;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }

    public Long getGroupId () {
        return groupId;
    }

    public void setGroupId (Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupCode () {
        return groupCode;
    }

    public void setGroupCode (String groupCode) {
        this.groupCode = groupCode;
    }
}