package com.ecarinfo.weichexin.api.protocol;

import java.io.Serializable;

public class ResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer status_code = ReponseState.OK;
	private String msg;
	private Object response_data;

	public Integer getStatus_code() {
		return status_code;
	}

	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResponse_data() {
		return response_data;
	}

	public void setResponse_data(Object response_data) {
		this.response_data = response_data;
	}

	@Override
	public String toString() {
		return "ResponseDto [status_code=" + status_code + ", msg=" + msg + ", response_data=" + response_data + "]";
	}
}
