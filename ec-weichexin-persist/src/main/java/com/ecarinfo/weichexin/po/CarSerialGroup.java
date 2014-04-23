package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class CarSerialGroup implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private String name;//名称
	private String mark;//备注

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

    public String getMark () {
        return mark;
    }

    public void setMark (String mark) {
        this.mark = mark;
    }
}