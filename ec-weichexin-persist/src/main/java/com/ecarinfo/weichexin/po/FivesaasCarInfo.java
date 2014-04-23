package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class FivesaasCarInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private Long carId;//车辆ID
	private Date toMTime;//下次维护时间
	private Float toMMileage;
	private Float currentMileage;
	private Float dayMileage;//日均里程
	private Float avgSpeed;//平均车速
	private Float avgOilOneHundred;//百公里油耗
	private Integer fairNum;//故障次数
	private Integer serialId;//车系ID
	private Integer brandId;//厂牌ID
	private String carNo;//车牌号
	private Integer modelId;//车型ID
	private String deviceNo;//车猫编号
	private String deviceVersion;//车猫版本
	private String email;//邮箱
	private Date ctime;//注册日期
	private String faultCode;//最近一次错误码
	private Date faultTime;//最后一次故障时间
	private Long orgId;//4s店
	private String orgCode;
	private Integer bindCount;//绑定后判断是否是登陆，还是修改
	private String oldOrgCode;//״̬ľɵĻ
	private Integer groupId;//集团ID
	private String groupCode;//集团编码
	private Date maintenanceNoticeTime;//保养提醒时间
	private Long lastActivityId;//最近活动ID(过期用的）
	private Date lastActivityTime;//最近活动时间
	private Long lastTipId;//最近贴士ID
	private Date lastTipTime;//最近贴士时间
	private Long faultFollowActivityId;//最近跟进该车辆的活动ID
	private Date faultFollowActivityTime;//最近关怀故障车辆的时间
	private Long maintenanceFollowActivityId;//保养跟进活动ID
	private Date maintenanceFollowActivityTime;//保养跟进活动时间
	private Date updateTime = new Date();//更新时间
	private Date yearCheckDate;//年检日期
	private Date renewInsuranceDate;//续保日期
	private Date drivingLicenseExpireDate;//驾照过期日期
	private Date yearCheckExportTime;//年检导出时间
	private Date renewInsuranceExportTime;//续保导出时间
	private Date drivingLicenseExpireExportTime;//驾照过期导出时间
	private Date lastUploadDataTime;//最近上传数据时间
	private Date uploadDataStatExportTime;//上传记录导出时间
	private Long userId;
	private Integer drivingLicenseType;
	private Date drivingLicenseYearCareDate;
	private Long wcxUserId;
	private Integer carSource = 0;

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

    public Date getToMTime () {
        return toMTime;
    }

    public void setToMTime (Date toMTime) {
        this.toMTime = toMTime;
    }

    public Float getToMMileage () {
        return toMMileage;
    }

    public void setToMMileage (Float toMMileage) {
        this.toMMileage = toMMileage;
    }

    public Float getCurrentMileage () {
        return currentMileage;
    }

    public void setCurrentMileage (Float currentMileage) {
        this.currentMileage = currentMileage;
    }

    public Float getDayMileage () {
        return dayMileage;
    }

    public void setDayMileage (Float dayMileage) {
        this.dayMileage = dayMileage;
    }

    public Float getAvgSpeed () {
        return avgSpeed;
    }

    public void setAvgSpeed (Float avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Float getAvgOilOneHundred () {
        return avgOilOneHundred;
    }

    public void setAvgOilOneHundred (Float avgOilOneHundred) {
        this.avgOilOneHundred = avgOilOneHundred;
    }

    public Integer getFairNum () {
        return fairNum;
    }

    public void setFairNum (Integer fairNum) {
        this.fairNum = fairNum;
    }

    public Integer getSerialId () {
        return serialId;
    }

    public void setSerialId (Integer serialId) {
        this.serialId = serialId;
    }

    public Integer getBrandId () {
        return brandId;
    }

    public void setBrandId (Integer brandId) {
        this.brandId = brandId;
    }

    public String getCarNo () {
        return carNo;
    }

    public void setCarNo (String carNo) {
        this.carNo = carNo;
    }

    public Integer getModelId () {
        return modelId;
    }

    public void setModelId (Integer modelId) {
        this.modelId = modelId;
    }

    public String getDeviceNo () {
        return deviceNo;
    }

    public void setDeviceNo (String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceVersion () {
        return deviceVersion;
    }

    public void setDeviceVersion (String deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }

    public String getFaultCode () {
        return faultCode;
    }

    public void setFaultCode (String faultCode) {
        this.faultCode = faultCode;
    }

    public Date getFaultTime () {
        return faultTime;
    }

    public void setFaultTime (Date faultTime) {
        this.faultTime = faultTime;
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

    public Integer getBindCount () {
        return bindCount;
    }

    public void setBindCount (Integer bindCount) {
        this.bindCount = bindCount;
    }

    public String getOldOrgCode () {
        return oldOrgCode;
    }

    public void setOldOrgCode (String oldOrgCode) {
        this.oldOrgCode = oldOrgCode;
    }

    public Integer getGroupId () {
        return groupId;
    }

    public void setGroupId (Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupCode () {
        return groupCode;
    }

    public void setGroupCode (String groupCode) {
        this.groupCode = groupCode;
    }

    public Date getMaintenanceNoticeTime () {
        return maintenanceNoticeTime;
    }

    public void setMaintenanceNoticeTime (Date maintenanceNoticeTime) {
        this.maintenanceNoticeTime = maintenanceNoticeTime;
    }

    public Long getLastActivityId () {
        return lastActivityId;
    }

    public void setLastActivityId (Long lastActivityId) {
        this.lastActivityId = lastActivityId;
    }

    public Date getLastActivityTime () {
        return lastActivityTime;
    }

    public void setLastActivityTime (Date lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }

    public Long getLastTipId () {
        return lastTipId;
    }

    public void setLastTipId (Long lastTipId) {
        this.lastTipId = lastTipId;
    }

    public Date getLastTipTime () {
        return lastTipTime;
    }

    public void setLastTipTime (Date lastTipTime) {
        this.lastTipTime = lastTipTime;
    }

    public Long getFaultFollowActivityId () {
        return faultFollowActivityId;
    }

    public void setFaultFollowActivityId (Long faultFollowActivityId) {
        this.faultFollowActivityId = faultFollowActivityId;
    }

    public Date getFaultFollowActivityTime () {
        return faultFollowActivityTime;
    }

    public void setFaultFollowActivityTime (Date faultFollowActivityTime) {
        this.faultFollowActivityTime = faultFollowActivityTime;
    }

    public Long getMaintenanceFollowActivityId () {
        return maintenanceFollowActivityId;
    }

    public void setMaintenanceFollowActivityId (Long maintenanceFollowActivityId) {
        this.maintenanceFollowActivityId = maintenanceFollowActivityId;
    }

    public Date getMaintenanceFollowActivityTime () {
        return maintenanceFollowActivityTime;
    }

    public void setMaintenanceFollowActivityTime (Date maintenanceFollowActivityTime) {
        this.maintenanceFollowActivityTime = maintenanceFollowActivityTime;
    }

    public Date getUpdateTime () {
        return updateTime;
    }

    public void setUpdateTime (Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getYearCheckDate () {
        return yearCheckDate;
    }

    public void setYearCheckDate (Date yearCheckDate) {
        this.yearCheckDate = yearCheckDate;
    }

    public Date getRenewInsuranceDate () {
        return renewInsuranceDate;
    }

    public void setRenewInsuranceDate (Date renewInsuranceDate) {
        this.renewInsuranceDate = renewInsuranceDate;
    }

    public Date getDrivingLicenseExpireDate () {
        return drivingLicenseExpireDate;
    }

    public void setDrivingLicenseExpireDate (Date drivingLicenseExpireDate) {
        this.drivingLicenseExpireDate = drivingLicenseExpireDate;
    }

    public Date getYearCheckExportTime () {
        return yearCheckExportTime;
    }

    public void setYearCheckExportTime (Date yearCheckExportTime) {
        this.yearCheckExportTime = yearCheckExportTime;
    }

    public Date getRenewInsuranceExportTime () {
        return renewInsuranceExportTime;
    }

    public void setRenewInsuranceExportTime (Date renewInsuranceExportTime) {
        this.renewInsuranceExportTime = renewInsuranceExportTime;
    }

    public Date getDrivingLicenseExpireExportTime () {
        return drivingLicenseExpireExportTime;
    }

    public void setDrivingLicenseExpireExportTime (Date drivingLicenseExpireExportTime) {
        this.drivingLicenseExpireExportTime = drivingLicenseExpireExportTime;
    }

    public Date getLastUploadDataTime () {
        return lastUploadDataTime;
    }

    public void setLastUploadDataTime (Date lastUploadDataTime) {
        this.lastUploadDataTime = lastUploadDataTime;
    }

    public Date getUploadDataStatExportTime () {
        return uploadDataStatExportTime;
    }

    public void setUploadDataStatExportTime (Date uploadDataStatExportTime) {
        this.uploadDataStatExportTime = uploadDataStatExportTime;
    }

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    public Integer getDrivingLicenseType () {
        return drivingLicenseType;
    }

    public void setDrivingLicenseType (Integer drivingLicenseType) {
        this.drivingLicenseType = drivingLicenseType;
    }

    public Date getDrivingLicenseYearCareDate () {
        return drivingLicenseYearCareDate;
    }

    public void setDrivingLicenseYearCareDate (Date drivingLicenseYearCareDate) {
        this.drivingLicenseYearCareDate = drivingLicenseYearCareDate;
    }

    public Long getWcxUserId () {
        return wcxUserId;
    }

    public void setWcxUserId (Long wcxUserId) {
        this.wcxUserId = wcxUserId;
    }

    public Integer getCarSource () {
        return carSource;
    }

    public void setCarSource (Integer carSource) {
        this.carSource = carSource;
    }
}