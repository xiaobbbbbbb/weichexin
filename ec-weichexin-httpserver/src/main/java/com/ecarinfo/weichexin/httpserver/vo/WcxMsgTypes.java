package com.ecarinfo.weichexin.httpserver.vo;

public enum WcxMsgTypes {
	TEXT,
	IMAGE,
	VOICE,
	VIDEO,
	LOCATION,
	LINK;
	
	public String getString () {
		return this.name().toLowerCase();
	}
	public int getDBValue() {
		return this.ordinal();
	}
	
	public static WcxMsgTypes fromString(String str) {
		if (str==null) {
			return null;
		} 
		return WcxMsgTypes.valueOf(str.toUpperCase());
	}

	public static WcxMsgTypes fromDBValue(int value) {
		WcxMsgTypes type = null;
		switch (value) {
		case 0:
			type = TEXT;
			break;
		case 1:
			type = IMAGE;
			break;
		case 2:
			type = VOICE;
			break;
		case 3:
			type = VIDEO;
			break;
		case 4:
			type = LOCATION;
			break;
		case 5:
			type = LINK;
			break;
		default:
			break;
		}
		return type;		
	}
}
