package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class UserGindex implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//主键
	private Integer carId;//车的id
	private String carNo;//车牌号
	private String email;//邮箱
	private String carType;//车型
	private String pailiang;//排量
	private String oilType;//油品
	private Integer userAttr;//用户属性
	private Integer driveNum;//行车次数
	private Float totalDriveTime;//总行车时长
	private Float totalMileage;//总行驶里程
	private Float totalOilSize;//耗油总量
	private Float totalOilMoney;//总花费
	private Float totalCarbonSize;//总碳排
	private Float totalCarbonMoney;//总碳税金额
	private Float avgSpeed;//平均车速
	private Float dayAvgOil;//日平均油耗
	private Date createTime;//记录创建时间
	private Float dayAvgCarbon;//日平均碳排
	private Float dayCarbonMoney;//日碳税金额
	private Date clientDay;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getCarId () {
        return carId;
    }

    public void setCarId (Integer carId) {
        this.carId = carId;
    }

    public String getCarNo () {
        return carNo;
    }

    public void setCarNo (String carNo) {
        this.carNo = carNo;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
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

    public Integer getUserAttr () {
        return userAttr;
    }

    public void setUserAttr (Integer userAttr) {
        this.userAttr = userAttr;
    }

    public Integer getDriveNum () {
        return driveNum;
    }

    public void setDriveNum (Integer driveNum) {
        this.driveNum = driveNum;
    }

    public Float getTotalDriveTime () {
        return totalDriveTime;
    }

    public void setTotalDriveTime (Float totalDriveTime) {
        this.totalDriveTime = totalDriveTime;
    }

    public Float getTotalMileage () {
        return totalMileage;
    }

    public void setTotalMileage (Float totalMileage) {
        this.totalMileage = totalMileage;
    }

    public Float getTotalOilSize () {
        return totalOilSize;
    }

    public void setTotalOilSize (Float totalOilSize) {
        this.totalOilSize = totalOilSize;
    }

    public Float getTotalOilMoney () {
        return totalOilMoney;
    }

    public void setTotalOilMoney (Float totalOilMoney) {
        this.totalOilMoney = totalOilMoney;
    }

    public Float getTotalCarbonSize () {
        return totalCarbonSize;
    }

    public void setTotalCarbonSize (Float totalCarbonSize) {
        this.totalCarbonSize = totalCarbonSize;
    }

    public Float getTotalCarbonMoney () {
        return totalCarbonMoney;
    }

    public void setTotalCarbonMoney (Float totalCarbonMoney) {
        this.totalCarbonMoney = totalCarbonMoney;
    }

    public Float getAvgSpeed () {
        return avgSpeed;
    }

    public void setAvgSpeed (Float avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Float getDayAvgOil () {
        return dayAvgOil;
    }

    public void setDayAvgOil (Float dayAvgOil) {
        this.dayAvgOil = dayAvgOil;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }

    public Float getDayAvgCarbon () {
        return dayAvgCarbon;
    }

    public void setDayAvgCarbon (Float dayAvgCarbon) {
        this.dayAvgCarbon = dayAvgCarbon;
    }

    public Float getDayCarbonMoney () {
        return dayCarbonMoney;
    }

    public void setDayCarbonMoney (Float dayCarbonMoney) {
        this.dayCarbonMoney = dayCarbonMoney;
    }

    public Date getClientDay () {
        return clientDay;
    }

    public void setClientDay (Date clientDay) {
        this.clientDay = clientDay;
    }
}