package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class LogAction implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private Long sid;//点击那个主题的id
	private Integer type;//1:活动优惠;2:贴士;3:故障求助;4:保养预约,5登陆记录
	private Date ctime;//创建时间
	private Date loginTime;//登录时间
	private Date outTime;//退出时间
	private Long userId;//用户id
	private String ip;//操作ip
	private Long groupId;//集团id
	private String groupCode;//集团编码
	private Long orgId;//4s店id
	private String orgCode;//4s店编码

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getSid () {
        return sid;
    }

    public void setSid (Long sid) {
        this.sid = sid;
    }

    public Integer getType () {
        return type;
    }

    public void setType (Integer type) {
        this.type = type;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }

    public Date getLoginTime () {
        return loginTime;
    }

    public void setLoginTime (Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getOutTime () {
        return outTime;
    }

    public void setOutTime (Date outTime) {
        this.outTime = outTime;
    }

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    public String getIp () {
        return ip;
    }

    public void setIp (String ip) {
        this.ip = ip;
    }

    public Long getGroupId () {
        return groupId;
    }

    public void setGroupId (Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupCode () {
        return groupCode;
    }

    public void setGroupCode (String groupCode) {
        this.groupCode = groupCode;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }
}