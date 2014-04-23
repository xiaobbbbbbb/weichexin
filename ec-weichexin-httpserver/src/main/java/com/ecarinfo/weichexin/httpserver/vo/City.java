package com.ecarinfo.weichexin.httpserver.vo;

public class City {
	private Integer id;
	private Integer proId;//省份Id
	private String name;
	private String sname;//简称 琼A B C 等
	
	public City(Integer id,Integer proId,String name,String sname){
		this.id = id;
		this.proId = proId;
		this.name = name;
		this.sname = sname;
	}
	public City(){
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	
}
