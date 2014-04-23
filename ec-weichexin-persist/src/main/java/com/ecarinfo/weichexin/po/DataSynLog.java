package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class DataSynLog implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private String synTable;//同步表名
	private Date synTime;//同步时间
	private Boolean updated = false;

    public String getSynTable () {
        return synTable;
    }

    public void setSynTable (String synTable) {
        this.synTable = synTable;
    }

    public Date getSynTime () {
        return synTime;
    }

    public void setSynTime (Date synTime) {
        this.synTime = synTime;
    }

    public Boolean getUpdated () {
        return updated;
    }

    public void setUpdated (Boolean updated) {
        this.updated = updated;
    }
}