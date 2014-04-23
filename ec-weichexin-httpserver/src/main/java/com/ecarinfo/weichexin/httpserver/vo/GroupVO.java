package com.ecarinfo.weichexin.httpserver.vo;

import java.util.List;

import com.ecarinfo.weichexin.po.CarSerial;

public class GroupVO {
	private Integer id;
	private String name;
	private List<CarSerial> serialList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CarSerial> getSerialList() {
		return serialList;
	}
	public void setSerialList(List<CarSerial> serialList) {
		this.serialList = serialList;
	}
	
}
