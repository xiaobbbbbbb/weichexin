package com.ecarinfo.weichexin.httpserver.utils;


public class ConfigUtils {

	private int port;
	private String staticRootPath;
	private String img_url;
	private String domain;
	private String email;
	private String ecar5s_url;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getStaticRootPath() {
		return staticRootPath;
	}
	public void setStaticRootPath(String staticRootPath) {
		this.staticRootPath = staticRootPath;
	}
	public String getEcar5s_url() {
		return ecar5s_url;
	}
	public void setEcar5s_url(String ecar5s_url) {
		this.ecar5s_url = ecar5s_url;
	}

}
