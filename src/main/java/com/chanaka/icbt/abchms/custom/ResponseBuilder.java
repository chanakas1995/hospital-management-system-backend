package com.chanaka.icbt.abchms.custom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

	public static ResponseEntity<Object> build(String message, ResponseType responseType, Object data) {

		switch (responseType) {
		case FOUND:
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponse(message + Texts.FOUND, data));
		case NOT_FOUND:
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponse(message + Texts.NOT_FOUND, null));
		case CREATED:
			return ResponseEntity.status(HttpStatus.CREATED).body(new JsonResponse(message + Texts.CREATED, data));
		case UPDATED:
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponse(message + Texts.UPDATED, data));
		case DELETED:
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponse(message + Texts.DELETED, null));
		case UNPROCESSABLE_ENTITY:
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(new JsonResponse(message + " data" + Texts.INVALID, data));
		case SERVER_ERROR:
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new JsonResponse(Texts.SERVER_ERROR, null));
		default:
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(Texts.SERVER_ERROR, null));
		}
	}

}
