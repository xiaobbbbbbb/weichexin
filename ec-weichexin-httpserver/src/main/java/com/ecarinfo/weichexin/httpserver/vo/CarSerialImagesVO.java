package com.ecarinfo.weichexin.httpserver.vo;

import com.ecarinfo.weichexin.po.CarSerial;

public class CarSerialImagesVO extends CarSerial {
	private String imageUrl;
	private int modelId;
	private boolean hasGift;
	private boolean hasYouhui;
	
	
	
	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isHasGift() {
		return hasGift;
	}

	public void setHasGift(boolean hasGift) {
		this.hasGift = hasGift;
	}

	public boolean isHasYouhui() {
		return hasYouhui;
	}

	public void setHasYouhui(boolean hasYouhui) {
		this.hasYouhui = hasYouhui;
	}
	
}
