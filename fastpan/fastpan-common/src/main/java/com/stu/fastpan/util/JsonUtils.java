package com.stu.fastpan.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	private JsonUtils() {

	}

	public static ObjectMapper getInstance() {

		return objectMapper;
	}
	
	/**
	 * javaBean,list,array convert to json string
	 */
	public static String obj2json(Object obj) throws Exception {

		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * javaBean,list,array convert to json string
	 */
	public static String obj2jsonInoreString(Object obj) throws Exception {

		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * json string convert to javaBean
	 */
	public static <T> T json2pojo(String jsonStr, Class<T> clazz)
			throws Exception {
		//objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); 
		return objectMapper.readValue(jsonStr, clazz);
	}

	public static <T> T jsonToObject(Object object, Class<T> clazz)throws Exception {
		return json2pojo(JsonUtils.obj2json(object),clazz);
	} 
	
	/**
	 * json string convert to map
	 */
	public static <T> Map<String, Object> json2map(String jsonStr)
			throws Exception {
		return objectMapper.readValue(jsonStr, Map.class);
	}

	/**
	 * json string convert to map with javaBean
	 */
	public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz)
			throws Exception {
		Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr,
				new TypeReference<Map<String, T>>() {
				});
		Map<String, T> result = new HashMap<String, T>();
		for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
		}
		return result;
	}

	/**
	 * json array string convert to list with javaBean
	 */
	public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)
			throws Exception {
		List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr,
				new TypeReference<List<T>>() {
				});
		List<T> result = new ArrayList<T>();
		for (Map<String, Object> map : list) {
			result.add(map2pojo(map, clazz));
		}
		return result;
	}

	/**
	 * map convert to javaBean
	 */
	public static <T> T map2pojo(Map map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * }
	 */

}
