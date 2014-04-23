package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class CarSerial implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private String name;//名称
	private Integer groupId;//分组ID
	private String groupName;//分组名称
	private String code;//编码
	private Integer brandId;//厂牌
	private String brandName;
	private String url;//车系网址
	private Integer disabled;//是否有效
	private Float changeRate = 1.0f;//油耗调整系统
	private Integer version = 0;
	private Integer isNew = 1;

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

    public Integer getGroupId () {
        return groupId;
    }

    public void setGroupId (Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName () {
        return groupName;
    }

    public void setGroupName (String groupName) {
        this.groupName = groupName;
    }

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public Integer getBrandId () {
        return brandId;
    }

    public void setBrandId (Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName () {
        return brandName;
    }

    public void setBrandName (String brandName) {
        this.brandName = brandName;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public Integer getDisabled () {
        return disabled;
    }

    public void setDisabled (Integer disabled) {
        this.disabled = disabled;
    }

    public Float getChangeRate () {
        return changeRate;
    }

    public void setChangeRate (Float changeRate) {
        this.changeRate = changeRate;
    }

    public Integer getVersion () {
        return version;
    }

    public void setVersion (Integer version) {
        this.version = version;
    }

    public Integer getIsNew () {
        return isNew;
    }

    public void setIsNew (Integer isNew) {
        this.isNew = isNew;
    }
}