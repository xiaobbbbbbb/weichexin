package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class OpeUserInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//主键
	private Integer userId;//用户id
	private String email;//注册邮箱
	private String carNo;//车牌号
	private String code;//设备号
	private Integer carId;//车的id
	private String appType;//客户端类型
	private String hardVersion;//车猫版本
	private String carType;//车型
	private String pailiang;//排量
	private String oilType;//油品
	private Date userCreateDate;//用户注册日期
	private String deviceState;//车猫绑定状态
	private Date deviceCreateDate;//车猫绑定日期
	private Integer liveness;//行车活跃度
	private String typeNianjian;//年检提醒状态
	private String typeWeizhang;//违章提醒状态
	private String typeGuzhang;//车辆故障状态
	private String typeBaoyang;//车辆保养状态

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getUserId () {
        return userId;
    }

    public void setUserId (Integer userId) {
        this.userId = userId;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getCarNo () {
        return carNo;
    }

    public void setCarNo (String carNo) {
        this.carNo = carNo;
    }

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public Integer getCarId () {
        return carId;
    }

    public void setCarId (Integer carId) {
        this.carId = carId;
    }

    public String getAppType () {
        return appType;
    }

    public void setAppType (String appType) {
        this.appType = appType;
    }

    public String getHardVersion () {
        return hardVersion;
    }

    public void setHardVersion (String hardVersion) {
        this.hardVersion = hardVersion;
    }

    public String getCarType () {
        return carType;
    }

    public void setCarType (String carType) {
        this.carType = carType;
    }

    public String getPailiang () {
        return pailiang;
    }

    public void setPailiang (String pailiang) {
        this.pailiang = pailiang;
    }

    public String getOilType () {
        return oilType;
    }

    public void setOilType (String oilType) {
        this.oilType = oilType;
    }

    public Date getUserCreateDate () {
        return userCreateDate;
    }

    public void setUserCreateDate (Date userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public String getDeviceState () {
        return deviceState;
    }

    public void setDeviceState (String deviceState) {
        this.deviceState = deviceState;
    }

    public Date getDeviceCreateDate () {
        return deviceCreateDate;
    }

    public void setDeviceCreateDate (Date deviceCreateDate) {
        this.deviceCreateDate = deviceCreateDate;
    }

    public Integer getLiveness () {
        return liveness;
    }

    public void setLiveness (Integer liveness) {
        this.liveness = liveness;
    }

    public String getTypeNianjian () {
        return typeNianjian;
    }

    public void setTypeNianjian (String typeNianjian) {
        this.typeNianjian = typeNianjian;
    }

    public String getTypeWeizhang () {
        return typeWeizhang;
    }

    public void setTypeWeizhang (String typeWeizhang) {
        this.typeWeizhang = typeWeizhang;
    }

    public String getTypeGuzhang () {
        return typeGuzhang;
    }

    public void setTypeGuzhang (String typeGuzhang) {
        this.typeGuzhang = typeGuzhang;
    }

    public String getTypeBaoyang () {
        return typeBaoyang;
    }

    public void setTypeBaoyang (String typeBaoyang) {
        this.typeBaoyang = typeBaoyang;
    }
}