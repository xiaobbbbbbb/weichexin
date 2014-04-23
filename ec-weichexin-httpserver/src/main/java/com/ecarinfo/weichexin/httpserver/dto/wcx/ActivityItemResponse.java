package com.ecarinfo.weichexin.httpserver.dto.wcx;

import java.io.Serializable;

public class ActivityItemResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long sid;
	private String title;
	private String content;
	private String screenshot_img;
	private String url;
	private String link_man;// 活动联系人
	private String link_tel;// 活动联系电话 (客户拨打时需要记录)
	private String address;// 活动地址
	private String start_date;// 有效期开始时间(date格式)
	private String end_date;// 有效期终止时间
	private String ctime;// 添加时间

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getScreenshot_img() {
		return screenshot_img;
	}

	public void setScreenshot_img(String screenshot_img) {
		this.screenshot_img = screenshot_img;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getLink_man() {
		return link_man;
	}

	public void setLink_man(String link_man) {
		this.link_man = link_man;
	}

	public String getLink_tel() {
		return link_tel;
	}

	public void setLink_tel(String link_tel) {
		this.link_tel = link_tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
}
