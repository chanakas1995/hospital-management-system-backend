package com.chanaka.icbt.abchms.custom;

public class JsonResponse {

	private String message;
	private Object data;

	public JsonResponse(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

}
