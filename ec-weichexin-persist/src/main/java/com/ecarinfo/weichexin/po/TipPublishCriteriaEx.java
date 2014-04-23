package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class TipPublishCriteriaEx implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private Long tipId;
	private Integer mBegin;//开始里程
	private Integer mEnd;//结束里程
	private String carMIds;//车型ids
	private String carSIds;//车系ids
	private String carBIds;//厂牌ids
	private Long orgId;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getTipId () {
        return tipId;
    }

    public void setTipId (Long tipId) {
        this.tipId = tipId;
    }

    public Integer getMBegin () {
        return mBegin;
    }

    public void setMBegin (Integer mBegin) {
        this.mBegin = mBegin;
    }

    public Integer getMEnd () {
        return mEnd;
    }

    public void setMEnd (Integer mEnd) {
        this.mEnd = mEnd;
    }

    public String getCarMIds () {
        return carMIds;
    }

    public void setCarMIds (String carMIds) {
        this.carMIds = carMIds;
    }

    public String getCarSIds () {
        return carSIds;
    }

    public void setCarSIds (String carSIds) {
        this.carSIds = carSIds;
    }

    public String getCarBIds () {
        return carBIds;
    }

    public void setCarBIds (String carBIds) {
        this.carBIds = carBIds;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }
}