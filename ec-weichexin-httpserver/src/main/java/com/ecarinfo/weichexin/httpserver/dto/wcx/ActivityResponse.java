package com.ecarinfo.weichexin.httpserver.dto.wcx;

import java.io.Serializable;
import java.util.List;

public class ActivityResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private long total_page;// 总条数
	private int current_page;// 当前页码
	private List<ActivityItemResponse> activity_items;

	public long getTotal_page() {
		return total_page;
	}

	public void setTotal_page(long total_page) {
		this.total_page = total_page;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public List<ActivityItemResponse> getActivity_items() {
		return activity_items;
	}

	public void setActivity_items(List<ActivityItemResponse> activity_items) {
		this.activity_items = activity_items;
	}
}
