package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class ServiceOrg implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long sid;//ID
	private String code;//店编码
	private String brands;//该机构拥有的厂牌按照逗号分隔
	private String name;
	private Integer groupId;//集团ID
	private String groupCode;//集团编码
	private String logo;//LOGO
	private String address;
	private String tel;//服务电话
	private Date ctime;//添加时间
	private String mark;//备注
	private Date startTime;//服务开始时间
	private Date endTime;//服务结束时间
	private Boolean isValid;//机构是否有效
	private Integer delFlag;//是否删除
	private String activityPublishMonth;//yyyy-mm(活动发布当前月份）
	private Integer activityPublishNum = 0;//当前月份活动发布次数
	private Integer activityPublishMaxNum = 5;//当前月活动发布上限
	private Date updateTime = new Date();//更新时间
	private String telForComplaint;//投诉服务电话
	private String telForMaintenance;//保养服务电话
	private String telForSales;//销售服务电话
	private String renewInsureTel;//保养服务电话
	private String usedCarServeTel;//2手车服务电话
	private String yearCheckServeTel;//年检服务电话
	private String helpTel;//救援热线
	private Integer reserveAvailableDays;//预约可提前天数
	private String reserveDiscountNote;//预约折扣备注

    public Long getSid () {
        return sid;
    }

    public void setSid (Long sid) {
        this.sid = sid;
    }

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public String getBrands () {
        return brands;
    }

    public void setBrands (String brands) {
        this.brands = brands;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
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

    public String getLogo () {
        return logo;
    }

    public void setLogo (String logo) {
        this.logo = logo;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getTel () {
        return tel;
    }

    public void setTel (String tel) {
        this.tel = tel;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }

    public String getMark () {
        return mark;
    }

    public void setMark (String mark) {
        this.mark = mark;
    }

    public Date getStartTime () {
        return startTime;
    }

    public void setStartTime (Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime () {
        return endTime;
    }

    public void setEndTime (Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getIsValid () {
        return isValid;
    }

    public void setIsValid (Boolean isValid) {
        this.isValid = isValid;
    }

    public Integer getDelFlag () {
        return delFlag;
    }

    public void setDelFlag (Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getActivityPublishMonth () {
        return activityPublishMonth;
    }

    public void setActivityPublishMonth (String activityPublishMonth) {
        this.activityPublishMonth = activityPublishMonth;
    }

    public Integer getActivityPublishNum () {
        return activityPublishNum;
    }

    public void setActivityPublishNum (Integer activityPublishNum) {
        this.activityPublishNum = activityPublishNum;
    }

    public Integer getActivityPublishMaxNum () {
        return activityPublishMaxNum;
    }

    public void setActivityPublishMaxNum (Integer activityPublishMaxNum) {
        this.activityPublishMaxNum = activityPublishMaxNum;
    }

    public Date getUpdateTime () {
        return updateTime;
    }

    public void setUpdateTime (Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTelForComplaint () {
        return telForComplaint;
    }

    public void setTelForComplaint (String telForComplaint) {
        this.telForComplaint = telForComplaint;
    }

    public String getTelForMaintenance () {
        return telForMaintenance;
    }

    public void setTelForMaintenance (String telForMaintenance) {
        this.telForMaintenance = telForMaintenance;
    }

    public String getTelForSales () {
        return telForSales;
    }

    public void setTelForSales (String telForSales) {
        this.telForSales = telForSales;
    }

    public String getRenewInsureTel () {
        return renewInsureTel;
    }

    public void setRenewInsureTel (String renewInsureTel) {
        this.renewInsureTel = renewInsureTel;
    }

    public String getUsedCarServeTel () {
        return usedCarServeTel;
    }

    public void setUsedCarServeTel (String usedCarServeTel) {
        this.usedCarServeTel = usedCarServeTel;
    }

    public String getYearCheckServeTel () {
        return yearCheckServeTel;
    }

    public void setYearCheckServeTel (String yearCheckServeTel) {
        this.yearCheckServeTel = yearCheckServeTel;
    }

    public String getHelpTel () {
        return helpTel;
    }

    public void setHelpTel (String helpTel) {
        this.helpTel = helpTel;
    }

    public Integer getReserveAvailableDays () {
        return reserveAvailableDays;
    }

    public void setReserveAvailableDays (Integer reserveAvailableDays) {
        this.reserveAvailableDays = reserveAvailableDays;
    }

    public String getReserveDiscountNote () {
        return reserveDiscountNote;
    }

    public void setReserveDiscountNote (String reserveDiscountNote) {
        this.reserveDiscountNote = reserveDiscountNote;
    }
}