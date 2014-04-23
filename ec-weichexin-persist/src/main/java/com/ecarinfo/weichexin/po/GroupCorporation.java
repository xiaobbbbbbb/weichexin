package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class GroupCorporation implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private String groupName;//集团名称
	private String groupCode;//集团编码
	private String zone;//地区
	private String address;//地址
	private String contactInfo;//联系方式
	private Date ctime;//添加时间
	private String comment;//备注
	private Integer isAway;//是否启用，0为启用
	private Integer delFlag = 1;//是否删除，删除为1
	private Date updateTime = new Date();//更新时间

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getGroupName () {
        return groupName;
    }

    public void setGroupName (String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCode () {
        return groupCode;
    }

    public void setGroupCode (String groupCode) {
        this.groupCode = groupCode;
    }

    public String getZone () {
        return zone;
    }

    public void setZone (String zone) {
        this.zone = zone;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getContactInfo () {
        return contactInfo;
    }

    public void setContactInfo (String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }

    public String getComment () {
        return comment;
    }

    public void setComment (String comment) {
        this.comment = comment;
    }

    public Integer getIsAway () {
        return isAway;
    }

    public void setIsAway (Integer isAway) {
        this.isAway = isAway;
    }

    public Integer getDelFlag () {
        return delFlag;
    }

    public void setDelFlag (Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getUpdateTime () {
        return updateTime;
    }

    public void setUpdateTime (Date updateTime) {
        this.updateTime = updateTime;
    }
}