package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class ClickStat implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private Long carId;//车辆ID
	private String deviceNo;//车猫号
	private Long sid;//广告ID
	private Integer type;//1:贴士;2:咨询;3:故障求助;4:保养预约
	private Date clickTime;//点击时间
	private Date ctime;
	private String month;
	private Date day;
	private Long orgId;
	private String orgCode;
	private Long groupId;
	private String groupCode;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getCarId () {
        return carId;
    }

    public void setCarId (Long carId) {
        this.carId = carId;
    }

    public String getDeviceNo () {
        return deviceNo;
    }

    public void setDeviceNo (String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Long getSid () {
        return sid;
    }

    public void setSid (Long sid) {
        this.sid = sid;
    }

    public Integer getType () {
        return type;
    }

    public void setType (Integer type) {
        this.type = type;
    }

    public Date getClickTime () {
        return clickTime;
    }

    public void setClickTime (Date clickTime) {
        this.clickTime = clickTime;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }

    public String getMonth () {
        return month;
    }

    public void setMonth (String month) {
        this.month = month;
    }

    public Date getDay () {
        return day;
    }

    public void setDay (Date day) {
        this.day = day;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
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