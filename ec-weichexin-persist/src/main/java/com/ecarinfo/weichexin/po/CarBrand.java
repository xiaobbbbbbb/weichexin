package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class CarBrand implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private String code;//编码
	private String name;//名称
	private String letter;//字母
	private String img;//图片
	private Integer disabled;//是否有效（1：有效）
	private Float changeRate = 1.0f;//油耗调整系统
	private Integer version = 0;
	private Integer isNew = 1;

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

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getLetter () {
        return letter;
    }

    public void setLetter (String letter) {
        this.letter = letter;
    }

    public String getImg () {
        return img;
    }

    public void setImg (String img) {
        this.img = img;
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