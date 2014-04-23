package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class CarModel implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private String name;//名称
	private String code;//编码
	private String year;//年份
	private Integer serialId;//车系ID
	private String serialName;//车系名称
	private Float oilSize;//油箱大小
	private Float oilNet;//网友油耗
	private Float oilStandard;//工信油耗标准
	private Float pailiang;//排量
	private String url;
	private Integer issale;//是否正在销售中
	private Integer disabled;//是否有效
	private Float oilChangeRate = 1.00f;//油耗调整系统
	private Float mileageChangeRate;
	private Float avgOil;//车信标准油耗
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

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public String getYear () {
        return year;
    }

    public void setYear (String year) {
        this.year = year;
    }

    public Integer getSerialId () {
        return serialId;
    }

    public void setSerialId (Integer serialId) {
        this.serialId = serialId;
    }

    public String getSerialName () {
        return serialName;
    }

    public void setSerialName (String serialName) {
        this.serialName = serialName;
    }

    public Float getOilSize () {
        return oilSize;
    }

    public void setOilSize (Float oilSize) {
        this.oilSize = oilSize;
    }

    public Float getOilNet () {
        return oilNet;
    }

    public void setOilNet (Float oilNet) {
        this.oilNet = oilNet;
    }

    public Float getOilStandard () {
        return oilStandard;
    }

    public void setOilStandard (Float oilStandard) {
        this.oilStandard = oilStandard;
    }

    public Float getPailiang () {
        return pailiang;
    }

    public void setPailiang (Float pailiang) {
        this.pailiang = pailiang;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public Integer getIssale () {
        return issale;
    }

    public void setIssale (Integer issale) {
        this.issale = issale;
    }

    public Integer getDisabled () {
        return disabled;
    }

    public void setDisabled (Integer disabled) {
        this.disabled = disabled;
    }

    public Float getOilChangeRate () {
        return oilChangeRate;
    }

    public void setOilChangeRate (Float oilChangeRate) {
        this.oilChangeRate = oilChangeRate;
    }

    public Float getMileageChangeRate () {
        return mileageChangeRate;
    }

    public void setMileageChangeRate (Float mileageChangeRate) {
        this.mileageChangeRate = mileageChangeRate;
    }

    public Float getAvgOil () {
        return avgOil;
    }

    public void setAvgOil (Float avgOil) {
        this.avgOil = avgOil;
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