package com.ecarinfo.weichexin.httpserver.vo;

import com.ecarinfo.weichexin.po.CarModel;

public class CarModelVO extends CarModel {
	private Float  refPrice;
	private Float ourPrice;
	private boolean hasGift;
	private boolean hasYouhui;
	public Float getRefPrice() {
		return refPrice;
	}
	public void setRefPrice(Float refPrice) {
		this.refPrice = refPrice;
	}
	public Float getOurPrice() {
		return ourPrice;
	}
	public void setOurPrice(Float ourPrice) {
		this.ourPrice = ourPrice;
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
