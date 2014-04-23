package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class ActivityPublishCriteria implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private Long activityId;//活动ID
	private Integer currentMileageBegin;
	private Integer currentMileageEnd;
	private Integer modelId;//车型ID
	private Long orgId;//5s店ID

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getActivityId () {
        return activityId;
    }

    public void setActivityId (Long activityId) {
        this.activityId = activityId;
    }

    public Integer getCurrentMileageBegin () {
        return currentMileageBegin;
    }

    public void setCurrentMileageBegin (Integer currentMileageBegin) {
        this.currentMileageBegin = currentMileageBegin;
    }

    public Integer getCurrentMileageEnd () {
        return currentMileageEnd;
    }

    public void setCurrentMileageEnd (Integer currentMileageEnd) {
        this.currentMileageEnd = currentMileageEnd;
    }

    public Integer getModelId () {
        return modelId;
    }

    public void setModelId (Integer modelId) {
        this.modelId = modelId;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }
}