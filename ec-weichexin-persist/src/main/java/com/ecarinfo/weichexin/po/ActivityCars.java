package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class ActivityCars implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long activityId;//活动ID
	private Long carId;//车辆ID
	private Long orgId;//5s店ID

    public Long getActivityId () {
        return activityId;
    }

    public void setActivityId (Long activityId) {
        this.activityId = activityId;
    }

    public Long getCarId () {
        return carId;
    }

    public void setCarId (Long carId) {
        this.carId = carId;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }
}