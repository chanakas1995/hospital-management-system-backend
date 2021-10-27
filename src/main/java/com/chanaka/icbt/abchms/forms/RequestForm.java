package com.chanaka.icbt.abchms.forms;

import com.chanaka.icbt.abchms.custom.Texts;

public abstract class RequestForm {
	protected String entityName;

	protected final String REQUIRED = Texts.REQUIRED;
	protected final String INVALID = Texts.INVALID;
	protected final String PAST_DATE_OR_EQUAL = Texts.PAST_DATE_OR_EQUAL;

	public String getEntityName() {
		return entityName;
	}
}
