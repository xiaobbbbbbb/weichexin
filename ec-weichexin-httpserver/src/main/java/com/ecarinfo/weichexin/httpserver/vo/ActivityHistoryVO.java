package com.ecarinfo.weichexin.httpserver.vo;

import java.util.Date;

import com.ecarinfo.weichexin.po.ActivityAppoint;

public class ActivityHistoryVO extends ActivityAppoint {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private Date btime;
	private Date etime;
	private String image;//图片地址
	private String phoneNo;//电话
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getBtime() {
		return btime;
	}
	public void setBtime(Date btime) {
		this.btime = btime;
	}
	public Date getEtime() {
		return etime;
	}
	public void setEtime(Date etime) {
		this.etime = etime;
	}
	
}
