package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class WorkerServeTimePoints implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private Integer workerId;//状态
	private String timePoint;
	private String discountNote;
	private Integer weekIndex;
	private String orgCode;
	private Long orgId;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getWorkerId () {
        return workerId;
    }

    public void setWorkerId (Integer workerId) {
        this.workerId = workerId;
    }

    public String getTimePoint () {
        return timePoint;
    }

    public void setTimePoint (String timePoint) {
        this.timePoint = timePoint;
    }

    public String getDiscountNote () {
        return discountNote;
    }

    public void setDiscountNote (String discountNote) {
        this.discountNote = discountNote;
    }

    public Integer getWeekIndex () {
        return weekIndex;
    }

    public void setWeekIndex (Integer weekIndex) {
        this.weekIndex = weekIndex;
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