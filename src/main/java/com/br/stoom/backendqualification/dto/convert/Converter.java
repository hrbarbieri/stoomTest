package com.br.stoom.backendqualification.dto.convert;

public interface Converter<IN, OUT> {
	OUT convert(IN in);
	
	OUT convert(IN in, OUT out);
}
