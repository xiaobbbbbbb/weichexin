package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class FaultCode implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private String code;//故障码序号
	private String system;//系统
	private String summary;//故障概要
	private String masterType;//大分类
	private Integer typeId;//故障分类ID
	private String typeName;//故障类型
	private Integer grade;//等级
	private Integer isdisabled;
	private Date updateTime;//同步时间

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public String getSystem () {
        return system;
    }

    public void setSystem (String system) {
        this.system = system;
    }

    public String getSummary () {
        return summary;
    }

    public void setSummary (String summary) {
        this.summary = summary;
    }

    public String getMasterType () {
        return masterType;
    }

    public void setMasterType (String masterType) {
        this.masterType = masterType;
    }

    public Integer getTypeId () {
        return typeId;
    }

    public void setTypeId (Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName () {
        return typeName;
    }

    public void setTypeName (String typeName) {
        this.typeName = typeName;
    }

    public Integer getGrade () {
        return grade;
    }

    public void setGrade (Integer grade) {
        this.grade = grade;
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