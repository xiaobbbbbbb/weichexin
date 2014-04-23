package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class CarTip implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;//ID
	private String title;
	private String content;
	private Integer categoryId;//类别ID
	private String image;//广告图片
	private Long orgId;//4s店ID
	private String orgCode;
	private Integer groupId;//4s集团ID
	private Date ctime;//添加时间
	private Integer status = 0;//审核状态
	private Long cuserId;//创建用户ID
	private Long auserId;//审核userID
	private Date checktime;//审核时间
	private String checkReason;// 不通过审核原因
	private Integer clickNum;
	private Integer pushNum;
	private String auserName;
	private String cuserName;//创建者登录名
	private String publishCriterias;//发布条件
	private Integer discardUserId;//作废用户id
	private Date discardTime;//作废时间
	private Integer wcxClickNums = 0;
	private Integer publishChannel;//全部/ APP及微车信/APP/微车信

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public Integer getCategoryId () {
        return categoryId;
    }

    public void setCategoryId (Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage () {
        return image;
    }

    public void setImage (String image) {
        this.image = image;
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

    public Integer getGroupId () {
        return groupId;
    }

    public void setGroupId (Integer groupId) {
        this.groupId = groupId;
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

    public Long getCuserId () {
        return cuserId;
    }

    public void setCuserId (Long cuserId) {
        this.cuserId = cuserId;
    }

    public Long getAuserId () {
        return auserId;
    }

    public void setAuserId (Long auserId) {
        this.auserId = auserId;
    }

    public Date getChecktime () {
        return checktime;
    }

    public void setChecktime (Date checktime) {
        this.checktime = checktime;
    }

    public String getCheckReason () {
        return checkReason;
    }

    public void setCheckReason (String checkReason) {
        this.checkReason = checkReason;
    }

    public Integer getClickNum () {
        return clickNum;
    }

    public void setClickNum (Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getPushNum () {
        return pushNum;
    }

    public void setPushNum (Integer pushNum) {
        this.pushNum = pushNum;
    }

    public String getAuserName () {
        return auserName;
    }

    public void setAuserName (String auserName) {
        this.auserName = auserName;
    }

    public String getCuserName () {
        return cuserName;
    }

    public void setCuserName (String cuserName) {
        this.cuserName = cuserName;
    }

    public String getPublishCriterias () {
        return publishCriterias;
    }

    public void setPublishCriterias (String publishCriterias) {
        this.publishCriterias = publishCriterias;
    }

    public Integer getDiscardUserId () {
        return discardUserId;
    }

    public void setDiscardUserId (Integer discardUserId) {
        this.discardUserId = discardUserId;
    }

    public Date getDiscardTime () {
        return discardTime;
    }

    public void setDiscardTime (Date discardTime) {
        this.discardTime = discardTime;
    }

    public Integer getWcxClickNums () {
        return wcxClickNums;
    }

    public void setWcxClickNums (Integer wcxClickNums) {
        this.wcxClickNums = wcxClickNums;
    }

    public Integer getPublishChannel () {
        return publishChannel;
    }

    public void setPublishChannel (Integer publishChannel) {
        this.publishChannel = publishChannel;
    }
}