package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class FaultCodeType implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//系统ID
	private String name;//分类名称
	private String message;//分类描述
	private Integer isdisabled = 0;//是否禁用
	private Date updateTime;//同步时间

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }

    public Integer getIsdisabled () {
        return isdisabled;
    }

    public void setIsdisabled (Integer isdisabled) {
        this.isdisabled = isdisabled;
    }

    public Date getUpdateTime () {
        return updateTime;
    }

    public void setUpdateTime (Date updateTime) {
        this.updateTime = updateTime;
    }
}