package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class WcxTrafficCars implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;//ID
	private Long wcxUserId;
	private String carNo;
	private String carFrameNo;
	private String carEngineNo;
	private String city;//城市
	private Date ctime;//创建时间
	private String orgCode;
	private Date utime;
	private Integer status = 1;//1,有效，0,无效

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

    public String getCarNo () {
        return carNo;
    }

    public void setCarNo (String carNo) {
        this.carNo = carNo;
    }

    public String getCarFrameNo () {
        return carFrameNo;
    }

    public void setCarFrameNo (String carFrameNo) {
        this.carFrameNo = carFrameNo;
    }

    public String getCarEngineNo () {
        return carEngineNo;
    }

    public void setCarEngineNo (String carEngineNo) {
        this.carEngineNo = carEngineNo;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }

    public Date getUtime () {
        return utime;
    }

    public void setUtime (Date utime) {
        this.utime = utime;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }
}