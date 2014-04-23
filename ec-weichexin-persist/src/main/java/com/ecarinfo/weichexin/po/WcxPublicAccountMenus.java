package com.ecarinfo.weichexin.po;
import java.io.Serializable;

public class WcxPublicAccountMenus implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private Integer accountId;
	private Integer menuId;
	private Integer selected = 0;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getAccountId () {
        return accountId;
    }

    public void setAccountId (Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getMenuId () {
        return menuId;
    }

    public void setMenuId (Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getSelected () {
        return selected;
    }

    public void setSelected (Integer selected) {
        this.selected = selected;
    }
}