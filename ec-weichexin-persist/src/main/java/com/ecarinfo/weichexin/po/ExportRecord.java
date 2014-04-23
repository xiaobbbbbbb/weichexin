package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class ExportRecord implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String fileUrl;
	private Integer fileType;
	private Date exportTime;
	private Integer exportUserId;
	private Long orgId;
	private String orgCode;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getFileUrl () {
        return fileUrl;
    }

    public void setFileUrl (String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getFileType () {
        return fileType;
    }

    public void setFileType (Integer fileType) {
        this.fileType = fileType;
    }

    public Date getExportTime () {
        return exportTime;
    }

    public void setExportTime (Date exportTime) {
        this.exportTime = exportTime;
    }

    public Integer getExportUserId () {
        return exportUserId;
    }

    public void setExportUserId (Integer exportUserId) {
        this.exportUserId = exportUserId;
    }

    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }
}