package com.ecarinfo.weichexin.httpserver.vo;

import com.ecarinfo.weichexin.po.WcxTrafficCars;

public class WcxTrafficCarsVo extends WcxTrafficCars {
	
	private Integer count;
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	public WcxTrafficCarsVo(){
	}
	public WcxTrafficCarsVo(Integer count,WcxTrafficCars car){
		this.count=count;
		this.setCarEngineNo(car.getCarEngineNo());
		this.setCarFrameNo(car.getCarFrameNo());
		this.setCarNo(car.getCarNo());
		this.setCity(car.getCity());
		this.setCtime(car.getCtime());
		this.setStatus(car.getStatus());
		this.setId(car.getId());
		this.setOrgCode(car.getOrgCode());
		this.setUtime(car.getUtime());
		this.setWcxUserId(car.getWcxUserId());
	}
}
