package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class SystemLog implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;//主键id
	private Date actionTime;//操作的时间
	private String action;// 操作内容
	private Integer type;//操作类型(1登陆、2退出、3添加、4修改、5删除)
	private String ip;//操作时间
	private Long userId;//操作用户id
	private String userName;//操作用户名

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Date getActionTime () {
        return actionTime;
    }

    public void setActionTime (Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getAction () {
        return action;
    }

    public void setAction (String action) {
        this.action = action;
    }

    public Integer getType () {
        return type;
    }

    public void setType (Integer type) {
        this.type = type;
    }

    public String getIp () {
        return ip;
    }

    public void setIp (String ip) {
        this.ip = ip;
    }

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }
}