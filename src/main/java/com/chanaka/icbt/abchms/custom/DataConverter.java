package com.chanaka.icbt.abchms.custom;

import org.modelmapper.ModelMapper;

public class DataConverter {

	private static ModelMapper modelMapper = new ModelMapper();

	public static <T> Object convert(Object object, Class<T> className) {
		return modelMapper.map(object, className);
	}

}
