package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class WcxPublicAccount implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private Integer cuserId;
	private String name;
	private String loginName;
	private String url;
	private String token;
	private String appKey;
	private String appSecret;
	private String wcxMenuIds;
	private Date ctime;
	private Integer status;//状态0，初始态，1：已验证url，2，已输入appkey、appSecret，3，已创建菜单
	private String orgCode;
	private Long orgId;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getCuserId () {
        return cuserId;
    }

    public void setCuserId (Integer cuserId) {
        this.cuserId = cuserId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getLoginName () {
        return loginName;
    }

    public void setLoginName (String loginName) {
        this.loginName = loginName;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getToken () {
        return token;
    }

    public void setToken (String token) {
        this.token = token;
    }

    public String getAppKey () {
        return appKey;
    }

    public void setAppKey (String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret () {
        return appSecret;
    }

    public void setAppSecret (String appSecret) {
        this.appSecret = appSecret;
    }

    public String getWcxMenuIds () {
        return wcxMenuIds;
    }

    public void setWcxMenuIds (String wcxMenuIds) {
        this.wcxMenuIds = wcxMenuIds;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }
}