package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class TipCars implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long tipId;//活动ID
	private Long carId;//车辆ID
	private Long orgId;

    public Long getTipId () {
        return tipId;
    }

    public void setTipId (Long tipId) {
        this.tipId = tipId;
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