package com.example.demo.utils;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class JsonUtils {

	/**
	 * Prepare the Request JSON
	 * 
	 * @param <T>
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	public <T> String getRequestJSON(T request) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(request);
		return requestJson;
	}
}
