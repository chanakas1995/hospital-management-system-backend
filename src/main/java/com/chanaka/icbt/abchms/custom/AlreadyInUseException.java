package com.chanaka.icbt.abchms.custom;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public class AlreadyInUseException extends Exception {

	private String entity;
	private String field;

	public AlreadyInUseException(String entity, String field) {
		this.entity = entity;
		this.field = field;
	};

	public ResponseEntity<Object> getJsonResponse() {
		Map<String, String> errors = new HashMap<>();
		errors.put(field, field + Texts.ALREADY_IN_USE);
		return ResponseBuilder.build(entity, ResponseType.UNPROCESSABLE_ENTITY, errors);
	}
}
