package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class MaintenanceStat implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private String orgCode;//机构编码
	private Long carId;//车id
	private Float nextMileage;//下一次里程
	private Date nextTime;//下一次时间
	private Float currentMileage;//当前里程
	private String deviceNo;//车猫编号
	private Date ctime;
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

    public Float getNextMileage () {
        return nextMileage;
    }

    public void setNextMileage (Float nextMileage) {
        this.nextMileage = nextMileage;
    }

    public Date getNextTime () {
        return nextTime;
    }

    public void setNextTime (Date nextTime) {
        this.nextTime = nextTime;
    }

    public Float getCurrentMileage () {
        return currentMileage;
    }

    public void setCurrentMileage (Float currentMileage) {
        this.currentMileage = currentMileage;
    }

    public String getDeviceNo () {
        return deviceNo;
    }

    public void setDeviceNo (String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
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