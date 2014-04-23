package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class FivesaasCarInfoImprot implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;//主键ID
	private Long userId;//用户id
	private String userName;//用户名称
	private Long carInfoTempRealId;//车辆正式表中对应的车辆ID,此ID表明那些车辆是批量导入的
	private Date ctime;//记录创建的时间

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public Long getCarInfoTempRealId () {
        return carInfoTempRealId;
    }

    public void setCarInfoTempRealId (Long carInfoTempRealId) {
        this.carInfoTempRealId = carInfoTempRealId;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }
}