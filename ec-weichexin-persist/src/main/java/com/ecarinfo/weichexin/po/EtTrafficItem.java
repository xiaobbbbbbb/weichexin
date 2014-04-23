package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class EtTrafficItem implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;//ID
	private Long carId;//车辆ID
	private String carNo;//车牌
	private String no = "";//违章单号
	private Date day;//违章日期
	private Date trafficTime;//违章时间
	private String address;//违章地点
	private String item;//违章项目
	private Integer money;//罚款金额
	private String unit;//执法单位
	private Integer isValid = 0;//是否处理
	private Integer points;//违章分数
	private Date createdTime;//创建时间

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

    public String getCarNo () {
        return carNo;
    }

    public void setCarNo (String carNo) {
        this.carNo = carNo;
    }

    public String getNo () {
        return no;
    }

    public void setNo (String no) {
        this.no = no;
    }

    public Date getDay () {
        return day;
    }

    public void setDay (Date day) {
        this.day = day;
    }

    public Date getTrafficTime () {
        return trafficTime;
    }

    public void setTrafficTime (Date trafficTime) {
        this.trafficTime = trafficTime;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getItem () {
        return item;
    }

    public void setItem (String item) {
        this.item = item;
    }

    public Integer getMoney () {
        return money;
    }

    public void setMoney (Integer money) {
        this.money = money;
    }

    public String getUnit () {
        return unit;
    }

    public void setUnit (String unit) {
        this.unit = unit;
    }

    public Integer getIsValid () {
        return isValid;
    }

    public void setIsValid (Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getPoints () {
        return points;
    }

    public void setPoints (Integer points) {
        this.points = points;
    }

    public Date getCreatedTime () {
        return createdTime;
    }

    public void setCreatedTime (Date createdTime) {
        this.createdTime = createdTime;
    }
}