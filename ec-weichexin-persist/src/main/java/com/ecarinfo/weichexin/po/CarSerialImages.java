package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class CarSerialImages implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private Long orgId;
	private Integer serialId;
	private Integer width;
	private Integer height;
	private String url;
	private Integer status;//状态

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

    public Integer getSerialId () {
        return serialId;
    }

    public void setSerialId (Integer serialId) {
        this.serialId = serialId;
    }

    public Integer getWidth () {
        return width;
    }

    public void setWidth (Integer width) {
        this.width = width;
    }

    public Integer getHeight () {
        return height;
    }

    public void setHeight (Integer height) {
        this.height = height;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }
}