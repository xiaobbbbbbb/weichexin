package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class Expert implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private String name;//专家名称
	private String jobInfo;//工作岗位
	private String phoneNo;//联系电话
	private String headImage;//头像
	private String profile;//简介
	private Integer totalScore = 0;//总评分
	private Integer commentCount = 0;//评论次数
	private Integer status;//状态
	private Date addTime;//添加时间
	private Integer addUserId;//添加用户
	private String orgCode;
	private Long orgId;

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

    public String getJobInfo () {
        return jobInfo;
    }

    public void setJobInfo (String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public String getPhoneNo () {
        return phoneNo;
    }

    public void setPhoneNo (String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getHeadImage () {
        return headImage;
    }

    public void setHeadImage (String headImage) {
        this.headImage = headImage;
    }

    public String getProfile () {
        return profile;
    }

    public void setProfile (String profile) {
        this.profile = profile;
    }

    public Integer getTotalScore () {
        return totalScore;
    }

    public void setTotalScore (Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getCommentCount () {
        return commentCount;
    }

    public void setCommentCount (Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public Date getAddTime () {
        return addTime;
    }

    public void setAddTime (Date addTime) {
        this.addTime = addTime;
    }

    public Integer getAddUserId () {
        return addUserId;
    }

    public void setAddUserId (Integer addUserId) {
        this.addUserId = addUserId;
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