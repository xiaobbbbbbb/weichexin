package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class WcxMenu implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private String type;
	private String menuKey;
	private String name;
	private Integer ownId;
	private Integer level;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public String getMenuKey () {
        return menuKey;
    }

    public void setMenuKey (String menuKey) {
        this.menuKey = menuKey;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Integer getOwnId () {
        return ownId;
    }

    public void setOwnId (Integer ownId) {
        this.ownId = ownId;
    }

    public Integer getLevel () {
        return level;
    }

    public void setLevel (Integer level) {
        this.level = level;
    }
}