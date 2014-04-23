package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class WcxShowCarModels implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private Long orgId;
	private Integer modelId;
	private Double refPrice;
	private Double ourPrice;
	private String colors;
	private Boolean isTop = false;
	private Date topTime;//置顶时间
	private String comment;
	private Date pubTime;
	private Date checkTime;
	private Date discardTime;
	private String checkComment;
	private String discardComment;
	private Integer pubUserId;
	private Integer checkUserId;
	private Integer discardUserId;
	private Integer status;//状态(0:待审核；1:生效；2: 未通过；3:已停用

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }

    public Integer getModelId () {
        return modelId;
    }

    public void setModelId (Integer modelId) {
        this.modelId = modelId;
    }

    public Double getRefPrice () {
        return refPrice;
    }

    public void setRefPrice (Double refPrice) {
        this.refPrice = refPrice;
    }

    public Double getOurPrice () {
        return ourPrice;
    }

    public void setOurPrice (Double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public String getColors () {
        return colors;
    }

    public void setColors (String colors) {
        this.colors = colors;
    }

    public Boolean getIsTop () {
        return isTop;
    }

    public void setIsTop (Boolean isTop) {
        this.isTop = isTop;
    }

    public Date getTopTime () {
        return topTime;
    }

    public void setTopTime (Date topTime) {
        this.topTime = topTime;
    }

    public String getComment () {
        return comment;
    }

    public void setComment (String comment) {
        this.comment = comment;
    }

    public Date getPubTime () {
        return pubTime;
    }

    public void setPubTime (Date pubTime) {
        this.pubTime = pubTime;
    }

    public Date getCheckTime () {
        return checkTime;
    }

    public void setCheckTime (Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getDiscardTime () {
        return discardTime;
    }

    public void setDiscardTime (Date discardTime) {
        this.discardTime = discardTime;
    }

    public String getCheckComment () {
        return checkComment;
    }

    public void setCheckComment (String checkComment) {
        this.checkComment = checkComment;
    }

    public String getDiscardComment () {
        return discardComment;
    }

    public void setDiscardComment (String discardComment) {
        this.discardComment = discardComment;
    }

    public Integer getPubUserId () {
        return pubUserId;
    }

    public void setPubUserId (Integer pubUserId) {
        this.pubUserId = pubUserId;
    }

    public Integer getCheckUserId () {
        return checkUserId;
    }

    public void setCheckUserId (Integer checkUserId) {
        this.checkUserId = checkUserId;
    }

    public Integer getDiscardUserId () {
        return discardUserId;
    }

    public void setDiscardUserId (Integer discardUserId) {
        this.discardUserId = discardUserId;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }
}