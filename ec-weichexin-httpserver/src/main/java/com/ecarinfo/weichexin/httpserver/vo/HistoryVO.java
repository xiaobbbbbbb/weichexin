package com.ecarinfo.weichexin.httpserver.vo;

import java.util.Date;

public class HistoryVO {
	private Integer id;
	private Date appointTime;
	private String name ;
	private String serialName;
	private String year;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(Date appointTime) {
		this.appointTime = appointTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSerialName() {
		return serialName;
	}
	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "HistoryVO [id=" + id + ", appointTime=" + appointTime
				+ ", name=" + name + ", serialName=" + serialName + ", year="
				+ year + ", status=" + status + "]";
	}
}
