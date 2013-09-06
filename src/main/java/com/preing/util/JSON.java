package com.preing.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class JSON {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final Log logger = LogFactory.getLog(JsonParser.class);

	public static String encode(Object object) {
		String json = null;
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			logger.info("JsonGenerationException " + e.getMessage());  
		} catch (JsonMappingException e) {
			logger.info("JsonMappingException " + e.getMessage());
		} catch (IOException e) {
			logger.info("IOException " + e.getMessage());
		}
		return json;
	}

	public static <T> T decode(String content, Class<T> clazzs) {
		T t = null;
		try {
			t = objectMapper.readValue(content, clazzs);
		} catch (JsonParseException e) {
			logger.info("JsonParseException " + e.getMessage());
		} catch (JsonMappingException e) {
			logger.info("JsonMappingException " + e.getMessage());
		} catch (IOException e) {
			logger.info("IOException " + e.getMessage());
		}
		return t;
	}

	public static <T> T decode(String content, TypeReference<T> typeReference) {
		T t = null;
		try {
			t = objectMapper.readValue(content, typeReference);
		} catch (JsonParseException e) {
			logger.info("JsonParseException " + e.getMessage());
		} catch (JsonMappingException e) {
			logger.info("JsonMappingException " + e.getMessage());
		} catch (IOException e) {
			logger.info("IOException " + e.getMessage());
		}
		return t;
	}

	public static <T> T decode(InputStream in, Class<T> clazzs) {
		T t = null;
		try {
			t = objectMapper.readValue(in, clazzs);
		} catch (JsonParseException e) {
			logger.info("JsonParseException " + e.getMessage());
		} catch (JsonMappingException e) {
			logger.info("JsonMappingException " + e.getMessage());
		} catch (IOException e) {
			logger.info("IOException " + e.getMessage());
		}
		return t;
	}
}
