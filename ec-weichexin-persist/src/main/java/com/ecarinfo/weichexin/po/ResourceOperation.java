package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class ResourceOperation implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private Integer bitIndex;
	private String code;//例如add/list/delete/discard/edit/download/check
	private String name;//添加、删除、作废、编辑、导出、审核

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getBitIndex () {
        return bitIndex;
    }

    public void setBitIndex (Integer bitIndex) {
        this.bitIndex = bitIndex;
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
}